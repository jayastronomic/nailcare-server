package com.app.nailcare.controllers;

import com.app.nailcare.models.User;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<APIResponse<String>> create(@Valid @RequestBody User payload){
        return ResponseEntity
                .created(URI.create("/api/v1/auth/signup"))
                .body(new APIResponse<>(authService.create(payload), "success"));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<User>> login(@RequestBody User payload){
        return ResponseEntity
                .ok(new APIResponse<>(authService.login(payload), "success"));
    }



}
