package com.app.nailcare.controllers;

import com.app.nailcare.models.Claim;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {
    private final ClaimService claimService;

    @Autowired
    public ClaimController(ClaimService claimService){
        this.claimService = claimService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse<Claim>> create(@Valid @RequestBody Claim payload){
        return  ResponseEntity
                .created(URI.create("/api/v1/claims"))
                .body(new APIResponse<>(claimService.create(payload), "success"));

    }
}
