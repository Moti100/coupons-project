package com.moti.server.controller;

import com.moti.server.entities.CompanyEntity;
import com.moti.server.entities.CustomerEntity;
import com.moti.server.entities.UserEntity;
import com.moti.server.enums.UserType;
import com.moti.server.logic.CustomerLogic;
import com.moti.server.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerLogic customerLogic;

    @Autowired
    public CustomerController(CustomerLogic customerLogic) {
        this.customerLogic = customerLogic;
    }

    @PostMapping
    //Add new customer
    public void createCoustomer(@RequestBody CustomerEntity customer) throws Exception {
        customer.getUser().setUserType(UserType.Customer);
        customerLogic.createCustomer(customer);
    }

    //Delete Customer
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") long id) throws Exception {
        customerLogic.removeCustomer(id);
    }


    //Original
    /*
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") long id, @RequestBody CustomerEntity customer) throws Exception {
        customer.setId(id);//To pass validation
        customerLogic.updateCustomer(customer);
    }
*/

    @PutMapping
    public void updateCustomer(@RequestBody CustomerEntity customer) throws Exception {
        customerLogic.updateCustomer(customer);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() throws Exception {
        List<CustomerDto> listOfCoupons = customerLogic.getAllCustomers();
        return listOfCoupons;
    }

    @GetMapping("/byPage")
    public List<CustomerDto> getAllCustomers(long pageNumber) throws Exception {
        List<CustomerDto> listOfCoupons = customerLogic.getAllCustomers();
        return listOfCoupons;
    }

    @GetMapping("{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") long id) throws Exception {
        CustomerDto customer = customerLogic.getCustomerDataById(id);
        return customer;
    }
}

