package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.DaySales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.DaySalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public GraphDTO getDaySalesGraph() {
        List<DaySales> daySalesList = getDaySalesList();
        GraphDTO daySalesGraph = new GraphDTO();
        List<String> labels = daySalesGraph.getLabels();
        List<Number> data = daySalesGraph.getData();
        daySalesList.stream().forEach(daySales -> {
            data.add(daySales.getSum());
            Date date = daySales.getDate();
            labels.add(DateUtils.getDayNameByDate(date));
        });
        return daySalesGraph;
    }

    private List<DaySales> getDaySalesList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.ASC, "date", 0, 7);
        List<DaySales> daySalesList = daySalesRepository.getDaySalesList(paging);
        return daySalesList;
    }

}
