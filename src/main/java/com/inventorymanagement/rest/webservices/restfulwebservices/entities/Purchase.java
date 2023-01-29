package com.inventorymanagement.rest.webservices.restfulwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @CreatedDate
    private Instant createdDate;

    private Double sum;
}
