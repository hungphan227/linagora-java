package com.linagora.coding.exercise;

import java.util.UUID;

import com.linagora.coding.exercise.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> retrieve(UUID productId);

    Flux<Product> list();
}
