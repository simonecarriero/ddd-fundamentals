package com.thoughtworks.ddd_workshop.domain;

import org.junit.jupiter.api.Test;

import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private static Product HERO_INK_PEN = new Product("Hero ink Pen", new Price(0.99, Currency.getInstance("USD")));
    private static Product IPAD_PRO = new Product("Ipad Pro", new Price(999.99, Currency.getInstance("USD")));
    private static Product REEBOK_CRICKET_BAT = new Product("Reebok cricket bat", new Price(150.2, Currency.getInstance("USD")));

    @Test
    void addIpadProToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Item(IPAD_PRO, 1));

        assertFalse(cart.isEmpty());
        assertTrue(cart.contains(IPAD_PRO.getName()));
    }

    @Test
    void addHeroInkPenToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Item(HERO_INK_PEN, 1));

        assertFalse(cart.isEmpty());
        assertTrue(cart.contains(HERO_INK_PEN.getName()));
        assertFalse(cart.contains(IPAD_PRO.getName()));
    }

    @Test
    void addTwoReebokCricketBatsToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Item(REEBOK_CRICKET_BAT, 2));

        assertEquals(2, cart.countProduct(REEBOK_CRICKET_BAT.getName()));
    }

    @Test
    void removeAllReebokCricketBatsFromTheCart() {
        var cart = new Cart();

        cart.addProduct(new Item(REEBOK_CRICKET_BAT, 3));
        cart.removeProducts(REEBOK_CRICKET_BAT.getName());

        assertEquals(0, cart.countProduct(REEBOK_CRICKET_BAT.getName()));
    }

    @Test
    void notRemoveOtherProductsWhenRemovingAllReebokCricketBatsFromTheCart() {
        var cart = new Cart();

        cart.addProduct(new Item(IPAD_PRO, 1));
        cart.addProduct(new Item(REEBOK_CRICKET_BAT, 1));
        cart.removeProducts(REEBOK_CRICKET_BAT.getName());

        assertEquals(1, cart.countProduct(IPAD_PRO.getName()));
    }

    @Test
    void keepTrackOfTheProductsThatHaveBeenRemovedFromTheCart() {
        var cart = new Cart();
        Item item = new Item(IPAD_PRO, 1);

        cart.addProduct(item);
        cart.removeProducts(IPAD_PRO.getName());

        assertEquals(List.of(item), cart.getRemovedProducts());
    }

    @Test
    void notKeepTrackOfAttemptsToRemoveProductsNotInTheCart() {
        var cart = new Cart();

        cart.removeProducts("product not in the cart");

        assertEquals(List.of(), cart.getRemovedProducts());
    }

    @Test
    void twoCartsShouldNotBeTheSame() {
        Cart cart1 = new Cart();
        cart1.addProduct(new Item(IPAD_PRO, 1));

        Cart cart2 = new Cart();
        cart2.addProduct(new Item(IPAD_PRO, 1));

        assertNotEquals(cart1, cart2);
    }

    @Test
    void itemHasAProduct() {
        Item item = new Item(IPAD_PRO, 1);
    }
}
