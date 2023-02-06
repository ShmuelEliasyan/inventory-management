package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.MonthSales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.MonthSalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public GraphDTO getMonthSalesGraph() {
        List<MonthSales> monthSalesList = getMonthSalesList();
        GraphDTO monthSalesGraph = new GraphDTO();
        List<String> labels = monthSalesGraph.getLabels();
        List<Number> data = monthSalesGraph.getData();
        monthSalesList.stream().forEach(monthSales -> {
            data.add(monthSales.getSum());
            Date date = monthSales.getDate();
            labels.add(DateUtils.getMonthNameByDate(date));
        });
        return monthSalesGraph;
    }

    private List<MonthSales> getMonthSalesList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.ASC, "date", 0, 6);
        List<MonthSales> monthSalesList = monthSalesRepository.getMonthSalesList(paging);
        return monthSalesList;
    }
}
