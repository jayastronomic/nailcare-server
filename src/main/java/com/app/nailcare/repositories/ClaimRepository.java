package com.app.nailcare.repositories;

import com.app.nailcare.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, UUID> {
    Optional<List<Claim>> findByUser_Id(UUID id);
}
