package com.bakery.dao;

import com.bakery.model.Cart;

import com.bakery.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartDao implements CartDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCartDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Retrieve a customers cart based on their ID
    public Cart getCustomerCart(long id) {

        Cart returnedCart = null;

        // Get cart from DB based on customer
        String sql = "SELECT id FROM cart WHERE customer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        // Get cart entry and map to Cart object
        if (rowSet.next()) {
            returnedCart = mapRowToCart(rowSet);
        }

        sql = "SELECT i.id, i.name FROM item i " +
                "JOIN cart_item ci " +
                "ON i.id = ci.item_id " +
                "WHERE ci.cart_id = ?";
        rowSet = jdbcTemplate.queryForRowSet(sql, returnedCart.getCartId());

        List<Item> returnedCartItems = new ArrayList<>();
        while (rowSet.next()) {
            Item returnedItem = new JdbcItemDao().mapRowToItem(rowSet);
            returnedCartItems.add(returnedItem);
        }

        returnedCart.setItems(returnedCartItems);
        return returnedCart;
    }

    public Cart mapRowToCart(SqlRowSet rowSet) {
        Cart mappedCart = new Cart();

        mappedCart.setCartId(rowSet.getLong("id"));
        mappedCart.setItems(null);

        return mappedCart;
    }

}
