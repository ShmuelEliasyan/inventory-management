package com.inventorymanagement.rest.webservices.restfulwebservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {
    private Double sum;

    private int workerId;

    private List<PurchaseSoldPairsDTO> purchaseSoldPairsDTOList;
}
