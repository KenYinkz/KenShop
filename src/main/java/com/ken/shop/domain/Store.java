package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ken.shop.domain.helper.Currency;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import javax.persistence.EnumType;
import javax.persistence.FetchType;

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

    @JsonProperty(value = "selectedCurrency")
    @Column(name="currency")
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;

    @JsonProperty
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<Item> items;

    protected Store() { }

    public Store(String name, Currency defaultCurrency){
        this.name = name;
        this.currency = defaultCurrency;
    }

}
