package com.ken.shop.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adeyinkaokuneye on 23/11/2015.
 */
@RestController
public class ItemController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
