package com.bakery.dao;

import com.bakery.model.Item;

import java.util.List;

public interface ItemDao {

    public List<Item> getCartItems(long cartId);

}
