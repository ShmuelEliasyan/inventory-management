package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.WorkerSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WorkerSalesRepository extends JpaRepository<WorkerSales, Integer> {
    public WorkerSales findByDate(Date date);
}