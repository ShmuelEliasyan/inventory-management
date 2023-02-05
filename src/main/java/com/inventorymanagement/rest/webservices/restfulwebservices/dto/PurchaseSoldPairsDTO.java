package com.inventorymanagement.rest.webservices.restfulwebservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseSoldPairsDTO {
    private int soldPairs;

    private String barcode;
}
