package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ken.shop.domain.helper.Currency;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.List;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@Entity
public class Store {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true)
    @JsonProperty
    private String id;

    @JsonProperty
    @Column(name = "name", unique = true)
    private String name;

    @Getter
    @JsonProperty(value = "availableCurrencies")
    @Column(name = "currencies")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Currency> currencies;

    @Getter
    @JsonProperty(value = "defaultCurrency")
    @Column(name = "currency", unique = true)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @JsonProperty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<Item> items;

    protected Store() { }

    public Store(String name, List<Currency> currencies, Currency currency){
        this.name = name;
        this.currencies = currencies;
        this.currency = currency;
    }

}
