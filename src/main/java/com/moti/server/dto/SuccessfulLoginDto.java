package com.moti.server.dto;

import com.moti.server.enums.UserType;

public class SuccessfulLoginDto {

    private long id;
    private UserType userType;
    private Long companyId;

    public SuccessfulLoginDto(long id, UserType userType, Long companyId) {
        this.id = id;
        this.userType = userType;
        this.companyId = companyId;
    }


    public SuccessfulLoginDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    @Override
    public String toString() {
        return "SuccessfulLoginDto{" +
                "id=" + id +
                ", userType=" + userType +
                ", companyId=" + companyId +
                '}';
    }
}
