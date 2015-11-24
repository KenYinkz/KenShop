package com.ken.shop.service;

import com.ken.shop.domain.Item;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
public interface ItemRepository extends CrudRepository<Item, String> {

    Item findByName(String name);

}
