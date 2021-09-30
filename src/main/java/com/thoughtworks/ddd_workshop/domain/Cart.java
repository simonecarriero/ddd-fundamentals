package com.thoughtworks.ddd_workshop.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Cart {

    private final UUID id = UUID.randomUUID();
    private List<Item> items = new ArrayList<>();
    private final List<Item> removedItems = new ArrayList<>();
    private final List<Event> events = new ArrayList<>();
    private boolean checkedOut = false;

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
        raise(new AddItemEvent(item.getProduct().getName(), item.getProduct().getPrice(), item.getQuantity()));
    }

    public void removeProducts(String productName) {
        raise(new RemoveItemEvent(productName));
    }

    public List<Item> getRemovedProducts() {
        return this.removedItems;
    }

    public boolean contains(String productName) {
        return this.items.stream()
                .anyMatch(item -> Objects.equals(item.getProduct().getName(), productName));
    }

    public long countProduct(String productName) {
        Optional<Item> found = this.items.stream()
                .filter(item -> Objects.equals(item.getProduct().getName(), productName))
                .findFirst();

        if (found.isPresent()) {
            return found.get().getQuantity();
        }

        return 0;
    }

    private void raise(AddItemEvent event) {
        this.events.add(event);
        this.items.add(new Item(new Product(event.name, event.price), event.quantity));
    }

    private void raise(RemoveItemEvent event) {
        this.events.add(event);

        String productName = event.name;
        var filteredProducts = this.items.stream()
                .filter(p -> !Objects.equals(p.getProduct().getName(), productName))
                .collect(Collectors.toList());

        if (filteredProducts.size() < this.items.size()) {
            this.removedItems.add(new Item(new Product(productName, null), 0));
            this.items = filteredProducts;
        }
    }

    public boolean isCheckedOut() {
        return this.checkedOut;
    }

    public Order checkout() {
        this.checkedOut = true;
        List<Product> products = new ArrayList<>();
        for (var item: items) {
            for (int i=0; i<item.getQuantity(); ++i) {
                products.add(item.getProduct());
            }
        }
        var order =  new Order(products);

        return order;
    }

    private class Event {}

    private class AddItemEvent extends Event {
        private final String name;
        private final Price price;
        private final int quantity;

        public AddItemEvent(String name, Price price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    private class RemoveItemEvent extends Event {
        private final String name;

        public RemoveItemEvent(String name) {
            this.name = name;
        }
    }
}
