package com.example.demo.repository;

import com.example.demo.constant.SQLConstants;
import com.example.demo.model.User;
import com.example.demo.repository.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQLConstants.SELECT_USER_FROM_ID, new UserRowMapper(), id);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update(SQLConstants.INSERT_USER, new Object[] { user.getUserName(), user.getUserAge() });
    }

    @Override
    public int update(User user, long id) {
        return jdbcTemplate.update(SQLConstants.UPDATE_USER, new Object[] { user.getUserName() , user.getUserAge(), id});
    }

    @Override
    public List<User> findAll(){
        return jdbcTemplate.query(SQLConstants.FIND_ALL_USERS, new UserRowMapper());
    }

    @Override
    public int delete(Long id){
        return jdbcTemplate.update(SQLConstants.DELETE_USER_FROM_ID, id);
    }

}
