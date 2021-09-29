package com.thoughtworks.ddd_workshop.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private static String HERO_INK_PEN_NAME = "Hero ink Pen";
    private static String IPAD_PRO = "Ipad Pro";
    private static String REEBOK_CRICKET_BAT = "Reebok cricket bat";

    @Test
    void addIpadProToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(IPAD_PRO));

        assertFalse(cart.isEmpty());
        assertTrue(cart.contains(IPAD_PRO));
    }

    @Test
    void addHeroInkPenToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(HERO_INK_PEN_NAME));

        assertFalse(cart.isEmpty());
        assertTrue(cart.contains(HERO_INK_PEN_NAME));
        assertFalse(cart.contains(IPAD_PRO));
    }

    @Test
    void addTwoReebokCricketBatsToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(REEBOK_CRICKET_BAT), 2);

        assertEquals(2, cart.countProduct(REEBOK_CRICKET_BAT));
    }

    @Test
    void addOnePlusOneReebokCricketBatsToTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(REEBOK_CRICKET_BAT));
        cart.addProduct(new Product(REEBOK_CRICKET_BAT));

        assertEquals(2, cart.countProduct(REEBOK_CRICKET_BAT));
    }

    @Test
    void removeAllReebokCricketBatsFromTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(REEBOK_CRICKET_BAT), 3);
        cart.removeProducts(REEBOK_CRICKET_BAT);

        assertEquals(0, cart.countProduct(REEBOK_CRICKET_BAT));
    }

    @Test
    void notRemoveOtherProductsWhenRemovingAllReebokCricketBatsFromTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(IPAD_PRO));
        cart.addProduct(new Product(REEBOK_CRICKET_BAT));
        cart.removeProducts(REEBOK_CRICKET_BAT);

        assertEquals(1, cart.countProduct(IPAD_PRO));
    }

    @Test
    void keepTrackOfTheProductsThatHaveBeenRemovedFromTheCart() {
        var cart = new Cart();

        cart.addProduct(new Product(IPAD_PRO));
        cart.removeProducts(IPAD_PRO);

        assertEquals(List.of(new Product(IPAD_PRO)), cart.getRemovedProducts());
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
        cart1.addProduct(new Product(IPAD_PRO));

        Cart cart2 = new Cart();
        cart2.addProduct(new Product(IPAD_PRO));

        assertNotEquals(cart1, cart2);
    }
}
