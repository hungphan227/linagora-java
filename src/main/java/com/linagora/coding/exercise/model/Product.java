package com.linagora.coding.exercise.model;

import java.util.UUID;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;


public class Product {
    private UUID id;
    private String name;
    private float price;

    public Product() {}

    public Product(UUID id, String name, float price) {
        Preconditions.checkArgument(price > 0);

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 &&
            Objects.equal(id, product.id) &&
            Objects.equal(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, price);
    }
}
