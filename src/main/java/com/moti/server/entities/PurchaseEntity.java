package com.moti.server.entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "amount", nullable = false)
    private long amount;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "couponId")
    private CouponEntity coupon;

    @Column(name = "total_cost", nullable = false)
    private long totalCost;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private CompanyEntity company;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    public PurchaseEntity(long id, long amount, CustomerEntity customer, CouponEntity coupon,  CompanyEntity company) {
        this.id = id;
        this.amount = amount;
        this.customer = customer;
        this.coupon = coupon;
        this.company = company;
        this.purchaseDate = Date.valueOf(LocalDate.now());;
    }


    public PurchaseEntity() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
