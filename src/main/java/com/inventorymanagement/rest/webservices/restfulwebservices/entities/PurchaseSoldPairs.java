package com.inventorymanagement.rest.webservices.restfulwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"purchase_id", "shoes_id"})})
public class PurchaseSoldPairs {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    private int soldPairs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Shoes shoes;
}
