package com.thoughtworks.ddd_workshop.domain;

import java.util.Currency;
import java.util.Map;

public class PriceCalculator {
    private final Map<String, Double> prices = Map.of(
            "Hero ink Pen", 0.99,
            "Ipad Pro", 999.99,
            "Reebok cricket bat", 56.39);
    private final Double ratio = 0.9;

    public Price calculate(String productName) {
        return new Price(prices.get(productName) * ratio, Currency.getInstance("USD"));
    }
}
