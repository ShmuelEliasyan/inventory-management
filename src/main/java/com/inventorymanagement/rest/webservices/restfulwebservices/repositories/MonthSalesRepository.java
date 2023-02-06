package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.MonthSales;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MonthSalesRepository extends JpaRepository<MonthSales, Integer> {
    public MonthSales findByDate(Date date);

    @Query("select ms from MonthSales ms")
    public List<MonthSales> getMonthSalesList(PageRequest paging);
}