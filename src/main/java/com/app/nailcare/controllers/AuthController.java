package com.app.nailcare.controllers;

import com.app.nailcare.models.User;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.responses.AuthUser;
import com.app.nailcare.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Constructs an AuthController with the provided AuthService.
     *
     * @param authService The AuthService responsible for handling authentication operations.
     */
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


    @GetMapping("/loggedIn")
    public ResponseEntity<APIResponse<User>> isLoggedIn(){
        return ResponseEntity.ok(new APIResponse<>(authService.isLoggedIn(), "success"));
    }
}
