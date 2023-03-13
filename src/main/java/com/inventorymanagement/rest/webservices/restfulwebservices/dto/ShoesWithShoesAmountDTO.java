package com.inventorymanagement.rest.webservices.restfulwebservices.dto;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.Shoes;
import com.inventorymanagement.rest.webservices.restfulwebservices.entities.shoes.ShoesAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoesWithShoesAmountDTO {
    private Shoes shoes;
    private List<ShoesAmount> shoesAmount = new ArrayList<>();
}
