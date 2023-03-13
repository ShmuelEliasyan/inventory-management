package com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"barcode", "size"})})
public class ShoesAmount {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    private String barcode;

    private String size;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Shoes shoes;
}
