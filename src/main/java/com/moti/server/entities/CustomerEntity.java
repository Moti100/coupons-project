package com.moti.server.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @Column(name="id",nullable = false)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private UserEntity user;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "amount_of_Kids")
    private long amountOfKids;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
    private List<PurchaseEntity> listOfpurchases;

    public CustomerEntity(long id, UserEntity user, String address, String phone, long amountOfKids) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.phone = phone;
        this.amountOfKids = amountOfKids;
    }

    public CustomerEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
        return "CustomerEntity{" +
                "id=" + id +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", amountOfKids=" + amountOfKids +
                ", listOfpurchases=" + listOfpurchases +
                '}';
    }
}
