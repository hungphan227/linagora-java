package com.linagora.coding.exercise;

import com.linagora.coding.exercise.model.Product;
import com.linagora.coding.exercise.model.Purchase;
import com.linagora.coding.exercise.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RestClient {

    Flux<Purchase> getRecentPurchases(String username);
    Flux<User> getUsersPurchasingProduct(UUID productId);
    Mono<Product> getProductById(UUID productId);

}
