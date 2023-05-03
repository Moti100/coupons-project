package com.moti.server.controller;


import com.moti.server.entities.PurchaseEntity;
import com.moti.server.enums.UserType;
import com.moti.server.logic.PurchaseLogic;
import com.moti.server.dto.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private PurchaseLogic purchaseLogic;

    @Autowired
    public PurchaseController(PurchaseLogic purchaseLogic) {
        this.purchaseLogic = purchaseLogic;
    }

    //Add new purchase
    @PostMapping
    public void addPurchase(@RequestBody PurchaseEntity purchase) throws Exception {
        purchaseLogic.createPurchase(purchase);
    }

    //Delete Purchase
    @DeleteMapping("{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") long id) throws Exception {
        purchaseLogic.removePurchaseById( id);
    }

    //Updating existing purchase
    @PutMapping
    public void updatePurchase(@RequestBody PurchaseEntity purchase) throws Exception {
        purchaseLogic.updatePurchase(purchase);
    }

    //Getting data without pagintation
    @GetMapping
    public List<PurchaseDto> getAllPurchases() throws Exception {
        List<PurchaseDto> listOfPurchases = purchaseLogic.getAllPurchasesFromDB();
        return listOfPurchases;
    }

//Getting data with pagination
    @GetMapping("/byPage")
    public List<PurchaseDto> getAllPurchases(long pageNumber) throws Exception {
        List<PurchaseDto> listOfPurchases = purchaseLogic.getAllPurchasesFromDB(pageNumber);
        return listOfPurchases;
    }


    @GetMapping("{purchaseId}")
    public PurchaseDto getPurchase(@PathVariable("purchaseId") long id) throws Exception {
        PurchaseDto purchase = purchaseLogic.getPurchaseById(id);
        return purchase;
    }
}


