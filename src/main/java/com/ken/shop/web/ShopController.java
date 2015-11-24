package com.ken.shop.web;

import com.ken.shop.domain.*;
import com.ken.shop.service.CartRepository;
import com.ken.shop.service.ItemRepository;
import com.ken.shop.service.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    StoreRepository storeRepository;

    @Autowired
    CartRepository cartRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Ken's Shop";
    }

    /**
     * GET - Fetch list of items
     * @return
     */
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
     * GET - Fetch store and data in it i.e. items, currency
     * @return
     */
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
     * @param cart
     * @return
     */
    @RequestMapping(value = "/cart",
                    method = RequestMethod.POST)
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart){

        try {
            cartRepository.save(cart);
            return new ResponseEntity<>(cart, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

}
