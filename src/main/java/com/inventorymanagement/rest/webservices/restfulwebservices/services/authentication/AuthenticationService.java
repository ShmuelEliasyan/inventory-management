package com.inventorymanagement.rest.webservices.restfulwebservices.services.authentication;

import com.inventorymanagement.rest.webservices.restfulwebservices.entities.users.User;
import com.inventorymanagement.rest.webservices.restfulwebservices.enums.Role;
import com.inventorymanagement.rest.webservices.restfulwebservices.repositories.users.UserRepository;
import com.inventorymanagement.rest.webservices.restfulwebservices.request.AuthenticationRequest;
import com.inventorymanagement.rest.webservices.restfulwebservices.request.RegisterRequest;
import com.inventorymanagement.rest.webservices.restfulwebservices.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder().username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .role(Role.USER).build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
