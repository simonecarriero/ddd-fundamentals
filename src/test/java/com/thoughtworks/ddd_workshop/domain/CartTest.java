package com.thoughtworks.ddd_workshop.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private static PriceCalculator pricer = new PriceCalculator();

    private static String HERO_INK_PEN_NAME = "Hero ink Pen";
    private static String IPAD_PRO_NAME = "Ipad Pro";
    private static String REEBOK_CRICKET_BAT_NAME = "Reebok cricket bat";

    private static Product HERO_INK_PEN = new Product(HERO_INK_PEN_NAME, pricer.calculate(HERO_INK_PEN_NAME));
    private static Product IPAD_PRO = new Product(IPAD_PRO_NAME,pricer.calculate(IPAD_PRO_NAME));
    private static Product REEBOK_CRICKET_BAT = new Product(REEBOK_CRICKET_BAT_NAME, pricer.calculate(REEBOK_CRICKET_BAT_NAME));

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
    void cartIsCheckedOut() {
        var cart = new Cart();

        assertFalse(cart.isCheckedOut());
        var order = cart.checkout();
        assertTrue(cart.isCheckedOut());
    }

    @Test
    void checkoutGenerateOrder() {
        var cart = new Cart();

        cart.addProduct(new Item(IPAD_PRO, 3));
        cart.addProduct(new Item(REEBOK_CRICKET_BAT, 2));
        var order = cart.checkout();

        assertEquals(5, order.getNumberOfProducts());
        assertEquals(List.of(IPAD_PRO, IPAD_PRO, IPAD_PRO, REEBOK_CRICKET_BAT, REEBOK_CRICKET_BAT), order.getProducts());
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
