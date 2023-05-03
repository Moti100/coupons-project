package com.moti.server.dto;

import com.moti.server.entities.CompanyEntity;
import com.moti.server.enums.CategoryName;

import java.sql.Date;


public class CouponDto {
    private long id;
    private CategoryName categoryName;
    private String title;
    private String description;
    private Date startDate;
    private Date dateEnd;
    private long amount;
    private long price;
    private CompanyEntity company;
    private String CompanyName;

    public CouponDto() {
    }

    public CouponDto(long id, CategoryName categoryName, String title, String description, Date startDate, Date dateEnd, long amount, long price, CompanyEntity company, String companyName) {
        this.id = id;
        this.categoryName = categoryName;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dateEnd = dateEnd;
        this.amount = amount;
        this.price = price;
        this.company = company;
        CompanyName = companyName;
    }


    public CouponDto(CategoryName categoryName, String title, String description, Date startDate, Date dateEnd, long amount, long price, CompanyEntity company, String companyName) {
        this.categoryName = categoryName;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dateEnd = dateEnd;
        this.amount = amount;
        this.price = price;
        this.company = company;
        CompanyName = companyName;
    }

    public CouponDto(long id,  long price) {
        this.id = id;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    @Override
    public String toString() {
        return "CouponDto{" +
                "id=" + id +
                ", categoryName=" + categoryName +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", dateEnd=" + dateEnd +
                ", amount=" + amount +
                ", price=" + price +
                ", company=" + company +
                ", CompanyName='" + CompanyName + '\'' +
                '}';
    }
}
