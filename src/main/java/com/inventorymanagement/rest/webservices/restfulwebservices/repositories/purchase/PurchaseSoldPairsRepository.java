package com.inventorymanagement.rest.webservices.restfulwebservices.repositories.purchase;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.purchase.PurchaseSoldPairs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseSoldPairsRepository extends JpaRepository<PurchaseSoldPairs, Integer> {
}