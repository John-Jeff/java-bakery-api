package com.bakery.dao;

import com.bakery.model.Item;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcItemDao implements ItemDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Item> getCartItems(long cartId) {

        List<Item> returnedItemList = new ArrayList<>();

        String sql = "SELECT i.id, i.name FROM item i " +
                "JOIN cart_item ci " +
                "ON i.id = ci.item_id " +
                "WHERE ci.cart_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, cartId);

        while (rowSet.next()) {
            Item returnedItem = mapRowToItem(rowSet);
            returnedItemList.add(returnedItem);
        }

        return returnedItemList;
    }

    public Item mapRowToItem(SqlRowSet rowSet) {
        Item mappedItem = new Item();

        mappedItem.setItemId(rowSet.getLong("id"));
        mappedItem.setName(rowSet.getString("name"));

        return mappedItem;
    }

}
