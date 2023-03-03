package com.bakery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

public class Customer {
    @JsonProperty("id")
    private Long userId;

    @JsonProperty("first_name")
    @NotEmpty(message = "First name must be entered")
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty(message = "Last name must be entered")
    private String lastName;

    @JsonProperty("email_address")
    @NotEmpty(message = "Email name must be entered")
    private String email;

    @JsonProperty("customer_cart")
    private Cart customerCart;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cart getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(Cart customerCart) {
        this.customerCart = customerCart;
    }
}
