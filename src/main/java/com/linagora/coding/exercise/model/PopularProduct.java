package com.linagora.coding.exercise.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PopularProduct {

    private UUID id;
    private String name;
    private float price;
    private List<String> recent;

    public PopularProduct() {}

    public PopularProduct(UUID id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getRecent() {
        return recent;
    }

    public PopularProduct setRecent(List<String> recent) {
        this.recent = recent;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopularProduct that = (PopularProduct) o;
        return Float.compare(that.price, price) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(recent, that.recent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, recent);
    }
}
