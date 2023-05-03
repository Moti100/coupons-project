package com.moti.server.logic;


import com.moti.server.dal.IPurchaseDal;
import com.moti.server.entities.PurchaseEntity;
import com.moti.server.enums.ErrorType;
import com.moti.server.enums.UserType;
import com.moti.server.exceptions.ServerException;
import com.moti.server.dto.PurchaseDto;
import com.moti.server.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.moti.server.constans.Consts.PAGENIATION_FOR_PURCHASE;


@Service
public class PurchaseLogic {
    private IPurchaseDal iPurchaseDal;
    private CouponLogic couponLogic;
    private UserLogic userLogic;

    @Autowired
    public PurchaseLogic(IPurchaseDal iPurchaseDal, CouponLogic couponLogic, UserLogic userLogic) {
        this.iPurchaseDal = iPurchaseDal;
        this.couponLogic = couponLogic;
        this.userLogic = userLogic;
    }

    /***********************************************************************************
     Crud and more operational  functions
     ***********************************************************************************/
    //Add Purchase to DB
    //Only by Client user type
    public long createPurchase(PurchaseEntity purchaseEntity) throws Exception {
        //Calculating the cost of the purchase
        purchaseEntity = getTotalCostOfCouponsForPurchase(purchaseEntity);
        //Only after updating data - validation for the purchase
        validatePurchase(purchaseEntity);
        iPurchaseDal.save(purchaseEntity);
        return purchaseEntity.getId();
    }

    //Remove purchase from db
    public void removePurchaseById(long id) throws Exception {
        Validation.validateId(id);
        iPurchaseDal.existsById(id);
        iPurchaseDal.deleteById(id);
    }

    //Getting all data from table-No pagination
    public List<PurchaseDto> getAllPurchasesFromDB() throws Exception {
        List<PurchaseDto> purchasesDataFromDb = iPurchaseDal.getAllPurchasesFromDB();
        return purchasesDataFromDb;
    }

    //Getting all data from table-With pagination
    public List<PurchaseDto> getAllPurchasesFromDB(long pageNumber) throws Exception {
      Validation.validatePageNumber(pageNumber);
        Pageable paging = PageRequest.of((int) pageNumber, (int) PAGENIATION_FOR_PURCHASE);
        List<PurchaseDto> purchasesDataFromDb = iPurchaseDal.getAllPurchasesFromDB(paging);
        return purchasesDataFromDb;
    }

    //This function updates an existing purchase details
    public void updatePurchase(PurchaseEntity purchase) throws Exception {
        Validation.validateId(purchase.getId());
        iPurchaseDal.existsById(purchase.getId());
        validatePurchase(purchase);
        iPurchaseDal.save(purchase);
    }

    //Get purchase
    public PurchaseDto getPurchaseById(long id) throws Exception {
        Validation.validateId(id);
        PurchaseDto purchase = iPurchaseDal.findPurchaseById(id);
        return purchase;
    }

    //Calculating and adding total cost of purchase and coupon cost to purchase
    private PurchaseEntity getTotalCostOfCouponsForPurchase(PurchaseEntity purchase) throws Exception {
        try {
            long totalCost = calculateTotalCost(purchase.getCoupon().getPrice(), purchase.getAmount());
            purchase.setTotalCost(totalCost);
        } catch (Exception e) {
            throw new ServerException(e, ErrorType.GENERAL_ERROR, "calculation failed.Check data: " + purchase.toString());
        }
        return purchase;
    }

    //Calculates the total cost of the purchase
    private long calculateTotalCost(long couponPrice, long amountOfCoupons) throws Exception {
        long totalCost = amountOfCoupons * couponPrice;
        return totalCost;
    }

    /***********************************************************
     Validations functions
     ************************************************************/

    //Validation of  removing purchase from DB
    private void validateUserTypeForRemovePurchaseAction(UserType userType) throws Exception {
        if (userType.equals(UserType.Customer) || userType.equals(UserType.Client)) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "This user type can not perform remove action");
        }
    }

    //Validate user type for purchase
    private void validateUserTypeForPurchaseAction(UserType userType) throws Exception {
        if (userType.equals(UserType.Admin) || userType.equals(UserType.Customer)) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "Only client can purchase coupons");
        }
    }

    //Validate purchase data before creation or update
    private void validatePurchase(PurchaseEntity purchase) throws Exception {
        Validation.validateId(purchase.getCompany().getId());//For company Id
        Validation.validateId(purchase.getCoupon().getId());//For CouponId
        Validation.validateId(purchase.getCustomer().getId()); //For Customer Id
        validateAmountOrCost(purchase.getAmount());
        validateAmountOrCost(purchase.getCoupon().getPrice());
        validateAmountOrCost(purchase.getTotalCost());
        isUserExists(purchase.getCustomer().getId(), UserType.Admin);
    }

    private boolean isUserExists(long customerId, UserType userType) throws Exception {
        boolean isUserExists = userLogic.isUserExists(customerId);
        if (isUserExists) {
            //id of user exist as customer id
            return true;
        } else {
            throw new ServerException(ErrorType.INVALID_USER, "No such user for this id: " + customerId);
        }
    }

    //Checking amount field
    private void validateAmountOrCost(long number) throws Exception {
        if (number <= 0) {
            throw new ServerException(ErrorType.INVALID_PURCHASE_AMOUNT_OR_COST, "Amount or cost in purchase can not be negative or zero");
        }
    }

    private void validateSelectRequest(UserType userType) throws Exception {
        if (userType.equals(UserType.Customer) || userType.equals(UserType.Client)) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "Only admin can get all  data of purchases");
        }
        if (userType.equals(null)) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "User type is empty");
        }
    }

    //Validate that the right User type for update
    private void validateUserForUpdatingPurchase(UserType userType) throws Exception {
        //After the purchase is done, only admin can change data in it
        if (userType.equals(null)) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "User type is empty");
        }
        if (userType.equals(UserType.Customer) || userType.equals(UserType.Client)) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, "Only admin can change data for done purchase");
        }
    }

}
