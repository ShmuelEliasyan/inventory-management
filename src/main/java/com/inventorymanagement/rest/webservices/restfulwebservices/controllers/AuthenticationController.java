package com.inventorymanagement.rest.webservices.restfulwebservices.controllers;

import com.inventorymanagement.rest.webservices.restfulwebservices.request.AuthenticationRequest;
import com.inventorymanagement.rest.webservices.restfulwebservices.request.RegisterRequest;
import com.inventorymanagement.rest.webservices.restfulwebservices.response.AuthenticationResponse;
import com.inventorymanagement.rest.webservices.restfulwebservices.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
