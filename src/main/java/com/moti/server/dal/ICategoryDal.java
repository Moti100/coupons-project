package com.moti.server.dal;

import com.moti.server.dto.CategoryDto;
import com.moti.server.entities.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryDal extends CrudRepository<CategoryEntity,Long> {



    @Query("SELECT NEW com.moti.server.dto.CategoryDto (cat.id,cat.categoryName)  FROM " +
            "CategoryEntity cat " +
            "WHERE cat.id= :id")
    List<CategoryDto> getAllCategories();
}
