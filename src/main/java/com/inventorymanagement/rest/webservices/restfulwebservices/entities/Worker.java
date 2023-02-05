package com.inventorymanagement.rest.webservices.restfulwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    private String name;

    @OneToMany(mappedBy = "worker", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<WorkerSales> workerSales;

    @OneToMany(mappedBy = "worker", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Purchase> purchases;
}
