package com.inventorymanagement.rest.webservices.restfulwebservices.repositories.worker;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}