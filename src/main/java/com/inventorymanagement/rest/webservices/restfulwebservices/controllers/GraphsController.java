package com.inventorymanagement.rest.webservices.restfulwebservices.controllers;

import com.inventorymanagement.rest.webservices.restfulwebservices.dto.GraphsDTO;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.GraphsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("graphs")
public class GraphsController {
    @Autowired
    private GraphsService graphsService;


    @GetMapping("/get-graphs")
    public ResponseEntity<GraphsDTO> submitPurchase() {
        GraphsDTO graphsDTO = graphsService.getGraphs();
        return new ResponseEntity<>(graphsDTO, HttpStatus.CREATED);
    }
}
