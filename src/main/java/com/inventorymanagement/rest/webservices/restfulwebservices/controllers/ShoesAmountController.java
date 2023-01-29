package com.inventorymanagement.rest.webservices.restfulwebservices.controllers;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.ShoesAmount;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.ShoesAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoes-amount")
public class ShoesAmountController {

    @Autowired
    private ShoesAmountService shoesAmountService;

    @PostMapping("/add-shoes-amount/{barcode}")
    public ResponseEntity<List<ShoesAmount>> addShoesAmount(@RequestBody List<ShoesAmount> shoesAmountList, @PathVariable String barcode) {
        shoesAmountService.addShoesAmount(shoesAmountList, barcode);
        return new ResponseEntity<>(shoesAmountList, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-shoes-amount")
    public ResponseEntity<String> deleteShoesAmount(@RequestParam(value = "barcode") String barcode,
                                                    @RequestParam(value = "size", required = false) String size) {
        shoesAmountService.deleteShoesAmount(barcode, size);
        return new ResponseEntity<>("Shoes removed successfully", HttpStatus.OK);
    }

    @PutMapping("/update-shoes-amount")
    public ResponseEntity<String> updateShoesAmount(@RequestBody ShoesAmount shoesAmount) {
        shoesAmountService.updateShoesAmount(shoesAmount);
        return new ResponseEntity<>("Shoes amount updated successfully", HttpStatus.OK);
    }
}
