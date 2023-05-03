package com.moti.server.dto;

import com.moti.server.enums.UserType;

public class UserDto {
    private long id;
    private String userName;
    private UserType userType;
    private long companyId;



    public UserDto(long id, String userName, UserType userType, long companyId) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
        this.companyId = companyId;
    }

    public UserDto(long id, String userName, long companyId) {
        this.id = id;
        this.userName = userName;
        this.companyId = companyId;
    }


    public UserDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", companyId=" + companyId +
                '}';
    }
}
