package com.app.nailcare.repositories;

import com.app.nailcare.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The SubscriptionRepository interface defines data access operations for managing user subscriptions in the NailCare application.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
