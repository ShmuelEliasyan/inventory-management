package com.inventorymanagement.rest.webservices.restfulwebservices.services.worker;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.Worker;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.WorkerSales;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.worker.WorkerSalesRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.tasks.GraphsTask;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.DateUtils;
import com.inventorymanagement.rest.webservices.restfulwebservices.utils.PaginationUtils;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerSalesService {

    private static final Logger log = LoggerFactory.getLogger(GraphsTask.class);

    @Autowired
    private WorkerSalesRepository workerSalesRepository;

    @Resource
    @Lazy
    private WorkerSalesService self;

    private static WorkerSales getNewWorkerSales(Worker worker, Date currentMonthDate, List<WorkerSales> workerSalesList) {
        WorkerSales workerSales;
        workerSales = new WorkerSales(0, worker, 0.0, currentMonthDate);
        workerSalesList.add(workerSales);
        return workerSales;
    }

    @CachePut("workerSales")
    public List<WorkerSales> addNewPurchase(Double sum, Worker worker) {
        Date currentMonthDate = DateUtils.getCurrentMonthDate();
        List<WorkerSales> workerSalesList = self.getWorkerSalesList();

        List<WorkerSales> tempWorkerSalesList = workerSalesRepository.findByDateAndWorker(currentMonthDate, worker);
        WorkerSales workerSales = tempWorkerSalesList.isEmpty() ? getNewWorkerSales(worker, currentMonthDate, workerSalesList) : tempWorkerSalesList.get(0);

        Double oldSum = workerSales.getSum();
        Double newSum = oldSum + sum;
        workerSales.setSum(newSum);

        workerSalesRepository.save(workerSales);
        WorkerSales tempWorkerSales = workerSalesList.stream().filter(ws -> ws.getWorker().getName().equals(worker.getName())).collect(Collectors.toList()).get(0);
        tempWorkerSales.setSum(newSum);
        return workerSalesList;
    }

    public GraphDTO getWorkerSalesGraph() {
        List<WorkerSales> workerSalesList = self.getWorkerSalesList();
        GraphDTO workerSalesGraph = new GraphDTO();
        List<String> labels = workerSalesGraph.getLabels();
        List<Number> data = workerSalesGraph.getData();
        workerSalesList.stream().forEach(workerSales -> {
            data.add(0, workerSales.getSum());
            labels.add(0, workerSales.getWorker().getName());
        });
        return workerSalesGraph;
    }

    @Cacheable("workerSales")
    public List<WorkerSales> getWorkerSalesList() {
        PageRequest paging = PaginationUtils.getPageRequest(Sort.Direction.DESC, "sum", 0, 100);
        List<WorkerSales> workerSalesList = workerSalesRepository.getWorkerSalesList(DateUtils.getCurrentMonthDate(), paging);
        return workerSalesList;
    }
}
