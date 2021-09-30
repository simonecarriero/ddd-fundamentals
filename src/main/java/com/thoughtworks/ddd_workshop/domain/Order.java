package com.thoughtworks.ddd_workshop.domain;

import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id = UUID.randomUUID();
    private final List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    public int getNumberOfProducts() {
        return this.products.size();
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
