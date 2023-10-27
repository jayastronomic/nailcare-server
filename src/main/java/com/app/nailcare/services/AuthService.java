package com.app.nailcare.services;

import com.app.nailcare.exceptions.AlreadyExistException;
import com.app.nailcare.exceptions.UserNotLoggedInException;
import com.app.nailcare.models.User;
import com.app.nailcare.repositories.UserRepository;
import com.app.nailcare.security.AuthUserDetails;
import com.app.nailcare.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * The AuthService class provides authentication and user-related services in the NailCare application.
 */
@Service
public class AuthService extends ApplicationService {
    private final Logger logger = Logger.getLogger(AuthService.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Create a new user and return a JWT token upon successful registration.
     *
     * @param userObject The user object to be created and registered.
     * @return The JWT token generated upon successful registration.
     * @throws AlreadyExistException if a user with the same email already exists.
     */
    public String create(User userObject) throws AlreadyExistException {
        boolean exists = userRepository.existsByEmail(userObject.getEmail());
        if (exists) throw new AlreadyExistException(User.class, "email", userObject.getEmail());
        userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
        String jwt = jwtUtils.generateJwtToken(new AuthUserDetails(userObject));
        userRepository.save(userObject);
        return jwt;
    }

    /**
     * Check if a user is currently logged in and return their user details.
     *
     * @return The user details of the currently logged-in user.
     * @throws UserNotLoggedInException if no user is currently logged in.
     */
    public User isLoggedIn() throws UserNotLoggedInException {
        if(currentUser() == null) throw new UserNotLoggedInException("User is not logged in. Please log in!");
        return currentUser();
    }

    /**
     * Authenticate a user based on their login credentials and return their user details.
     *
     * @param payload The user login credentials (email and password).
     * @return The user details of the authenticated user.
     * @throws RuntimeException if the login credentials are invalid.
     */
    public User login(User payload)  {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateJwtToken(authUserDetails);
            return authUserDetails.user();
        } catch (Exception e) {
            throw new RuntimeException("Invalid email/password");
        }
    }
}
