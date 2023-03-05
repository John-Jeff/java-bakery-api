package com.bakery.dao;

import com.bakery.model.Item;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

//TODO: Add exception handlers

@Component
public class JdbcItemDao implements ItemDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Item getItemById(long itemId) {

        Item returnedItem = new Item();

        String sql = "SELECT id, name " +
                "FROM item " +
                "WHERE id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, itemId);

        if (rowSet.next()) {
            returnedItem = mapRowToItem(rowSet);
        }
        return returnedItem;
    }
    @Override
    public List<Item> getAllItems() {

        List<Item> returnedItemList = new ArrayList<>();

        String sql = "SELECT id, name " +
                "FROM item;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            Item returnedItem = mapRowToItem(rowSet);
            returnedItemList.add(returnedItem);
        }
        return returnedItemList;
    }
    @Override
    public List<Item> getCartItems(long cartId) {

        List<Item> returnedItemList = new ArrayList<>();

        String sql = "SELECT i.id, i.name FROM item i " +
                "JOIN cart_item ci " +
                "ON i.id = ci.item_id " +
                "WHERE ci.cart_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, cartId);

        while (rowSet.next()) {
            Item returnedItem = mapRowToItem(rowSet);
            returnedItemList.add(returnedItem);
        }
        return returnedItemList;
    }

    @Override
    public Item createItem(Item newItem) {

        String sql = "INSERT INTO item (name) " +
                "VALUES (?) RETURNING id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newItem.getName());

        return getItemById(newId);
    }

    public Item mapRowToItem(SqlRowSet rowSet) {
        Item mappedItem = new Item();

        mappedItem.setItemId(rowSet.getLong("id"));
        mappedItem.setName(rowSet.getString("name"));

        return mappedItem;
    }

}
