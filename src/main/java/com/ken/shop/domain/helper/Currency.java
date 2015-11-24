package com.ken.shop.domain.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@RequiredArgsConstructor
public enum Currency {
    DOLLARS(1, "USD"), POUNDS(0.5, "GBP"), EURO(0.75, "EUR");

    @Getter
    private double rate;

    @Getter
    private String symbol;

    Currency(double rate, String symbol) {
        this.rate = rate;
        this.symbol = symbol;
    }
}
