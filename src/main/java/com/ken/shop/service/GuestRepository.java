package com.ken.shop.service;

import com.ken.shop.domain.Guest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
public interface GuestRepository extends CrudRepository<Guest, String> {
    Guest findByName(String name);
}
