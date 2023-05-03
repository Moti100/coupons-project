package com.moti.server.entities;

import com.moti.server.logic.CouponLogic;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.TimerTask;

@Entity
@Table(name = "coupons")
public class CouponEntity {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @Column(name = "date_end", nullable = false)
    private Date dateEnd;
    @Column(name = "amount", nullable = false)
    private long amount;
    @Column(name = "price", nullable = false)
    private long price;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private CompanyEntity company;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "coupon")
    private List<PurchaseEntity> listOfPurchases;

    public CouponEntity(long id, CategoryEntity category, String title, String description, Date startDate, Date dateEnd, long amount, long price, CompanyEntity company, List<PurchaseEntity> listOfPurchases) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dateEnd = dateEnd;
        this.amount = amount;
        this.price = price;
        this.company = company;
        this.listOfPurchases = listOfPurchases;
    }

    public CouponEntity() {
    }
/*
    @Override
    public void run() {
        try {
            while (true) {
                couponLogic.removeExpiredCouponsFromDB();
                System.out.println("Running remove expired coupons");
                Thread.sleep(8399000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
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

    public List<PurchaseEntity> getListOfPurchases() {
        return listOfPurchases;
    }

    public void setListOfPurchases(List<PurchaseEntity> listOfPurchases) {
        this.listOfPurchases = listOfPurchases;
    }
}
