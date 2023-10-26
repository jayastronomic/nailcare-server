package com.app.nailcare.services;

import com.app.nailcare.models.Profile;
import com.app.nailcare.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService extends ApplicationService {
    public final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile payload){
        payload.setUser(currentUser());
        return  profileRepository.save(payload);
    }
}
