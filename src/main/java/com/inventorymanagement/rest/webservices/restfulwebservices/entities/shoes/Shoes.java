package com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.purchase.PurchaseSoldPairs;
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
public class Shoes {
    @Id
    @GeneratedValue
    @JsonIgnore
    private int id;

    @CreatedDate
    private Instant createdDate;

    @Column(unique = true)
    private String barcode;

    private String sizes;

    private String imageUrl;

    private String name;

    private Double cost;

    @OneToMany(mappedBy = "shoes", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ShoesAmount> shoesAmounts;

    @OneToMany(mappedBy = "shoes", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ShoesPopularity> shoesPopularityList;

    @OneToMany(mappedBy = "shoes", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<PurchaseSoldPairs> purchaseSoldPairsList;
}
