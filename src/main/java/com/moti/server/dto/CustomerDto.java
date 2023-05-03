package com.moti.server.dto;

import com.moti.server.entities.PurchaseEntity;
import com.moti.server.entities.UserEntity;

import java.util.List;

public class CustomerDto {
    private long id;
    private String userName;
    private String address;
    private String phone;
    private long amountOfKids;
    // private List<PurchaseDto> listOfPurchases;


    public CustomerDto(String userName, String address, String phone, long amountOfKids) {
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.amountOfKids = amountOfKids;
    }

    public CustomerDto(long id, String userName, String address, String phone, long amountOfKids) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.amountOfKids = amountOfKids;
    }

    public CustomerDto(long id, String address, String phone, long amountOfKids) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.amountOfKids = amountOfKids;
    }

    public CustomerDto() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getAmountOfKids() {
        return amountOfKids;
    }

    public void setAmountOfKids(long amountOfKids) {
        this.amountOfKids = amountOfKids;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", userName=" + userName +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", amountOfKids=" + amountOfKids +
                '}';
    }
}
