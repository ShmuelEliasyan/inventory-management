package com.inventorymanagement.rest.webservices.restfulwebservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GraphDTO {
    private List<String> labels = new ArrayList<>();
    private List<Number> data = new ArrayList<>();
}
