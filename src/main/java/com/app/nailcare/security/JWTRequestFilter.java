package com.app.nailcare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The JWTRequestFilter class is responsible for filtering and processing JWT-based authentication in the NailCare application.
 * It extends the OncePerRequestFilter provided by Spring Security.
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    Logger logger = Logger.getLogger(JWTRequestFilter.class.getName());

    private AuthUserDetailsService authUserDetailsService;
    private JWTUtils jwtUtils;

    @Autowired
    public void setAuthUserDetailsService(AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Parse the JWT token from the request's Authorization header.
     *
     * @param request The HTTP request containing the JWT token in the Authorization header.
     * @return The extracted JWT token or null if not found.
     */
    public String parseJWT(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        }
        logger.info("No header");
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJWT(request);
            if (jwt != null && jwtUtils.validateJwt(jwt)) {
                String username = jwtUtils.getUsernameFromJwt(jwt);
                UserDetails userDetails = this.authUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            logger.info("Cannot set user authentication token");
        }
        filterChain.doFilter(request, response);
    }
}
