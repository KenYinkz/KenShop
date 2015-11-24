package com.ken.shop.service;

import com.ken.shop.domain.Store;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
public interface StoreRepository extends CrudRepository<Store, String> {

    Store findByName(String name);

}
