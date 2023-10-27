package com.app.nailcare.services;

import com.app.nailcare.models.Profile;
import com.app.nailcare.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The ProfileService class provides services related to user profiles in the NailCare application.
 */
@Service
public class ProfileService extends ApplicationService {
    public final ProfileRepository profileRepository;

    /**
     * Create a ProfileService instance with the specified ProfileRepository.
     *
     * @param profileRepository The repository for managing user profiles.
     */
    @Autowired
    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    /**
     * Create a user profile associated with the currently logged-in user.
     *
     * @param payload The user profile data to be created.
     * @return The created user profile.
     */
    public Profile create(Profile payload){
        payload.setUser(currentUser());
        return profileRepository.save(payload);
    }
}
