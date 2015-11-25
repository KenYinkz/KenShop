package com.ken.shop.domain.helper;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
public enum Currency {
    DOLLARS(1, "USD"), POUNDS(0.5, "GBP"), EURO(0.75, "EUR");

    private double rate;

    private String symbol;

    Currency(double rate, String symbol) {
        this.rate = rate;
        this.symbol = symbol;
    }

    public double getRate() {
        return rate;
    }

    public String getSymbol() {
        return symbol;
    }
}
