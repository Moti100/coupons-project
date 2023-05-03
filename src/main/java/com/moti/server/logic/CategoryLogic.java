package com.moti.server.logic;

import com.moti.server.dal.ICategoryDal;
import com.moti.server.dto.CategoryDto;
import com.moti.server.dto.CouponDto;
import com.moti.server.entities.CategoryEntity;
import com.moti.server.enums.CategoryName;
import com.moti.server.enums.ErrorType;
import com.moti.server.exceptions.ServerException;
import com.moti.server.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryLogic {
    public List<CategoryDto> getAllCategories;
    //Initialize parameters
    private ICategoryDal iCategoryDal;
    private CouponLogic couponLogic;

    @Autowired
    public CategoryLogic(ICategoryDal iCategoryDal, CouponLogic couponLogic) {
        this.iCategoryDal = iCategoryDal;
        this.couponLogic = couponLogic;
    }

    /***********************************************************************************
     Crud and more operational  functions
     ***********************************************************************************/
    //This function validate and create new Category in DB

    public void createCategory(CategoryEntity category) throws Exception {
        validateCategory(category);
        iCategoryDal.save(category);
    }

    public void updateCategory(CategoryEntity category) throws Exception {
        Validation.validateId(category.getId());
        validateCategory(category);
        iCategoryDal.save(category);
    }

    public void removeCategory(long id) throws Exception {
        Validation.validateId(id);
        iCategoryDal.deleteById(id);;
    }


    private void validateCategoryIdForDelete(int id) throws Exception {
        validateCategoryId(id);
        validateIfCategoryIsInNonExpiredCoupons(id);
    }

    //Checking if the category id is not in Non expired coupon
    /*-
    Possible to implements also via direct joined SQL queries in dal- if returns null -so OK
   I will change ir if I will have time
    */





    /***********************************************************************************
                                        Validations
     ***********************************************************************************/
    private void validateIfCategoryIsInNonExpiredCoupons(long categoryName) throws Exception {
        List<CouponDto> listOfCouponsNotExpired = couponLogic.getCouponsNotExpiredList();
        int i = 0;
        while (i < listOfCouponsNotExpired.size()) {
            if (listOfCouponsNotExpired.get(i).getCategoryName().equals(categoryName)) {
                throw new ServerException(ErrorType.DELETION_ERROR, "Can not not delete a category if it is in non-expired coupons");
            }
            i++;
        }
    }


    //Validation before creating new Category
    private void  validateCategory(CategoryEntity category) throws Exception {
        //Checking if name exists
        if (category.getCategoryName() == null) {
            throw new ServerException(ErrorType.CATEGORY_CREATION_ISSUE, "Name of Category does not exists");
        }
        //Checking if new Category already exists in DB
        //Possible to use the unique option in DB
        validateIfCategoryInDb((CategoryName) category.getCategoryName());
    }



    //This function checks if the category is already in DB
    /* The mySQL engine checks it when trying to update (UNIQUE)
    but this check prevents an error direct from the Dal
     */

    private void validateIfCategoryInDb(CategoryName categoryName) throws Exception {
        List<CategoryDto> categoryInDb = iCategoryDal.getAllCategories();
        int i = 0;
        while (i < categoryInDb.size()) {
            if (categoryInDb.get(i).getCategoryName().equals(categoryName)) {
                throw new ServerException(ErrorType.CATEGORY_CREATION_ISSUE, "The value already exists in DB");
            }
            i++;
        }
    }


    //This function validates that the id  is valid-not 0 or minus
    //If the id does not exist in DB - error will come from MySQL engine/Dal
    private void validateCategoryId(int id) throws Exception {
        if (id < 0) {
            throw new ServerException(ErrorType.GENERAL_ERROR, "Id is wrong");
        }
    }

    //Checking the pagination number
    private void validateCategoryPageNumber(int pageNumber) throws Exception {
        if (pageNumber < 1) {
            throw new ServerException(ErrorType.CATEGORY_CREATION_ISSUE, "Page number must be positive number");
        }
    }

/*
    //THis function gets all categories in DB with pagination
    public List<Category> getCategoriesFromDb(int pageNumber) throws Exception {
        validateCategoryPageNumber(pageNumber);
        List<Category> categoryInDb = iCategoryDal.getCategoriesFromDB(pageNumber);
        return categoryInDb;
    }

    //Removing category by ID
    //But: can not remove category if id exist in NOT EXPIRED Coupons-check in validation.
    public void removeCategoryById(int id) throws Exception {
        validateCategoryIdForDelete(id);
        iCategoryDal.removeCategoryById(id);
    }

    public void updateCategoryNameById(int id, Enum<CategoryName> categoryName) throws Exception {
        validateCategoryId(id);
        validateIfCategoryInDb((CategoryName) categoryName);
        iCategoryDal.updateCategoryDetailsById(id, categoryName);
    }


    /***********************************************************
     Validations functions
     ************************************************************/
    //This function validates the id and if category is in use for non-expired coupons
    //Modularity - this is an executive function that calls 2 separate functions


}





