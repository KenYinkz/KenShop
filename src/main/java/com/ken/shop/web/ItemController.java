package com.ken.shop.web;

import com.ken.shop.domain.Item;
import com.ken.shop.domain.ItemRepository;
import com.ken.shop.domain.Store;
import com.ken.shop.domain.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StoreRepository storeRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/items")
    public ResponseEntity<List<Item>> getItems() {
        List<Item> items = (List<Item>)itemRepository.findAll();
        log.info("Item size = " + items.size());

        return new ResponseEntity<>(items, OK);
    }

    @RequestMapping("/store")
    public ResponseEntity<Store> getStore() {
        Store store = storeRepository.findByName("KenShop");

        return new ResponseEntity<>(store, OK);
    }
}
