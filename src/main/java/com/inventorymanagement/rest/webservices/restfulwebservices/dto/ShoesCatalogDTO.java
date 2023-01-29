package com.inventorymanagement.rest.webservices.restfulwebservices.dto;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.Shoes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoesCatalogDTO {
    private List<Shoes> shoes = new ArrayList<>();
    private int totalShoesAmount;
    private int totalPagesCount;
    private List<String> sizes = new ArrayList<>();
}
