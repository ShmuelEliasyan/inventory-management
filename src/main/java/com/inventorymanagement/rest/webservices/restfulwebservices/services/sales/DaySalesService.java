package com.inventorymanagement.rest.webservices.restfulwebservices.services.sales;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.sales.DaySales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.sales.DaySalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DaySalesService {

    @Autowired
    private DaySalesRepository daySalesRepository;

    @Resource
    @Lazy
    private DaySalesService self;

    @CachePut("daySales")
    public List<DaySales> addNewPurchase(Double sum) {
        Date todayDate = DateUtils.getTodayDate();

        DaySales todaySales = daySalesRepository.findByDate(todayDate);
        List<DaySales> todaySalesList = self.getDaySalesList();

        if (todaySales == null) {
            todaySales = new DaySales(0, todayDate, 0.0);
            if (todaySalesList.size() == 7) todaySalesList.remove(0);
            todaySalesList.add(todaySales);
        }

        Double oldSum = todaySales.getSum();
        Double newSum = oldSum + sum;
        todaySales.setSum(newSum);

        daySalesRepository.save(todaySales);
        DaySales daySales = todaySalesList.get(todaySalesList.size() - 1);
        daySales.setSum(newSum);
        return todaySalesList;
    }

    public GraphDTO getDaySalesGraph() {
        List<DaySales> daySalesList = self.getDaySalesList();
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

    //    @Cacheable("daySales")
    public List<DaySales> getDaySalesList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.ASC, "date", 0, 7);
        List<DaySales> daySalesList = daySalesRepository.getDaySalesList(paging);
        return daySalesList;
    }

}
