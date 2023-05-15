package com.marc.brot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@RestController
public class InventoryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
    @GetMapping("/items")
    public ResponseEntity<Object> ListItems() {

        String sql = "SELECT itemid, itemname FROM items;";

        List<Item> items = jdbcTemplate.query(sql, new ItemRowMapper());

        return new ResponseEntity<Object>(items, HttpStatus.OK);

    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
    @GetMapping("/users")
    public ResponseEntity<Object> ListUsers() {

        String sql = "SELECT userid, username, useremail FROM users;";

        List<User> users = jdbcTemplate.query(sql, new UserRowMapper());

        return new ResponseEntity<Object>(users, HttpStatus.OK);

    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
    @GetMapping("/stocks")
    public ResponseEntity<Object> ListStocks() {

        String sql = "SELECT userid, itemid, quantity, unit FROM stocks;";

        List<Stock> stocks = jdbcTemplate.query(sql, new StockRowMapper());

        return new ResponseEntity<Object>(stocks, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
    @PostMapping("/stocks")
    public ResponseEntity<Object> ListStocksForUser(@RequestParam(name = "useremail") String useremail) {

        ObjectMapper objectMapper = new ObjectMapper();

        String userSql = "SELECT userid, username, useremail FROM users WHERE useremail = ?";
        String stockSql = "SELECT s.stockid, s.itemname, s.unit, s.quantity AS quantity FROM stocks s WHERE userid = ?;";

        User user = jdbcTemplate.queryForObject(userSql, new Object[]{useremail}, new UserRowMapper());
        List<Map<String, Object>> stocks = jdbcTemplate.queryForList(stockSql, new Object[] { user.userid });

        return new ResponseEntity<Object>(stocks, HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
    @PostMapping("/stock/delete")
    public ResponseEntity<Object> DeleteStock(@RequestBody Map<String, String> stockMap) {

        String deleteStockSql = "DELETE FROM stocks WHERE stockid = ?";
        int[] types = {Types.INTEGER};

        jdbcTemplate.update(deleteStockSql, new Object[] { stockMap.get("stockid") }, types);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
    @PostMapping("/stock/insert")
    public ResponseEntity<Object> ListStocksForUser(@RequestBody Map<String, String> stockMap) {

        String insertStockSql = "INSERT INTO stocks (itemname, userid, quantity, unit) VALUES (?, ?, ?, ?)";
        int[] types = {Types.VARCHAR, Types.INTEGER, Types.DECIMAL, Types.VARCHAR};

        jdbcTemplate.update(insertStockSql, new Object[] { stockMap.get("itemname"),  stockMap.get("userid"), stockMap.get("quantity"), stockMap.get("unit")}, types);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}