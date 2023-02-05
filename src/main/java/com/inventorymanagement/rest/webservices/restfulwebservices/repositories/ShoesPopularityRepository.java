package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.ShoesPopularity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ShoesPopularityRepository extends JpaRepository<ShoesPopularity, Integer> {
    public ShoesPopularity findByDateAndShoes(Date date, Shoes shoes);
}