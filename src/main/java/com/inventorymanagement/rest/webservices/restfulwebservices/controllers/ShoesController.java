package com.inventorymanagement.rest.webservices.restfulwebservices.controllers;

import com.inventorymanagement.rest.webservices.restfulwebservices.criterias.ShoesCriteria;
import com.inventorymanagement.rest.webservices.restfulwebservices.dto.ShoesCatalogDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.dto.ShoesWithShoesAmountDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.ShoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoes")
public class ShoesController {

    @Autowired
    private ShoesService shoesService;

    @PostMapping("/add-shoes")
    public ResponseEntity<Shoes> addShoes(@RequestBody Shoes shoes) {
        shoesService.addShoes(shoes);
        return new ResponseEntity<>(shoes, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-shoes")
    public ResponseEntity<String> deleteShoes(@RequestParam(value = "barcode") String barcode) {
        shoesService.deleteShoes(barcode);
        return new ResponseEntity<>("Shoes removed successfully", HttpStatus.OK);
    }

    @GetMapping("/get-barcodes-list")
    public ResponseEntity<List<String>> getBarcodesList() {
        List<String> barcodesList = shoesService.getBarcodesList();
        return new ResponseEntity<>(barcodesList, HttpStatus.OK);
    }

    @GetMapping("/get-shoes-with-shoes-amount")
    public ResponseEntity<ShoesWithShoesAmountDTO> getShoesWithShoesAmount(@RequestParam(value = "barcode") String barcode) {
        ShoesWithShoesAmountDTO shoesWithShoesAmount = shoesService.getShoesWithShoesAmount(barcode);
        return new ResponseEntity<>(shoesWithShoesAmount, HttpStatus.OK);
    }

    @GetMapping("/get-shoes-catalog")
    public ResponseEntity<ShoesCatalogDTO> getShoesCatalog(@Valid ShoesCriteria shoesCriteria) {
        ShoesCatalogDTO shoesList = shoesService.getShoesCatalog(shoesCriteria);
        return new ResponseEntity<>(shoesList, HttpStatus.OK);
    }
}
