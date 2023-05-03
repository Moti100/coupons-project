package com.moti.server.controller;


import com.moti.server.dto.LoginDto;
import com.moti.server.dto.SuccessfulLoginDto;
import com.moti.server.entities.UserEntity;
import com.moti.server.enums.ErrorType;
import com.moti.server.exception.ApplicationException;
import com.moti.server.logic.UserLogic;
import com.moti.server.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    UserLogic userLogic;
    CompaniesController companiesController;

    @Autowired
    public UsersController(UserLogic userLogic) {
        this.userLogic = userLogic;
    }


    @PostMapping
    public void createUser(@RequestBody UserEntity userEntity) throws Exception {
        badTransactionRollbackExample(userEntity);
        //userLogic.createUser(userEntity);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") long id) throws Exception {
        userLogic.deleteUser(id);
    }

    @PutMapping
    public void updateUser(@RequestBody UserEntity userEntity) throws Exception {
        userLogic.updateUser(userEntity);
    }

    @GetMapping
    public List<UserDto> getAllUsers() throws Exception {
        List<UserDto> listOfUsers = userLogic.getAllDataOfUsers();
        return listOfUsers;
    }

    @GetMapping("/byPage")
    public List<UserDto> getAllUsers(long pageNumber) throws Exception {
        List<UserDto> listOfUsers = userLogic.getAllDataOfUsers(pageNumber);
        return listOfUsers;
    }

    @PostMapping("/login")
    public String loginVerification(@RequestBody LoginDto loginDto) throws Exception {
        String userTokenData = userLogic.login(loginDto);
        return userTokenData;
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable("id") long id) throws Exception {
        UserDto userDto = userLogic.getUserById(id);
        return userDto;
    }

    @Transactional(rollbackOn = {ApplicationException.class})
    public void badTransactionRollbackExample(UserEntity user) throws Exception {
        UserEntity user1 = user;
        UserEntity user2 = user;
        try {
            userLogic.createUser(user1);
            userLogic.createUser(user2);
        } catch (Exception e) {
            throw new ApplicationException("Error creating new user", e, ErrorType.CREATION_ERROR);
        }
    }
}
