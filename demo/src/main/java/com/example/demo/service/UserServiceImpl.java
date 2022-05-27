package com.example.demo.service;

import com.example.demo.exception.CommonException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.constant.ErrorConstants.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    private final MessageUtils messageUtils;

    public UserServiceImpl(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<User>();
        userRepository.findAll().forEach(userList::add);
        if (userList.isEmpty()){
            throw new NotFoundException(messageUtils.getErrorField(ERROR_2102).getMessage(), messageUtils.getErrorField(ERROR_2102).getCode());
        }
        else{
            return userList;
        }
    }

    @Override
    public int saveUser(User user){
        try {
            int result = userRepository.save(user);
            return result;
        } catch (DataAccessException dataAccessException){
            throw new CommonException(messageUtils.getErrorField(ERROR_2103).getMessage(), messageUtils.getErrorField(ERROR_2103).getCode(), DB_ERROR_TYPE, null);
        }
    }

    @Override
    public int updateUser(long id, User newUser){
        User user = userRepository.findById(id);
        if (user == null){
            throw new NotFoundException(messageUtils.getErrorField(ERROR_2104).getMessage(),messageUtils.getErrorField(ERROR_2104).getCode());
        }else{
            user.setUserName(newUser.getUserName());
            user.setUserAge(newUser.getUserAge());
            try{
                int result = userRepository.update(user, id);
                return result;
            }catch (DataAccessException dataAccessException){
                throw new CommonException(messageUtils.getErrorField(ERROR_2105).getMessage(), messageUtils.getErrorField(ERROR_2105).getCode(), DB_ERROR_TYPE, null);
            }
        }
    }

    @Override
    public int deleteUser(long id){
        try{
            int result = userRepository.delete(id);
            if (result == 0){
                throw new NotFoundException(messageUtils.getErrorField(ERROR_2104).getMessage(),messageUtils.getErrorField(ERROR_2104).getCode());
            }else{
                return result;
            }
        }catch (DataAccessException dataAccessException){
            throw new CommonException(messageUtils.getErrorField(ERROR_2105).getMessage(), messageUtils.getErrorField(ERROR_2105).getCode(), DB_ERROR_TYPE, null);
        }
    }

}
