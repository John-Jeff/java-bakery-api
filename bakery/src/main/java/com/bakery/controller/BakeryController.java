package com.bakery.controller;

import com.bakery.dao.*;
import com.bakery.model.Item;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/bakery")
public class BakeryController {

    private CustomerDao customerDao;
    private CartDao cartDao;
    private ItemDao itemDao;

    public BakeryController(CustomerDao customerDao, CartDao cartDao, ItemDao itemDao) {
        this.customerDao = customerDao;
        this.cartDao = cartDao;
        this.itemDao = itemDao;
    }

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public List<Item> getItems() {
        return itemDao.getAllItems();
    }

}
