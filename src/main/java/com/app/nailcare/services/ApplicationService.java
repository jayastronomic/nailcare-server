package com.app.nailcare.services;

import com.app.nailcare.models.User;
import com.app.nailcare.security.AuthUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    public static User currentUser(){
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return authUserDetails.user();
    }
}
