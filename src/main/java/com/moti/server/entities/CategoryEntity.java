package com.moti.server.entities;

import com.moti.server.enums.CategoryName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoryName", nullable = false, unique = true)
    private CategoryName categoryName;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category")
    List<CouponEntity> coupons;

    public CategoryEntity(long id, CategoryName categoryName, List<CouponEntity> coupons) {
        this.id = id;
        this.categoryName = categoryName;
        this.coupons = coupons;
    }

    public CategoryEntity() {
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

    public List<CouponEntity> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponEntity> coupons) {
        this.coupons = coupons;
    }
}
