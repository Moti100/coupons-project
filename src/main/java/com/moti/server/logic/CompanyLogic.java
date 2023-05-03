package com.moti.server.logic;

import com.moti.server.dal.ICompanyDal;
import com.moti.server.dto.CompanyDto;
import com.moti.server.dto.CouponDto;
import com.moti.server.entities.CompanyEntity;
import com.moti.server.enums.ErrorType;
import com.moti.server.exceptions.ServerException;
import com.moti.server.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.moti.server.constans.Consts.PAGENIATION_FOR_COMPANY;

@Service
public class CompanyLogic {
    //CompanyDal will be use in several function so the initialization is here
    private ICompanyDal iCompanyDal;
    @Autowired
    private UserLogic userLogic;//For validation in userLogic(prevent double coding)
    @Autowired
    private CouponLogic couponLogic;

    @Autowired
    public CompanyLogic(ICompanyDal iCompanyDal) {
        this.iCompanyDal = iCompanyDal;
    }

    /***********************************************************************************
     Crud and more operational  functions
     ***********************************************************************************/
    //This function adding company to DB after validation
    public void createCompany(CompanyEntity company) throws Exception {
        validateCompany(company);
        iCompanyDal.save(company);
    }

    //Updating the company  with the relevant id
    public void updateCompany(CompanyEntity company) {
        iCompanyDal.existsById(company.getId());
        iCompanyDal.save(company);
    }

    //Delete company
    public void deleteCompany(long id) throws Exception {
        Validation.validateId(id);
        ;
        iCompanyDal.existsById(id);
        iCompanyDal.deleteById(id);
    }

    //Getting all companies in dara
    public List<CompanyDto> getAllCompanies() {
        List<CompanyDto> listOfCompanies = iCompanyDal.getAllCompanies();
        return listOfCompanies;
    }


    //Getting all companies in dara-Pagination
    public List<CompanyDto> getAllCompanies(long pageNumber) {
        Pageable paging = PageRequest.of((int) pageNumber, (int) PAGENIATION_FOR_COMPANY);
        List<CompanyDto> listOfCompanies = iCompanyDal.getAllCompanies(paging);
        return listOfCompanies;
    }


    public CompanyDto getCompanyById(long id) throws Exception {
        Validation.validateId(id);
        CompanyDto company = iCompanyDal.findCompanyById(id);
        return company;
    }


    /***********************************************************
     Validations functions
     ************************************************************/

/*
//Validation for delete Company, includes verification that the user is admin
    private void validateIdForDeleteCompanyInDB(UserType userType, int id) throws Exception {
        // userLogic.validateUserTypeForDeleteRecords(userType);-> Checked in "isCompanyIdInCouponTable"

        if (isCompanyIdInCouponTable(userType, id)) {
            throw new ServerException(ErrorType.DELETION_ERROR, "Can not delete company id that is in Coupons table!,delete coupons first!");
        }
    }
    */


    //This function handle all the validation for creating a Company
    private void validateCompany(CompanyEntity company) throws Exception {
        validateCompanyAddress(company.getAddress());
        validatePhoneNumber(company.getPhoneNumber());
        validateCompanyName(company.getCompanyName());
        validateEmailField(company.getEmail());
    }

    //This function is checking if the id of the companyId is not in coupons table
    private boolean isCompanyIdInCouponTable(long id) throws Exception {
        List<CouponDto> couponsInDb = new ArrayList<>();
        couponsInDb.add(couponLogic.getCouponById(id));
        int i = 0;
        while (i < couponsInDb.size()) {
            if (id == couponsInDb.get(i).getCompany().getId()) {
                return true;
            }
            i++;
        }
        return false;
    }

    //This function validates that the email field
    private void validateEmailField(String inputValue) throws Exception {
        checkTextNotNullOrEmpty(inputValue);
        if (!isValidEmailTemplate(inputValue)) {
            throw new ServerException(ErrorType.INVALID_FILED, "Email field is not with the right format");
        }
    }

    //Checking email template (Taken from internet)
    private boolean isValidEmailTemplate(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();

    }

    //Handling the company name input
    private void validateCompanyName(String name) throws Exception {
        checkTextNotNullOrEmpty(name);

    }

    //Handling the company address input
    private void validateCompanyAddress(String newAddress) throws Exception {
        checkTextNotNullOrEmpty(newAddress);
    }

    //This function is used to check if a string is not null or empty
    private void checkTextNotNullOrEmpty(String text) throws Exception {
        if (text.equals(null) || text.equals("")) {
            throw new ServerException(ErrorType.INVALID_NAME, "The name given is not valid");
        }
    }

    //Checking if the company exists in live(not expired) coupon
    //The dal function is in Coupon Dal so some of this operation handle Coupon Logic
    private void validateCompanyNotInNotExpiredCoupons(long id) throws Exception {
        List<CouponDto> couponsWithCompanyNames = couponLogic.validateCompanyNotInNotExpiredCoupons(id);
        if (couponsWithCompanyNames.size() > 0) {
            throw new ServerException(ErrorType.COMPANY_IN_USE, "This company is in use");
        }
    }

    //This validation get the phone number (set as string) and checks that the value contains only digits
    private void validatePhoneNumber(String phoneNumber) throws Exception {
        int lenPhoneNumber = phoneNumber.length();
        for (int i = 0; i < lenPhoneNumber; i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                throw new ServerException(ErrorType.INVALID_PHONE_NUMBER, "The number inserted contains other characters but numbers");
            }

        }
    }


}

