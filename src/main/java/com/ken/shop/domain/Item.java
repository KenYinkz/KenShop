package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@Entity
public class Item  implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true)
    @JsonProperty
    private String id;

    @JsonProperty
    @Column(nullable = false)
    private String name;

    @JsonProperty
    @Column(nullable = false)
    private BigDecimal price;

    @JsonProperty
    @Column(nullable = false)
    private String formattedPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", nullable = false)
    private Store store;

    protected Item () { }

    public Item(String name, BigDecimal price, Store store) {
        this.name = name;
        this.price = price;
        this.store = store;
        this.formattedPrice = store.getCurrency().getSymbol() + " " + String.valueOf(price);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
