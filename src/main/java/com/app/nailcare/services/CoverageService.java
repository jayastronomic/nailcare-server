package com.app.nailcare.services;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.repositories.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The CoverageService class provides services related to insurance coverage in the NailCare application.
 */
@Service
public class CoverageService extends ApplicationService {
    private final CoverageRepository coverageRepository;

    /**
     * Create a CoverageService instance with the specified CoverageRepository.
     *
     * @param coverageRepository The repository for managing insurance coverage data.
     */
    @Autowired
    public CoverageService(CoverageRepository coverageRepository){
        this.coverageRepository = coverageRepository;
    }

    /**
     * Retrieve a list of all available insurance coverage options.
     *
     * @return A list of all available insurance coverage options.
     */
    public List<Coverage> index(){
        return coverageRepository.findAll();
    }

    /**
     * Retrieve details of a specific insurance coverage option based on its unique identifier (ID).
     *
     * @param id The unique identifier of the insurance coverage option to retrieve.
     * @return The details of the specified insurance coverage option.
     */
    public Coverage show(UUID id){
        return coverageRepository.findById(id).orElseThrow();
    }

    /**
     * Retrieve the insurance coverage associated with the currently logged-in user's subscription.
     *
     * @return The insurance coverage associated with the user's subscription.
     */
    public Coverage getUserCoverage(){
        return currentUser().getSubscription().getCoverage();
    }
}
