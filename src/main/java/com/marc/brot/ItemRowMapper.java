package com.marc.brot;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item>{

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {

        Item item = new Item();
        item.setItemid(rs.getLong("itemid"));
        item.setItemname(rs.getString("itemname"));

        return item;

    }
}
