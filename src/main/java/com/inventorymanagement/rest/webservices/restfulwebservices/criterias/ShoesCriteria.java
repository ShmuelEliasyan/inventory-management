package com.inventorymanagement.rest.webservices.restfulwebservices.criterias;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoesCriteria {
    @Min(0)
    private int page = 0;
    @Min(1)
    private int pageSize = 12;
    private String sortBy = "createdDate";
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private List<String> shoesSizes = new ArrayList<>();
}
