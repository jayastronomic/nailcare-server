package com.app.nailcare.controllers;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.responses.APIResponse;
import com.app.nailcare.services.CoverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CoverageController {
    private final CoverageService coverageService;

    /**
     * Constructs a CoverageController with the provided CoverageService.
     *
     * @param coverageService The CoverageService responsible for managing insurance coverage.
     */
    @Autowired
    public CoverageController(CoverageService coverageService){
        this.coverageService = coverageService;
    }

    /**
     * Endpoint for retrieving a list of available insurance coverages.
     *
     * @return A ResponseEntity containing an APIResponse with a success message and a list of available insurance coverages.
     */
    @GetMapping("/coverages")
    public ResponseEntity<APIResponse<List<Coverage>>> index(){
        return ResponseEntity.ok(new APIResponse<>(coverageService.index(), "success"));
    }

    /**
     * Endpoint for retrieving information about a specific insurance coverage by its unique identifier.
     *
     * @param id The unique identifier of the coverage to retrieve.
     * @return A ResponseEntity containing an APIResponse with a success message and information about the requested insurance coverage.
     */
    @GetMapping("/coverages/{id}")
    public ResponseEntity<APIResponse<Coverage>> show(@PathVariable(value = "id") UUID id){
        return  ResponseEntity.ok(new APIResponse<>(coverageService.show(id), "success"));
    }

    /**
     * Endpoint for retrieving insurance coverage information for a user.
     *
     * @return A ResponseEntity containing an APIResponse with a success message and the insurance coverage information for the user.
     */
    @GetMapping("/users/coverages")
    public ResponseEntity<APIResponse<Coverage>> getUserCoverage(){
        return  ResponseEntity.ok(new APIResponse<>(coverageService.getUserCoverage(), "success"));
    }

}
