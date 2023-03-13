package com.inventorymanagement.rest.webservices.restfulwebservices.entities.sales;

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
public class DaySales {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(unique = true)
    private Date date;

    private Double sum;
}
