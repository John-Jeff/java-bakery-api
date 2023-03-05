package com.bakery.dao;

import com.bakery.model.Item;

import java.util.List;

public interface ItemDao {

    public Item getItemById(long itemId);

    public List<Item> getAllItems();

    public List<Item> getCartItems(long cartId);

    public Item createItem(Item newItem);

}
