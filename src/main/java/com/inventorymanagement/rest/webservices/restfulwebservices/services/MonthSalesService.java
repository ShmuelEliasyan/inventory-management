package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.MonthSales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.MonthSalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MonthSalesService {

    @Autowired
    private MonthSalesRepository monthSalesRepository;

    public void addNewPurchase(Double sum) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();

        MonthSales currentMonthSales = monthSalesRepository.findByDate(currentMonthDate);

        if (currentMonthSales == null) {
            currentMonthSales = new MonthSales(0, currentMonthDate, 0.0);
        }

        Double oldSum = currentMonthSales.getSum();
        Double newSum = oldSum + sum;
        currentMonthSales.setSum(newSum);

        monthSalesRepository.save(currentMonthSales);
    }
}
