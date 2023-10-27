package com.app.nailcare.services;


import com.app.nailcare.models.Claim;
import com.app.nailcare.repositories.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The ClaimService class provides services related to insurance claims in the NailCare application.
 */
@Service
public class ClaimService extends ApplicationService {
    private final ClaimRepository claimRepository;

    /**
     * Create a ClaimService instance with the specified ClaimRepository.
     *
     * @param claimRepository The repository for managing insurance claims.
     */
    public ClaimService(ClaimRepository claimRepository){
        this.claimRepository = claimRepository;
    }

    /**
     * Create a new insurance claim and set its initial status to "Review."
     *
     * @param payload The insurance claim data to be created.
     * @return The created insurance claim with the "Review" status.
     */
    public Claim create(Claim payload){
        payload.setClaimStatus("Review");
        payload.setUser(currentUser());
        payload.setSubscription(currentUser().getSubscription());
        return claimRepository.save(payload);
    }

    /**
     * Retrieve a list of insurance claims associated with the currently logged-in user.
     *
     * @return A list of insurance claims belonging to the current user.
     */
    public List<Claim> index(){
        return claimRepository.findByUser_Id(currentUser().getId()).orElseThrow();
    }
}
