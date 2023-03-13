package com.inventorymanagement.rest.webservices.restfulwebservices.services.sales;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.sales.MonthSales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.sales.MonthSalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MonthSalesService {

    @Autowired
    private MonthSalesRepository monthSalesRepository;

    @Resource
    @Lazy
    private MonthSalesService self;

    @CachePut("monthSales")
    public List<MonthSales> addNewPurchase(Double sum) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();

        MonthSales currentMonthSales = monthSalesRepository.findByDate(currentMonthDate);
        List<MonthSales> monthSalesList = self.getMonthSalesList();

        if (currentMonthSales == null) {
            currentMonthSales = new MonthSales(0, currentMonthDate, 0.0);
            if (monthSalesList.size() == 6) monthSalesList.remove(0);
            monthSalesList.add(currentMonthSales);
        }

        Double oldSum = currentMonthSales.getSum();
        Double newSum = oldSum + sum;
        currentMonthSales.setSum(newSum);

        monthSalesRepository.save(currentMonthSales);
        MonthSales monthSales = monthSalesList.get(monthSalesList.size() - 1);
        monthSales.setSum(newSum);
        return monthSalesList;
    }

    public GraphDTO getMonthSalesGraph() {
        List<MonthSales> monthSalesList = self.getMonthSalesList();
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

    @Cacheable("monthSales")
    public List<MonthSales> getMonthSalesList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.ASC, "date", 0, 6);
        List<MonthSales> monthSalesList = monthSalesRepository.getMonthSalesList(paging);
        return monthSalesList;
    }
}
