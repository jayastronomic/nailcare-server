package com.app.nailcare.services;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.repositories.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoverageService extends ApplicationService {
    private final CoverageRepository coverageRepository;

    @Autowired
    public CoverageService(CoverageRepository coverageRepository){
        this.coverageRepository = coverageRepository;
    }


    public List<Coverage> index(){
        return coverageRepository.findAll();
    }

    public Coverage show(UUID id){
        return coverageRepository.findById(id).orElseThrow();
    }

    public Coverage getUserCoverage(){ return currentUser().getSubscription().getCoverage() ;}
}
