package com.bakery.dao;

import com.bakery.model.Item;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcItemDao {

    public Item mapRowToItem(SqlRowSet rowSet) {
        Item mappedItem = new Item();

        mappedItem.setItemId(rowSet.getLong("id"));
        mappedItem.setName(rowSet.getString("name"));

        return mappedItem;
    }

}
