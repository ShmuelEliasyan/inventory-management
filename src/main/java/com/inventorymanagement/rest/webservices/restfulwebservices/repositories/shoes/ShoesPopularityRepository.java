package com.inventorymanagement.rest.webservices.restfulwebservices.repositories.shoes;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.ShoesPopularity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShoesPopularityRepository extends JpaRepository<ShoesPopularity, Integer> {
    public List<ShoesPopularity> findByDateAndShoes(Date date, Shoes shoes);

    @Query("select sp from ShoesPopularity sp where sp.date = ?1")
    public List<ShoesPopularity> getShoesPopularityList(Date date, PageRequest paging);
}