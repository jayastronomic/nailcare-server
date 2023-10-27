package com.app.nailcare.repositories;

import com.app.nailcare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The UserRepository interface defines data access operations for managing user entities in the NailCare application.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieve a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return An Optional containing the user with the specified email or an empty Optional if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user with a given email address exists.
     *
     * @param email The email address to check.
     * @return true if a user with the specified email exists, otherwise false.
     */
    boolean existsByEmail(String email);
}

