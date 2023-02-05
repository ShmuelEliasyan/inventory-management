package com.inventorymanagement.rest.webservices.restfulwebservices.controllers;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.PurchaseDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @GetMapping("/test")
    public ResponseEntity<String> test() throws ExecutionException, InterruptedException {
        int number = 20;
        Thread newThread = new Thread(() -> {
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            log.info("Factorial of " + number + " is: " + number * number);
        });
        newThread.start();

        ExecutorService threadpool = Executors.newCachedThreadPool();
        threadpool.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
//            log.info("Second Factorial of " + number + " is: " + number * number);
            threadpool.shutdown();
        });

        return new ResponseEntity<>("test", HttpStatus.CREATED);
    }
}
