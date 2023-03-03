package com.bakery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class Item {

    @JsonProperty("id")
    private Long itemId;

    @JsonProperty("name")
    @NotEmpty(message = "Item must have a name")
    private String name;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
