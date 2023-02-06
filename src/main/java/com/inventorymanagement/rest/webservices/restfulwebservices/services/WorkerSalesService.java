package com.inventorymanagement.rest.webservices.restfulwebservices.services;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Worker;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.WorkerSales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.WorkerSalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public GraphDTO getWorkerSalesGraph() {
        List<WorkerSales> workerSalesList = getWorkerSalesList();
        GraphDTO workerSalesGraph = new GraphDTO();
        List<String> labels = workerSalesGraph.getLabels();
        List<Number> data = workerSalesGraph.getData();
        workerSalesList.stream().forEach(workerSales -> {
            data.add(0, workerSales.getSum());
            labels.add(0, workerSales.getWorker().getName());
        });
        return workerSalesGraph;
    }

    private List<WorkerSales> getWorkerSalesList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.DESC, "sum", 0, 10);
        List<WorkerSales> workerSalesList = workerSalesRepository.getWorkerSalesList(DateUtils.getCurrentMonthDate(), paging);
        return workerSalesList;
    }
}
