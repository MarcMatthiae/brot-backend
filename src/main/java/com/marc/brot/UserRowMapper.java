package com.marc.brot;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUserid(rs.getLong("userid"));
        user.setUsername(rs.getString("username"));
        user.setUseremail(rs.getString("useremail"));

        return user;

    }
}
