package com.inventorymanagement.rest.webservices.restfulwebservices.services.purchase;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.purchase.PurchaseSoldPairs;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.purchase.PurchaseSoldPairsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PurchaseSoldPairsService {

    @Autowired
    private PurchaseSoldPairsRepository purchaseSoldPairsRepository;


    public List<PurchaseSoldPairs> saveAll(List<PurchaseSoldPairs> purchaseSoldPairsList) {
        return purchaseSoldPairsRepository.saveAll(purchaseSoldPairsList);
    }
}
