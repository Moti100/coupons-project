package com.moti.server.dto;

import java.sql.Date;

public class PurchaseDto {
    private long id;
    private long amount;
    private long customerId;
    private long couponId;
    private long totalCost;
    private long companyId;
    private Date purchaseDate;
    /*Coupon Cost*/

    public PurchaseDto(long id, long amount, long customerId, long couponId, long totalCost, long companyId, Date purchaseDate) {
        this.id = id;
        this.amount = amount;
        this.customerId = customerId;
        this.couponId = couponId;
        this.totalCost = totalCost;
        this.companyId = companyId;
        this.purchaseDate = purchaseDate;
    }

    public PurchaseDto(long id, long amount,long totalCost, Date purchaseDate) {
        this.id = id;
        this.amount = amount;
        this.totalCost = totalCost;
        this.purchaseDate = purchaseDate;
    }


    public PurchaseDto(long id, long amount,long totalCost) {
        this.id = id;
        this.amount = amount;
        this.totalCost = totalCost;
    }
    public PurchaseDto() {

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

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "PurchaseDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", customerId=" + customerId +
                ", couponId=" + couponId +
                ", totalCost=" + totalCost +
                ", companyId=" + companyId +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
