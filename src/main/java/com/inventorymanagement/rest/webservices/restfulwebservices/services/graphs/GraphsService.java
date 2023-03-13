package com.inventorymanagement.rest.webservices.restfulwebservices.services.graphs;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphsDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.sales.DaySalesService;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.sales.MonthSalesService;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.shoes.ShoesPopularityService;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.worker.WorkerSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphsService {

    @Autowired
    private MonthSalesService monthSalesService;

    @Autowired
    private DaySalesService daySalesService;

    @Autowired
    private ShoesPopularityService shoesPopularityService;

    @Autowired
    private WorkerSalesService workerSalesService;

    public GraphsDTO getGraphs() {
        GraphDTO monthSalesGraph = monthSalesService.getMonthSalesGraph();
        GraphDTO daySalesGraph = daySalesService.getDaySalesGraph();
        GraphDTO shoesPopularityGraph = shoesPopularityService.getShoesPopularityGraph();
        GraphDTO workerSalesGraph = workerSalesService.getWorkerSalesGraph();
        GraphsDTO graphsDTO = new GraphsDTO(monthSalesGraph, daySalesGraph, shoesPopularityGraph, workerSalesGraph);
        return graphsDTO;
    }
}
