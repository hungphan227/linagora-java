package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.PopularPurchase;

import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
        return Flux.error(new RuntimeException("Please implement me!!!"));
    }

    @GetMapping("/api/recent_purchases/{username}")
    Flux<Product> usersWithSimilarPurchases(@PathVariable String username) {
        // TODO implement me...
        return popularPurchasesService.findPopularPurchases(username);
//        return Flux.error(new RuntimeException("Please implement me!!!"));
    }
}
