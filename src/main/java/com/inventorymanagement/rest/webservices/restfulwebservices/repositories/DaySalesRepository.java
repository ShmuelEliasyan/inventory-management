package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.DaySales;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DaySalesRepository extends JpaRepository<DaySales, Integer> {
    public DaySales findByDate(Date date);

    @Query("select ds from DaySales ds")
    public List<DaySales> getDaySalesList(PageRequest paging);
}