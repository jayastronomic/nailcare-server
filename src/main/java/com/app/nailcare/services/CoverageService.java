package com.app.nailcare.services;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.repositories.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverageService {
    private final CoverageRepository coverageRepository;

    @Autowired
    public CoverageService(CoverageRepository coverageRepository){
        this.coverageRepository = coverageRepository;
    }


    public List<Coverage> index(){
        return this.coverageRepository.findAll();
    }
}
