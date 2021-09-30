package com.thoughtworks.ddd_workshop.domain;

import java.util.Map;

public class CompetitorBasedPricer {
    private final Map<String, Double> prices = Map.of(
            "Hero ink Pen", 0.99,
            "Ipad Pro", 999.99,
            "Reebok cricket bat", 56.39);
    private final Double ratio = 0.9;

    public double getAdjustedPrice(String productName) {
        return prices.get(productName) * ratio;
    }
}
