package com.ken.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ken.shop.domain.helper.Currency;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @JoinColumn(name="guest")
    @OneToOne
    private Guest guest;

    @JsonProperty
    @OneToMany(fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @JsonProperty
    @Column(nullable = false)
    private BigDecimal totalPrice;

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

    public String getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
