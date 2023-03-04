package com.bakery.dao;

import com.bakery.model.Cart;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCartDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCartDao() {
        this.jdbcTemplate = new JdbcTemplate();
    }

    // Retrieve a customers cart based on their ID
    public Cart getCustomerCart(long id) {
        
        Cart returnedCart = null;

        String sql = "SELECT ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        if (rowSet.next()) {
            returnedCart = mapRowToCart(rowSet);
        }

        return returnedCart;
    }

    public Cart mapRowToCart(SqlRowSet rowSet) {
        Cart mappedCart = null;

        mappedCart.setCartId(rowSet.getLong(""));

        return mappedCart;
    }

}
