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


    /**
     * Endpoint for user registration (signup).
     *
     * @param payload The User object containing user registration details.
     * @return A ResponseEntity containing an APIResponse with a success message and the result of the registration.
     */
    @PostMapping("/signup")
    public ResponseEntity<APIResponse<String>> create(@Valid @RequestBody User payload){
        return ResponseEntity
                .created(URI.create("/api/v1/auth/signup"))
                .body(new APIResponse<>(authService.create(payload), "success"));
    }



    /**
     * Endpoint for user login.
     *
     * @param payload The User object containing user login credentials.
     * @return A ResponseEntity containing an APIResponse with a success message and the result of the login operation.
     */
    @PostMapping("/login")
    public ResponseEntity<APIResponse<User>> login(@RequestBody User payload){
        return ResponseEntity
                .ok(new APIResponse<>(authService.login(payload), "success"));
    }


    /**
     * Endpoint to check if a user is logged in and retrieve the user's information.
     *
     * @return A ResponseEntity containing an APIResponse with a success message and the user's information if logged in.
     */
    @GetMapping("/loggedIn")
    public ResponseEntity<APIResponse<User>> isLoggedIn(){
        return ResponseEntity.ok(new APIResponse<>(authService.isLoggedIn(), "success"));
    }
}
