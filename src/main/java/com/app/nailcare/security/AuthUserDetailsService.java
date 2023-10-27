package com.app.nailcare.security;

import com.app.nailcare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The AuthUserDetailsService class is responsible for loading user details for authentication and authorization in the NailCare application.
 * It implements the UserDetailsService interface from Spring Security.
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {
    public final UserRepository userRepository;

    @Autowired
    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load user details by their email address and create an AuthUserDetails object.
     *
     * @param email The email address of the user to load.
     * @return An AuthUserDetails object representing the user's details for authentication and authorization.
     * @throws UsernameNotFoundException if the user is not found by the provided email.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AuthUserDetails(userRepository.findByEmail(email).orElseThrow());
    }
}
