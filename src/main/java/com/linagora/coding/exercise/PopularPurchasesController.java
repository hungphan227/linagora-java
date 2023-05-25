package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.PopularProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

@RestController
public class PopularPurchasesController {

    private PopularPurchasesServiceImpl popularPurchasesService;

    @Autowired
    public PopularPurchasesController(PopularPurchasesServiceImpl popularPurchasesService) {
        this.popularPurchasesService = popularPurchasesService;
    }

    @GetMapping("/api/recent_purchases/{username}")
    Flux<PopularProduct> usersWithSimilarPurchases(@PathVariable String username) {
        return popularPurchasesService.findPopularPurchases(username).onErrorResume(ex -> {
            if (ex instanceof WebClientResponseException.BadRequest) {
                return Flux.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with username of '" + username + "' was not found"));
            }
            return Flux.error(new RuntimeException("Unknown error"));
        });
    }
}
