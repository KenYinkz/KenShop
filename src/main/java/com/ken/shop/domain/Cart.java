package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ken.shop.domain.helper.Currency;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Getter
    @JsonProperty
    @JoinColumn(name="guest")
    @OneToOne
    private Guest guest;

    @Getter
    @Setter
    @JsonProperty
    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Getter
    @Setter
    @JsonProperty
    @Column(name = "currency", unique = true)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    protected Cart() { }

    public Cart(Guest guest, List<CartItem> cartItems, Currency currency) {
        this.guest = guest;
        this.currency = currency;
        this.cartItems = cartItems;

        this.totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem : this.cartItems) {
            this.totalPrice =
                    this.totalPrice.add(cartItem.getItem().getPrice().multiply(BigDecimal.valueOf(currency.getRate())));
        }
    }
}
