package com.ken.shop.domain;

import org.springframework.data.repository.CrudRepository;


/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
public interface ItemRepository extends CrudRepository<Item, String> {

    Item findByName(String name);

}
