package com.moti.server.logic;


import com.moti.server.dal.ICouponDal;

import com.moti.server.dto.CompanyDto;
import com.moti.server.dto.CouponDto;
import com.moti.server.entities.CouponEntity;
import com.moti.server.enums.ErrorType;

import com.moti.server.exceptions.ServerException;
import com.moti.server.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;


import static com.moti.server.constans.Consts.PAGENIATION_FOR_CUSTOMER;

@Service
public class CouponLogic {
    private ICouponDal iCouponDal;
    private Calendar date = Calendar.getInstance();
    @Autowired
    private CompanyLogic companyLogic;

    @Autowired
    public CouponLogic(ICouponDal iCouponDal) {
        this.iCouponDal = iCouponDal;
    }

    /***********************************************************************************
     Crud and more operational  functions
     ***********************************************************************************/
/*
    //For delete of expired Coupons
    public void scheduleDeleteExpiredCouponsFromDb() {
        Timer timer = new Timer();
        //For future use with ScheduledExecutorService
        CouponEntity coupon = new CouponEntity();
        date.set(Calendar.HOUR_OF_DAY, 00);
        date.set(Calendar.MINUTE, 00);
        date.set(Calendar.SECOND, 00);
        try {
            //   timer.schedule(coupon, date.getTime());
            timer.schedule(coupon, 100);

        } catch (Exception e) {
            //Swallowing the exception - not to stop the process
            System.out.println(e);
            e.printStackTrace();
        }
    }
    */


    //This function creates new coupon in DB
    public long createCoupon(CouponEntity coupon) throws Exception {
        validateCoupon(coupon);
        iCouponDal.save(coupon);
        return coupon.getId();
    }

    //remove coupon by id
    public void removeCoupon(long id) throws Exception {
        Validation.validateId(id);
        ;
        iCouponDal.deleteById(id);
    }

    //Update coupon data by id
    public void updateCoupon(CouponEntity coupon) throws Exception {
        Validation.validateId(coupon.getId());
        validateCoupon(coupon);
        iCouponDal.save(coupon);
    }


    public List<CouponDto> getAllCoupons(long pageNumber) throws Exception {
        Validation.validatePageNumber(pageNumber);
        try {
            Pageable paging = PageRequest.of((int) pageNumber, (int) PAGENIATION_FOR_CUSTOMER);
            List<CouponDto> listOfCoupons = iCouponDal.getAllCoupons(paging);
            return listOfCoupons;
        } catch (Exception e) {
            throw new ServerException(ErrorType.Read_Error, "Failed to get coupon list from Db");
        }
    }

    //Getting all coupon data
    public List<CouponDto> getAllCoupons() throws Exception {
        try {
            List<CouponDto> listOfCoupons = iCouponDal.getAllCoupons();
            return listOfCoupons;
        } catch (Exception e) {
            throw new ServerException(ErrorType.Read_Error, "Failed to get coupon list from Db");
        }
    }

    //Getting full data of coupon by id
    public CouponDto getCouponById(long id) throws Exception {
        Validation.validateId(id);
        CouponDto coupon = iCouponDal.getCouponById(id);
        return coupon;
    }

    //Getting all coupon data by data range
    public List<CouponDto> getAllDataOfCouponsByPriceRange(long minPrice, long maxPrice) throws Exception {
        validateMinAndMaxValues(minPrice, maxPrice);
        try {
            List<CouponDto> listOfCouponsByPriceRange = iCouponDal.getAllDataOfCouponsByPriceRange(minPrice, maxPrice);
            return listOfCouponsByPriceRange;
        } catch (Exception e) {
            throw new ServerException(ErrorType.Read_Error, "Failed to get coupon list from Db");
        }
    }

    //This function is checking if a coupon is expired but before deletion-and if so - moving to archive and delete it
    public void removeExpiredCouponsFromDB() throws Exception {
      //  iCouponDal.deleteExpeiredCoupons();
    }

    //This function returns the price of the coupon by id
    public double getCouponPrice(long id) throws Exception {
        Validation.validateId(id);
        double price = iCouponDal.getCouponById(id).getPrice();
        return price;
    }

    //Used by Category logic validation
    List<CouponDto> getCouponsNotExpiredList() throws Exception {
        List<CouponDto> listOfCouponsNotExpired = iCouponDal.getNotExpiredCouponsForCategoryCheck();
        return listOfCouponsNotExpired;
    }


    /***********************************************************
     Private and Validations functions
     ************************************************************/
    //This function is part of the mechanism  of locate an expired coupon and adding them to a list
    private List<CouponDto> getExpiredCoupons() throws Exception {
        try {
            List<CouponDto> expiredCouponsFromDb = iCouponDal.getExpiredCoupons();
            return expiredCouponsFromDb;

        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, "Failed to get coupon list from Db");
        }
    }

    //Validation for price range
    private void validateMinAndMaxValues(long minPrice, long maxPrice) throws ServerException {
        if (minPrice > maxPrice) {
            throw new ServerException(ErrorType.ErrorWithMaxMinRange, "Max price  of coupon can not be smaller then min price");
        }
        if (minPrice < 0 || maxPrice <= 0) {
            throw new ServerException(ErrorType.ErrorWithMaxMinRange, "Values of coupon price are wrong");
        }
    }

    //Default (not public nor private) function used by companyLogic
    List<CouponDto> validateCompanyNotInNotExpiredCoupons(long id) throws Exception {

     //   List<CouponDto> couponsWithCompanyNames = iCouponDal.CompanyInNotExpireCoupons(id);
        return null;
    }

    //Checks if the coupon is indeed expired
    private void validateExpiredDateOfCoupon(Date expiredDate) throws Exception {
        if (expiredDate.after(Date.valueOf(LocalDate.now()))) {
            throw new ServerException(ErrorType.COUPON_IS_NOT_EXPIRED, "This coupon is not expired!, check your code");
        }
    }

    //This function validates the information of the CouponEntity (from creation,for example)
    private void validateCoupon(CouponEntity coupon) throws Exception {
        checkIfCompanyExists(coupon);
        if (coupon.getCompany().getId() <= 0 || coupon.getCompany().getId() > 1000000) {
            throw new Exception("Company id is not valid");
        }
        if (coupon.getCategory().getId() <= 0) {
            throw new Exception("Category Id must  be positive and above 0");
        }
        if (coupon.getTitle().equals(null) || coupon.getTitle().equals("")) {
            throw new Exception("Title can not be null or empty");
        }
        if (coupon.getDescription().equals(null) || coupon.getDescription().equals("")) {
            throw new Exception("Description can not be null or empty");
        }
        if (coupon.getAmount() <= 0) {
            throw new Exception("Amount must  be positive and above 0");
        }
        if (coupon.getPrice() <= 0) {
            throw new Exception("Price must  be positive and above 0");
        }
    }

    //checks if the company exist in DB
    private void checkIfCompanyExists(CouponEntity coupon) throws Exception {
        CompanyDto company = companyLogic.getCompanyById(coupon.getCompany().getId());
        if (company.getId() != coupon.getCompany().getId()) {
            throw new ServerException(ErrorType.COMPANY_DOES_NOT_EXIST, "No such company in DB - open a company and then try to create coupon");
        }
    }

}
