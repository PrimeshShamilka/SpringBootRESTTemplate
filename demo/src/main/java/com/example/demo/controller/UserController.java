package com.example.demo.controller;

import com.example.demo.exception.CommonException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NotFoundException notFoundException){
            LOGGER.error(notFoundException.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(" User created successfully.", HttpStatus.OK);
        }catch (CommonException commonException){
            LOGGER.error(commonException.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        try{
            userService.updateUser(id, user);
            return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
        }catch (NotFoundException notFoundException){
            LOGGER.error(notFoundException.getMessage());
            return new ResponseEntity<>("Cannot find user with id=" + id, HttpStatus.NOT_FOUND);
        }
        catch (CommonException commonException){
            LOGGER.error(commonException.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } catch (NotFoundException notFoundException){
            LOGGER.error(notFoundException.getMessage());
            return new ResponseEntity<>("Cannot find user with id=" + id, HttpStatus.OK);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>("Cannot delete user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
