package com.ken.shop.web;

import com.ken.shop.domain.*;
import com.ken.shop.domain.helper.Currency;
import com.ken.shop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@RestController
public class ShopController {

    private static final Logger log = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    GuestRepository guestRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Ken's Shop";
    }

    /**
     * GET - Fetch list of items
     * @return
     */
    @Cacheable("items")
    @RequestMapping("/items")
    public ResponseEntity<List<Item>> getItems() {
        try {
            List<Item> items = (List<Item>)itemRepository.findAll();
            if (items.size() < 1){
                return new ResponseEntity<>(NOT_FOUND);
            }
            return new ResponseEntity<>(items, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    /**
     * GET - Get our saved guest
     * @return
     */
    @Cacheable("guests")
    @RequestMapping("/guest")
    public ResponseEntity<Guest> getGuest() {

        try {
            // obviously if we had authentication in place this
            // will be replaced, but for now, lets stick with Bond!
            Guest guest = guestRepository.findByName("James Bond");
            return new ResponseEntity<>(guest, OK);
        } catch (Exception e) {
            // throw not found for now.
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    /**
     * GET - Fetch store and data in it i.e. items, currency
     * @return
     */
    @Cacheable("store")
    @RequestMapping("/store")
    public ResponseEntity<Store> getStore() {

        try {

            Store store = storeRepository.findByName("KenShop");
            return new ResponseEntity<>(store, OK);

        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }


    /**
     * Simple adding an item to cart.
     * Pass in a cart object
     *
     * @param cart
     * @return
     */
    @RequestMapping(value = "/cart",
                    method = RequestMethod.POST)
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart){

        try {

            cart.getCartItems().forEach(cartItem -> {
                cartItem.setCart(cart);
                cartItemRepository.save(cartItem);
            });

            // we need a new cart object. Why? So our constructor can derive the totalPrice
            Cart c = new Cart(cart.getGuest(), cart.getCartItems(), cart.getCurrency());
            cartRepository.save(c);
            // update...
//            cart.getCartItems().forEach(cartItem -> {
//                cartItem.setCart(c);
//                cartItemRepository.save(cartItem);
//            });
            return new ResponseEntity<>(c, OK);
        } catch (Exception e) {
            // we can do better...by sending the reason API failed!
                return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    /**
     * Update cart...add more Item
     * @param cartId
     * @param items
     * @return
     */
    @RequestMapping(value = "/cart/{cartId}/items",
                    method = RequestMethod.PUT)
    public ResponseEntity<Cart> addItemToCart(@PathVariable("cartId") String cartId, @RequestBody List<Item> items) {
        try {
            Cart cart = cartRepository.findOne(cartId);
            if (cart == null) {// fail fast
                return new ResponseEntity<>(NOT_FOUND);
            }
            // use Item object from store ... alternatively add store to item --> item.setStore(store);
            Store store = storeRepository.findByName("KenShop");
            List<CartItem> cartItems = new ArrayList<>();
            items.forEach(item -> {
                item.setStore(store);
                CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setItem(item);
                cartItems.add(cartItem);
            });
            cartItemRepository.save(cartItems);

            cart.getCartItems().addAll(cartItems);

            // update total Price
            updateCartPrice(cart);
            cartRepository.save(cart);

            return new ResponseEntity<>(cart, OK);

        } catch (Exception e) {
            // we can do better...by sending the reason the API failed!
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    /**
     * Update cart..change cart currency
     * @param cartId
     * @param currency
     * @return
     */
    @RequestMapping(value = "/cart/{cartId}/{currency}",
                    method = RequestMethod.PUT)
    public ResponseEntity<Cart> updateCurrency(@PathVariable("cartId") String cartId,
                                               @PathVariable("currency") String currency) {
        try {
            Cart cart = cartRepository.findOne(cartId);
            if (cart == null) {// fail fast
                return new ResponseEntity<>(NOT_FOUND);
            }
            cart.setCurrency(Currency.valueOf(currency));
            updateCartPrice(cart);
            cartRepository.save(cart);

            return new ResponseEntity<>(cart, OK);

        } catch (Exception e) {
            // we can do better...by sending the reason the API failed!
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    private void updateCartPrice(Cart cart) {
        BigDecimal price = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItems()) {
            price  =  price.add(cartItem.getItem().getPrice().multiply(BigDecimal.valueOf(cart.getCurrency().getRate())));
        }
        cart.setTotalPrice(price);
    }

    /**
     * GET - Fetch store and data in it i.e. items, currency
     * @return
     */
    @RequestMapping("/cart/{guestId}")
    public ResponseEntity<Cart> getCartForGuest(@PathVariable String guestId) {

        try {
            Guest guest = guestRepository.findOne(guestId);
            Cart cart = cartRepository.findCartForGuest(guest);
            return new ResponseEntity<>(cart, OK);

        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
}
