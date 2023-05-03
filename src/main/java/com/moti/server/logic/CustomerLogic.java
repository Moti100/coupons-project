package com.moti.server.logic;

import com.moti.server.dal.ICustomerDal;
import com.moti.server.entities.CustomerEntity;
import com.moti.server.enums.ErrorType;
import com.moti.server.enums.UserType;
import com.moti.server.exceptions.ServerException;
import com.moti.server.dto.CustomerDto;
import com.moti.server.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.moti.server.constans.Consts.PAGENIATION_FOR_CUSTOMER;

@Service
public class CustomerLogic {
    private UserLogic userLogic;
    private ICustomerDal iCustomerDal;

    @Autowired
    public CustomerLogic(UserLogic userLogic, ICustomerDal iCustomerDal) {
        this.userLogic = userLogic;
        this.iCustomerDal = iCustomerDal;
    }

    /***********************************************************************************
     Crud and more operational  functions
     ***********************************************************************************/

//    //Adding customer after adding a user, to get its id
    public long createCustomer(CustomerEntity customer) throws Exception {
        validateCustomer(customer);
        iCustomerDal.save(customer);
        return customer.getId();
    }

    //New function after phase 1- to update customer
    public void updateCustomer(CustomerEntity customer) throws Exception {
        validateCustomer(customer);
        iCustomerDal.save(customer);
    }

    //Remove Customer
    public void removeCustomer(long id) throws Exception {
//        userLogic.validateUserTypeForDeleteRecords(userType);
        Validation.validateId(id);
        iCustomerDal.existsById(id);
        iCustomerDal.deleteById(id);
    }

    //Getting Customer information by id
    public CustomerDto getCustomerDataById(long id) throws Exception {
        Validation.validateId(id);
        CustomerDto customer = iCustomerDal.findCustomerById(id);
        return customer;
    }

    //Getting all customers with pagination
    public List<CustomerDto> getAllCustomers(long pageNumber) throws Exception {
        validatePageNumber(pageNumber);
        Pageable paging = PageRequest.of((int) pageNumber, (int) PAGENIATION_FOR_CUSTOMER);
        List<CustomerDto> allCustomersInDb = iCustomerDal.getAllCustomers(paging);
        return allCustomersInDb;
    }

    //Getting all customers without pagination
    public List<CustomerDto> getAllCustomers() throws Exception {
        List<CustomerDto> allCustomersInDb = iCustomerDal.getAllCustomers();
        return allCustomersInDb;
    }

    /***********************************************************
     Validations functions
     ************************************************************/

//Validation for Customer data
    private void validateCustomer(CustomerEntity customer) throws Exception {
        validateAmountOfKids(customer.getAmountOfKids());
        validateCustomerAddress(customer.getAddress());
        validatePhoneNumber(customer.getPhone());
    }

    private void validateAmountOfKids(long amountOfKids) throws Exception {
        if (amountOfKids < 0) {
            throw new ServerException(ErrorType.INVALID_FILED, "amount of kids must  be positive or 0 ");
        }
    }

    //Validate customer address
    private void validateCustomerAddress(String address) throws Exception {
        if (address.equals(null) || address.equals("")) {
            throw new Exception("Address can not be null or empty");
        }
    }

    //Checking that the phone contains only digits
    private void validatePhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber.equals(null) || phoneNumber.equals("")) {
            throw new ServerException(ErrorType.INVALID_PHONE_NUMBER, "Phone can not be null or empty");
        }
        int lenPhoneNumber = phoneNumber.length();
        for (int i = 0; i < lenPhoneNumber; i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                throw new ServerException(ErrorType.INVALID_PHONE_NUMBER, "The number inserted contains other characters but numbers");
            }
        }
    }

    //Validation of page number
    private void validatePageNumber(long pageNumber) throws Exception {
        if (pageNumber <= 0) {
            throw new ServerException(ErrorType.INVALID_FILED, "Page number must be 1 or above");
        }
    }
}

