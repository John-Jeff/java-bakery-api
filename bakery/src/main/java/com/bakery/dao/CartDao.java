package com.bakery.dao;

import com.bakery.model.Cart;

public interface CartDao {

    public Cart getCustomerCart(long id);

}
