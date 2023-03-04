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

    private ItemDao itemDao;

    public JdbcCartDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.itemDao = new JdbcItemDao(dataSource);

    }

    // Retrieve a customers cart based on their ID
    public Cart getCustomerCart(long id) {
        //TODO: Add exception handlers
        Cart returnedCart = null;

        // Get cart from DB based on customer
        String sql = "SELECT id FROM cart WHERE customer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        // Get cart entry and map to Cart object
        if (rowSet.next()) {
            returnedCart = mapRowToCart(rowSet);
        }

        returnedCart.setItems(itemDao.getCartItems(returnedCart.getCartId()));
        return returnedCart;
    }

    public Cart mapRowToCart(SqlRowSet rowSet) {
        Cart mappedCart = new Cart();

        mappedCart.setCartId(rowSet.getLong("id"));
        mappedCart.setItems(null);

        return mappedCart;
    }

}
