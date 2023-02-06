package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.ShoesPopularity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShoesPopularityRepository extends JpaRepository<ShoesPopularity, Integer> {
    public ShoesPopularity findByDateAndShoes(Date date, Shoes shoes);

    @Query("select sp from ShoesPopularity sp where sp.date = ?1")
    public List<ShoesPopularity> getShoesPopularityList(Date date, PageRequest paging);
}