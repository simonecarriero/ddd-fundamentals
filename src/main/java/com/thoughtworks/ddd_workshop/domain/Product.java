package com.thoughtworks.ddd_workshop.domain;

import java.util.Objects;

public class Product {
    private final String name;
    private final Price price;

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
