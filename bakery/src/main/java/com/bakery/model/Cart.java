package com.bakery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cart {

    @JsonProperty("id")
    private Long cartId;

    @JsonProperty("items")
    private List<Item> items;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
