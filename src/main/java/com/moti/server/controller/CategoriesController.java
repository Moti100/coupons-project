package com.moti.server.controller;

import com.moti.server.dto.CategoryDto;
import com.moti.server.entities.CategoryEntity;
import com.moti.server.logic.CategoryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private CategoryLogic categoryLogic;

    @Autowired
    public CategoriesController(CategoryLogic categoryLogic) {
        this.categoryLogic = categoryLogic;
    }

    //Creating new Category
    @PostMapping
    public void createCategory(@RequestBody CategoryEntity category) throws Exception {
        categoryLogic.createCategory(category);
    }

    //Update Category
    @PutMapping
    public void updateCategory(@RequestBody CategoryEntity category) throws Exception {
        categoryLogic.updateCategory(category);
    }


    //Delete Company
    @DeleteMapping("{categoryId}")
    public void deleteCompany(@PathVariable("categoryId") long id) throws Exception {
        categoryLogic.removeCategory(id);
    }

    //Get all companies
    @GetMapping
    public List<CategoryDto> getAllCategories() throws Exception {
        List<CategoryDto> listOfCategories = categoryLogic.getAllCategories;
        return listOfCategories;
    }
}



