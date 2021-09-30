package com.thoughtworks.ddd_workshop.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private final UUID id = UUID.randomUUID();
    private List<Item> items = new ArrayList<>();
    private final List<Item> removedItems = new ArrayList<>();

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
        return this.items.isEmpty();
    }

    public void addProduct(Item item) {
        this.items.add(item);
    }

    public void addProduct(Item item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.addProduct(item);
        }
    }

    public boolean contains(String productName) {
        return this.items.stream()
                .anyMatch(item -> Objects.equals(item.getName(), productName));
    }

    public long countProduct(String productName) {
        return this.items.stream()
                .filter(item -> Objects.equals(item.getName(), productName))
                .count();
    }

    public void removeProducts(String productName) {
        var filteredProducts = this.items.stream()
                .filter(p -> !Objects.equals(p.getName(), productName))
                .collect(Collectors.toList());

        if (filteredProducts.size() < this.items.size()) {
            this.removedItems.add(new Item(productName));
            this.items = filteredProducts;
        }

    }

    public List<Item> getRemovedProducts() {
        return this.removedItems;
    }
}
