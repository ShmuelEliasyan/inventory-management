package com.inventorymanagement.rest.webservices.restfulwebservices.repositories.worker;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.Worker;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.WorkerSales;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkerSalesRepository extends JpaRepository<WorkerSales, Integer> {
    public List<WorkerSales> findByDateAndWorker(Date date, Worker worker);

    @Query("select ws from WorkerSales ws where ws.date = ?1")
    public List<WorkerSales> getWorkerSalesList(Date date, PageRequest paging);
}