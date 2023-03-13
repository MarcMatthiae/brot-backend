package com.marc.brot;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockRowMapper implements RowMapper<Stock>{

    @Override
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {

        Stock stock = new Stock();
        stock.setUserid(rs.getLong("userid"));
        stock.setItemid(rs.getLong("itemid"));
        stock.setQuantity(rs.getDouble("quantity"));
        stock.setUnit(rs.getString("unit"));

        return stock;

    }
}
