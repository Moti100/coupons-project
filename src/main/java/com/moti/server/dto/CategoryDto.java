package com.moti.server.dto;

import com.moti.server.entities.CouponEntity;
import com.moti.server.enums.CategoryName;

import java.util.List;

public class CategoryDto {
    private long id;
    private CategoryName categoryName;

    public CategoryDto(long id, CategoryName categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public CategoryDto() {
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



    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", categoryName=" + categoryName +
                '}';
    }
}
