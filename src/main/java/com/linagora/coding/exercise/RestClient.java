package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.Purchase;
import com.linagora.coding.exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class RestClient {

    @Autowired
    private WebClient webClient;

    public Flux<Purchase> getRecentPurchases(String username) {
        return webClient.get()
                .uri("/api/purchases/by_user/" + username + "?limit=5")
                .retrieve()
                .bodyToFlux(Purchase.class);
    }

    public Flux<User> getUsersPurchasingProduct(UUID productId) {
        return webClient.get()
                .uri("/api/purchases/by_product/" + productId)
                .retrieve()
                .bodyToFlux(User.class);
    }

    public Mono<Product> getProductById(UUID productId) {
        return webClient.get()
                .uri("/api/products/" + productId)
                .retrieve()
                .bodyToMono(Product.class);
    }

}
