package com.app.nailcare.repositories;

import com.app.nailcare.models.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The CoverageRepository interface defines data access operations for managing insurance coverages in the NailCare application.
 */
@Repository
public interface CoverageRepository extends JpaRepository<Coverage, UUID> {}
