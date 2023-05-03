package com.moti.server.controller;


import com.moti.server.dto.CompanyDto;
import com.moti.server.dto.UserDto;
import com.moti.server.entities.CompanyEntity;
import com.moti.server.enums.UserType;
import com.moti.server.logic.CompanyLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private CompanyLogic companyLogic;

    @Autowired
    public CompaniesController(CompanyLogic companyLogic) {
        this.companyLogic = companyLogic;
    }

    //Creating new company
    //Function relevant to addCompany in Logic
    @PostMapping
    public void createCompany(@RequestBody CompanyEntity company) throws Exception {
        companyLogic.createCompany(company);
    }

    @PutMapping
    public void updateCompany(@RequestBody CompanyEntity company) throws Exception {
        companyLogic.updateCompany(company);
    }

    /*
    //Update Company
    @PutMapping
    public void updateCompany(@RequestBody CompanyEntity company) throws Exception {
        companyLogic.updateCompany(company);
    }

    //How to use that?
    //Update Company Address
    @PutMapping("{companyId}/{userType}/{newAddress}")
    public void updateCompanyAddress(@PathVariable("companyId") long id, @PathVariable("newAddress") String newAddress) throws Exception {
        companyLogic.updateCompanyAddressById(id, newAddress);
    }

    //How to use that?
    @PutMapping("{companyId}/{newAddress}")
    public void updateCompanyEmail(@PathVariable("companyId") long id, @PathVariable("newAddress") String newAddress) throws Exception {
        companyLogic.updateCompanyEmailById(id, newAddress);
    }

    //How to use that?
    @PutMapping("{companyId}/{userType}/{newName}")
    public void updateCompanyName(@PathVariable("companyId") int id, @PathVariable("userType") UserType userType, @PathVariable("newName") String newName) throws Exception {
        companyLogic.updateCompanyNameById(userType, newName, id);
    }
    */


    //Delete Company
    @DeleteMapping("{companyId}")
    public void deleteCompany(@PathVariable("companyId") long id) throws Exception {
        companyLogic.deleteCompany(id);
    }

    //Get all companies
    @GetMapping
    public List<CompanyDto> getAllCompanies() throws Exception {
        List<CompanyDto> listOfCompanies = companyLogic.getAllCompanies();
        return listOfCompanies;
    }


    @GetMapping("/bypage")
    public List<CompanyDto> getAllCompanies(long pageNumber) throws Exception {
        List<CompanyDto> listOfCompanies = companyLogic.getAllCompanies(pageNumber);
        return listOfCompanies;
    }

    @GetMapping("{id}" )
    public CompanyDto getCompanyById(@PathVariable("id" ) long id) throws Exception {
        CompanyDto companyDto  = companyLogic.getCompanyById(id);
        return companyDto;
    }
}
