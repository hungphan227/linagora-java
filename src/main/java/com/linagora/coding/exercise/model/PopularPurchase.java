package com.linagora.coding.exercise.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class PopularPurchase {
    public static class ProductInfo {
        private final ImmutableList<User> recentBuyingUsers;
        private final float price;

        public ProductInfo(ImmutableList<User> recentBuyingUsers, float price) {
            Preconditions.checkArgument(price > 0);

            this.recentBuyingUsers = recentBuyingUsers;
            this.price = price;
        }

        public ImmutableList<User> getRecentBuyingUsers() {
            return recentBuyingUsers;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            ProductInfo that = (ProductInfo) o;
            return Objects.equal(recentBuyingUsers, that.recentBuyingUsers);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(recentBuyingUsers);
        }
    }
    private final ImmutableMap<Product, ProductInfo> recentlyBoughtProductInformation;

    public PopularPurchase(ImmutableMap<Product, ProductInfo> recentlyBoughtProductInformation) {
        this.recentlyBoughtProductInformation = recentlyBoughtProductInformation;
    }

    public ImmutableMap<Product, ProductInfo> getRecentlyBoughtProductInformation() {
        return recentlyBoughtProductInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PopularPurchase that = (PopularPurchase) o;
        return Objects.equal(recentlyBoughtProductInformation, that.recentlyBoughtProductInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(recentlyBoughtProductInformation);
    }
}
