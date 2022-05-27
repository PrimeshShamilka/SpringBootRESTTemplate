package com.example.demo.repository.util;

import com.example.demo.constant.DBColConstants;
import com.example.demo.model.User;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setUserName(rs.getString(DBColConstants.USER_COL_NAME));
        user.setUserAge(Integer.parseInt(rs.getString(DBColConstants.USER_COL_AGE)));
        return user;
    }
}
