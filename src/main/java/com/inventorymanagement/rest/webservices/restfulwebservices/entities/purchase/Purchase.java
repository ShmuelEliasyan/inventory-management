package com.inventorymanagement.rest.webservices.restfulwebservices.entities.purchase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.worker.Worker;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Worker worker;

    @OneToMany(mappedBy = "purchase", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PurchaseSoldPairs> purchaseSoldPairsList;
}
