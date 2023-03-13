package com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"worker_id", "date"})})
public class WorkerSales {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Worker worker;

    private Double sum;

    @Temporal(TemporalType.DATE)
    private Date date;
}
