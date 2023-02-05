package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.PurchaseSoldPairs;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.ShoesPopularity;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.ShoesPopularityRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoesPopularityService {

    @Autowired
    private ShoesPopularityRepository shoesPopularityRepository;

    public void addNewPurchase(List<PurchaseSoldPairs> purchaseSoldPairsList) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();

        for (PurchaseSoldPairs purchaseSoldPairs : purchaseSoldPairsList) {
            Shoes shoes = purchaseSoldPairs.getShoes();
            ShoesPopularity shoesPopularity = shoesPopularityRepository.findByDateAndShoes(currentMonthDate, shoes);

            if (shoesPopularity == null) {
                shoesPopularity = new ShoesPopularity(0, currentMonthDate, 0, shoes);
            }

            int oldSoldPairs = shoesPopularity.getSoldPairs();
            int newSoldPairs = oldSoldPairs + purchaseSoldPairs.getSoldPairs();
            shoesPopularity.setSoldPairs(newSoldPairs);

            shoesPopularityRepository.save(shoesPopularity);
        }
    }
}
