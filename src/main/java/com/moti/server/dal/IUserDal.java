package com.moti.server.dal;

import com.moti.server.dto.LoginDto;
import com.moti.server.dto.SuccessfulLoginDto;
import com.moti.server.entities.UserEntity;
import com.moti.server.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDal extends CrudRepository<UserEntity, Long> {

    @Query("SELECT NEW com.moti.server.dto.UserDto (us.id, us.userName,us.userType,us.company.id)  FROM " +
            "UserEntity us")
    List<UserDto> findAllUsers();


    @Query("SELECT NEW com.moti.server.dto.UserDto (us.id, us.userName,us.userType,us.company.id)  FROM " +
            "UserEntity us")
    List<UserDto> findAllUsers(Pageable paging);

    @Query("SELECT NEW com.moti.server.dto.UserDto (us.id, us.userName,us.userType,us.company.id)  FROM " +
            "UserEntity us " +
            "WHERE us.id= :id")
    UserDto findUserById(@Param("id") long id);
/*
    @Query("SELECT NEW com.moti.server.dto.SuccessfulLoginDto(us.userName,us.userType)  FROM " +
            "UserEntity us " +
            "WHERE us.userName= :userName AND us.password= :password")
    SuccessfulLoginDto findLoginData(String userName, String password);
    */


    @Query("SELECT NEW com.moti.server.dto.SuccessfulLoginDto(us.id,us.userType,us.company.id)  FROM " +
            "UserEntity us " +
            "WHERE us.userName= :userName AND us.password= :password")
    SuccessfulLoginDto findLoginUser(@Param ("userName") String userName,@Param("password")  String password);
}

