package com.inventorymanagement.rest.webservices.restfulwebservices.repositories;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Integer> {

    public static final String FIND_ALL_BARCODES = "SELECT barcode FROM shoes";

    @Query(value = FIND_ALL_BARCODES, nativeQuery = true)
    public List<String> findAllBarcodes();

    public Optional<Shoes> findByBarcode(String barcode);

    @Query("select s from Shoes s join s.shoesAmounts sa where sa.size in ?1")
    public List<Shoes> getShoesListByShoesSizes(List<String> shoesSizes, PageRequest paging);

    @Query("select count(s) from Shoes s join s.shoesAmounts sa where sa.size in ?1")
    public long getShoesCountByShoesSizes(List<String> shoesSizes);

    @Query("select s from Shoes s")
    public List<Shoes> getShoesList(PageRequest paging);
}