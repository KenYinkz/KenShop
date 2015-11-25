package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
@Entity
public class CartItem {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true)
    @JsonProperty
    private String id;

    @JsonProperty
    @JoinColumn(name="item")
    @OneToOne
    private Item item;

    public String getId() {
        return id;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
