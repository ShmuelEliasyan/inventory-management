package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.WorkerSales;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkerSalesRepository extends JpaRepository<WorkerSales, Integer> {
    public WorkerSales findByDate(Date date);

    @Query("select ws from WorkerSales ws where ws.date = ?1")
    public List<WorkerSales> getWorkerSalesList(Date date, PageRequest paging);
}