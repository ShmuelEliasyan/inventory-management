package com.inventorymanagement.rest.webservices.restfulwebservices.services.worker;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.Worker;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.worker.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Optional<Worker> findById(int workerId) {
        return workerRepository.findById(workerId);
    }
}
