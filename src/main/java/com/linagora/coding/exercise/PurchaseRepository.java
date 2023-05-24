package com.linagora.coding.exercise;

import java.util.UUID;

import com.linagora.coding.exercise.model.Purchase;

import reactor.core.publisher.Flux;

public interface PurchaseRepository {
    Flux<Purchase> purchaseByUser(UUID userId);

    Flux<Purchase> purchaseByProduct(UUID productId);
}
