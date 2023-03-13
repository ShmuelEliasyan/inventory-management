package com.inventorymanagement.rest.webservices.restfulwebservices.controllers;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.PurchaseDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @PostMapping("/submit-purchase")
    public ResponseEntity<PurchaseDTO> submitPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        purchaseService.submitPurchase(purchaseDTO);
        return new ResponseEntity<>(purchaseDTO, HttpStatus.CREATED);
    }
}
