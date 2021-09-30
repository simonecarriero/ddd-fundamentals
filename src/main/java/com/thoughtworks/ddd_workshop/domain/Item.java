package com.thoughtworks.ddd_workshop.domain;

import java.util.Objects;

public class Item {

    private Product product;
    private int quantity;

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(product, item.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
