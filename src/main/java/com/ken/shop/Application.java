package com.ken.shop;

import com.ken.shop.domain.Item;
import com.ken.shop.domain.ItemRepository;
import com.ken.shop.domain.Store;
import com.ken.shop.domain.StoreRepository;
import com.ken.shop.domain.helper.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner setupItems(ItemRepository itemRepository, StoreRepository storeRepository) {
        return (args) -> {

            // create a store
            Store store = new Store("KenShop", Currency.DOLLAR);
            storeRepository.save(store);

            // items for the store...
            itemRepository.save(new Item(" MacBook Pro - Apple", BigDecimal.valueOf(1000.0), store));
            itemRepository.save(new Item(" Windows Surface", BigDecimal.valueOf(1200.0), store));

            // fetch all items
            log.info("Items found with findAll():");
            log.info("-------------------------------");
            for (Item item : itemRepository.findAll()) {
                log.info(item.toString());
            }

        };
    }
}