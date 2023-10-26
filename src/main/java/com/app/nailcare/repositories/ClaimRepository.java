package com.app.nailcare.repositories;

import com.app.nailcare.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, UUID> { }
