package com.inventorymanagement.rest.webservices.restfulwebservices.repositories.shoes;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.ShoesAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoesAmountRepository extends JpaRepository<ShoesAmount, Integer> {
    public static final String FIND_ALL_SIZES = "select distinct size from shoes_amount";

    @Query(value = FIND_ALL_SIZES, nativeQuery = true)
    public List<String> findAllSizes();

    public Optional<ShoesAmount> findByBarcodeAndSize(String barcode, String size);

    public List<ShoesAmount> findByBarcode(String barcode);
}