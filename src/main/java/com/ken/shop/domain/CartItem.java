package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
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
    @Getter
    private String id;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name="item")
    @OneToOne
    private Item item;

}
