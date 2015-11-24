package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ken.shop.domain.helper.Currency;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
@Entity
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true)
    @JsonProperty
    private String id;

    /** Cart owner */
    @JsonProperty
    @Column(nullable = false)
    private Guest guest;

    @JsonProperty
    @Column(nullable = false)
    private List<Item> items;

    @JsonProperty
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @JsonProperty
    @Column(nullable = false)
    private Currency currency;

    protected Cart() { }

    public Cart(Guest guest, List<Item> items, Currency currency) {
        this.guest = guest;
        this.items = items;
        this.currency = currency;

        this.totalPrice = BigDecimal.ZERO;
        for (Item item : this.items) {
            this.totalPrice =
                    this.totalPrice.add(item.getPrice().multiply(BigDecimal.valueOf(currency.getRate())));
        }
    }
}
