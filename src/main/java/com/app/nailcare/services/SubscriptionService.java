package com.app.nailcare.services;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.models.Subscription;
import com.app.nailcare.repositories.CoverageRepository;
import com.app.nailcare.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * The SubscriptionService class provides services related to user subscriptions in the NailCare application.
 */
@Service
public class SubscriptionService extends ApplicationService {
    private final SubscriptionRepository subscriptionRepository;
    private final CoverageRepository coverageRepository;

    /**
     * Create a SubscriptionService instance with the specified SubscriptionRepository and CoverageRepository.
     *
     * @param subscriptionRepository The repository for managing user subscriptions.
     * @param coverageRepository     The repository for managing insurance coverage options.
     */
    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, CoverageRepository coverageRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.coverageRepository = coverageRepository;
    }

    /**
     * Create a new user subscription with the specified coverage option.
     *
     * @param id The unique identifier of the selected insurance coverage option.
     * @return The created user subscription with the selected coverage.
     */
    public Subscription create(UUID id){
        Coverage coverage = coverageRepository.findById(id).orElseThrow();
        Subscription newSubscription = new Subscription();
        newSubscription.setCoverage(coverage);
        newSubscription.setUser(currentUser());
        return subscriptionRepository.save(newSubscription);
    }
}

