package com.example.demo.constant;

public class SQLConstants {
    public static final String SELECT_USER_FROM_ID = "SELECT * FROM user WHERE id=?";

    public static final String INSERT_USER = "INSERT INTO user (name, age) VALUES(?,?)";

    public static final String UPDATE_USER = "UPDATE user SET name=?, age=? WHERE id=?";

    public static final String FIND_ALL_USERS = "SELECT * from user";

    public static final String DELETE_USER_FROM_ID = "DELETE FROM user WHERE id=?";

}
