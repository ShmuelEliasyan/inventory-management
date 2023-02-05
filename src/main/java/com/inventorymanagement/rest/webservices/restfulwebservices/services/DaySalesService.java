package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.DaySales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.DaySalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DaySalesService {

    @Autowired
    private DaySalesRepository daySalesRepository;

    public void addNewPurchase(Double sum) {
        Date todayDate = DateUtils.getTodayDate();

        DaySales todaySales = daySalesRepository.findByDate(todayDate);

        if (todaySales == null) {
            todaySales = new DaySales(0, todayDate, 0.0);
        }

        Double oldSum = todaySales.getSum();
        Double newSum = oldSum + sum;
        todaySales.setSum(newSum);

        daySalesRepository.save(todaySales);
    }
}
