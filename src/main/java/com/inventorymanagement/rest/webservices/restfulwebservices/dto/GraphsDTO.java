package com.inventorymanagement.rest.webservices.restfulwebservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GraphsDTO {
    private GraphDTO monthSalesGraph;
    private GraphDTO daySalesGraph;
    private GraphDTO shoesPopularityGraph;
    private GraphDTO workerSalesGraph;
}
