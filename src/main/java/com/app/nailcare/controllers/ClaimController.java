package com.app.nailcare.controllers;

import com.app.nailcare.models.Claim;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {
    private final ClaimService claimService;
    /**
     * Constructs a ClaimController with the provided ClaimService.
     *
     * @param claimService The ClaimService responsible for managing insurance claims.
     */
    @Autowired
    public ClaimController(ClaimService claimService){
        this.claimService = claimService;
    }

    /**
     * Endpoint for creating a new insurance claim.
     *
     * @param payload The Claim object containing details of the new claim.
     * @return A ResponseEntity containing an APIResponse with a success message and the result of the claim creation.
     */

    @PostMapping
    public ResponseEntity<APIResponse<Claim>> create(@Valid @RequestBody Claim payload){
        return  ResponseEntity
                .created(URI.create("/api/v1/claims"))
                .body(new APIResponse<>(claimService.create(payload), "success"));

    }



    /**
     * Endpoint for retrieving a list of insurance claims.
     *
     * @return A ResponseEntity containing an APIResponse with a success message and a list of insurance claims.
     */
    @GetMapping
    public ResponseEntity<APIResponse<List<Claim>>> index(){
        return  ResponseEntity.ok(new APIResponse<>(claimService.index(), "success"));
    }
}
