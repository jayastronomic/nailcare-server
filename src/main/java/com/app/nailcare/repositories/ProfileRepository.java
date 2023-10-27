package com.app.nailcare.repositories;

import com.app.nailcare.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The ProfileRepository interface defines data access operations for managing user profiles in the NailCare application.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
