package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
@Entity
public class Guest {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true)
    @JsonProperty
    @Getter
    private String id;

    @JsonProperty
    @Column(name = "name", unique = true)
    private String name;

    protected Guest() { }

    public Guest(String name) {
        this.name = name;
    }
}
