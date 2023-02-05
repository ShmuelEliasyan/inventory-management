package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Worker;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.WorkerSales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.WorkerSalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WorkerSalesService {

    @Autowired
    private WorkerSalesRepository workerSalesRepository;

    public void addNewPurchase(Double sum, Worker worker) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();

        WorkerSales workerSales = workerSalesRepository.findByDate(currentMonthDate);

        if (workerSales == null) {
            workerSales = new WorkerSales(0, worker, 0.0, currentMonthDate);
        }

        Double oldSum = workerSales.getSum();
        Double newSum = oldSum + sum;
        workerSales.setSum(newSum);

        workerSalesRepository.save(workerSales);
    }
}
