package com.ken.shop.service;

import com.ken.shop.domain.CartItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
public interface CartItemRepository extends CrudRepository<CartItem, String> {

}
