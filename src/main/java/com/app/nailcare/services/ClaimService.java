package com.app.nailcare.services;


import com.app.nailcare.models.Claim;
import com.app.nailcare.repositories.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService extends  ApplicationService{
    private final ClaimRepository claimRepository;


    public ClaimService(ClaimRepository claimRepository){
        this.claimRepository = claimRepository;
    }

    public Claim create(Claim payload){
        payload.setClaimStatus("Review");
        payload.setUser(currentUser());
        payload.setSubscription(currentUser().getSubscription());
        return claimRepository.save(payload);
    }

    public List<Claim> index(){
        return claimRepository.findByUser_Id(currentUser().getId()).orElseThrow();
    }
}
