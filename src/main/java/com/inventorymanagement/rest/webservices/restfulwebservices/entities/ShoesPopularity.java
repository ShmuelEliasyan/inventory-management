package com.inventorymanagement.rest.webservices.restfulwebservices.entities;

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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "shoes_id"})})
public class ShoesPopularity {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int soldPairs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Shoes shoes;
}
