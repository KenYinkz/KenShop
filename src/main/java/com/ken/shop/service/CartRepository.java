package com.ken.shop.service;

import com.ken.shop.domain.Cart;
import com.ken.shop.domain.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
public interface CartRepository extends CrudRepository<Cart, String>{

    @Query("select c from #{#entityName} c where c.guest = ?1")
    Cart findCartForGuest(Guest guest);

}
