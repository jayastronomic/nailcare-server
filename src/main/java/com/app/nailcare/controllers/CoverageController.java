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
@RequestMapping("/api/v1/coverages")
public class CoverageController {
    private final CoverageService coverageService;

    @Autowired
    public CoverageController(CoverageService coverageService){
        this.coverageService = coverageService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Coverage>>> index(){
        return ResponseEntity.ok(new APIResponse<>(coverageService.index(), "success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Coverage>> show(@PathVariable(value = "id") UUID id){
        return  ResponseEntity.ok(new APIResponse<>(coverageService.show(id), "success"));
    }
}
