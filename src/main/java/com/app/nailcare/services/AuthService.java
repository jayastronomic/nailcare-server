package com.app.nailcare.services;

import com.app.nailcare.exceptions.AlreadyExistException;
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

@Service
public class AuthService {
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

    public String create(User userObject) throws AlreadyExistException {
        boolean exists = userRepository.existsByEmail(userObject.getEmail());
        if (exists) throw new AlreadyExistException(User.class, "email", userObject.getEmail());
        userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
        String jwt = jwtUtils.generateJwtToken(new AuthUserDetails(userObject));
        userRepository.save(userObject);
        return jwt;
    }


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
