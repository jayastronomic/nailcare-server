package com.app.nailcare.repositories;

import com.app.nailcare.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The ClaimRepository interface defines data access operations for managing insurance claims in the NailCare application.
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, UUID> {

    /**
     * Retrieve a list of claims associated with a specific user by their unique identifier.
     *
     * @param id The unique identifier of the user for whom claims are to be retrieved.
     * @return An Optional containing a list of claims or an empty Optional if no claims are found.
     */
    Optional<List<Claim>> findByUser_Id(UUID id);
}