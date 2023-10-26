package com.app.nailcare.services;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.models.Subscription;
import com.app.nailcare.repositories.CoverageRepository;
import com.app.nailcare.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscriptionService extends ApplicationService {
    private final SubscriptionRepository subscriptionRepository;
    private final CoverageRepository coverageRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, CoverageRepository coverageRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.coverageRepository = coverageRepository;
    }


    public Subscription create(UUID id){
        Coverage coverage = coverageRepository.findById(id).orElseThrow();
        Subscription newSubscription = new Subscription();
        newSubscription.setCoverage(coverage);
        newSubscription.setUser(currentUser());
        return subscriptionRepository.save(newSubscription);
    }
}
