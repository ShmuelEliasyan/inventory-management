package com.inventorymanagement.rest.webservices.restfulwebservices.repositories.purchase;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}