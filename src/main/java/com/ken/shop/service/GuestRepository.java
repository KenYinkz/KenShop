package com.ken.shop.service;

import com.ken.shop.domain.Guest;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by adeyinkaokuneye on 24/11/2015.
 */
public interface GuestRepository extends CrudRepository<Guest, String> {

}
