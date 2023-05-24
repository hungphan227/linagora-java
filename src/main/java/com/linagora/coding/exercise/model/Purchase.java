package com.linagora.coding.exercise.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.google.common.base.Objects;

public class Purchase {
    private UUID purchaseId;
    private UUID userId;
    private UUID productId;
    private ZonedDateTime date;

    public Purchase() {}

    public Purchase(UUID purchaseId, UUID userId, UUID productId, ZonedDateTime date) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.productId = productId;
        this.date = date;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equal(purchaseId, purchase.purchaseId) &&
            Objects.equal(userId, purchase.userId) &&
            Objects.equal(date, purchase.date) &&
            Objects.equal(productId, purchase.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(purchaseId, userId, date, productId);
    }
}
