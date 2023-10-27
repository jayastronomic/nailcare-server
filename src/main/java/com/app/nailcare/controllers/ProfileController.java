package com.app.nailcare.controllers;

import com.app.nailcare.models.Profile;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    /**
     * Constructs a ProfileController with the provided ProfileService.
     *
     * @param profileService The ProfileService responsible for managing user profiles.
     */
    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }


    /**
     * Endpoint for creating a new user profile.
     *
     * @param payload The Profile object containing details of the new user profile.
     * @return A ResponseEntity containing an APIResponse with a success message and the result of the profile creation.
     */
    @PostMapping
    public ResponseEntity<APIResponse<Profile>> create(@Valid @RequestBody Profile payload){
        return ResponseEntity
                .created(URI.create("/api/v1/profiles"))
                .body(new APIResponse<>(profileService.create(payload), "success"));
    }
}
