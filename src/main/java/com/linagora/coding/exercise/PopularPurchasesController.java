package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.PopularProduct;
import com.linagora.coding.exercise.model.PopularPurchase;

import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PopularPurchasesController {

    private PopularPurchasesService popularPurchasesService;

//    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PopularPurchasesController(PopularPurchasesService popularPurchasesService) {
        this.popularPurchasesService = popularPurchasesService;
    }

    @GetMapping("/")
    Flux<String> welcome() {
        return Flux.just("Hello!");
    }

    @GetMapping("/api/recent_purchases/{username}")
    Flux<PopularProduct> usersWithSimilarPurchases(@PathVariable String username) {
        return popularPurchasesService.findPopularPurchases(username);
//        return Flux.error(new RuntimeException("Please implement me!!!"));
    }
}
