package com.thoughtworks.ddd_workshop.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private final UUID id = UUID.randomUUID();
    private List<Product> products = new ArrayList<>();
    private final List<Product> removedProducts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isEmpty() {
        return this.products.isEmpty();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.addProduct(product);
        }
    }

    public boolean contains(String productName) {
        return this.products.stream()
                .anyMatch(product -> Objects.equals(product.getName(), productName));
    }

    public long countProduct(String productName) {
        return this.products.stream()
                .filter(product -> Objects.equals(product.getName(), productName))
                .count();
    }

    public void removeProducts(String productName) {
        var filteredProducts = this.products.stream()
                .filter(p -> !Objects.equals(p.getName(), productName))
                .collect(Collectors.toList());

        if (filteredProducts.size() < this.products.size()) {
            this.removedProducts.add(new Product(productName));
            this.products = filteredProducts;
        }

    }

    public List<Product> getRemovedProducts() {
        return this.removedProducts;
    }
}
