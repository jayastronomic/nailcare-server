package com.app.nailcare.services;

import com.app.nailcare.models.Claim;
import com.app.nailcare.models.User;
import com.app.nailcare.repositories.ClaimRepository;
import com.app.nailcare.security.AuthUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimService claimService;

    private static final UUID userId = UUID.randomUUID();
    private AuthUserDetails authUserDetails;

    @BeforeEach
    void setup() {
        authUserDetails = new AuthUserDetails(
                new User("test@gmail.com", "password")
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(authUserDetails, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testCreateClaim() {
        // Create a mock Claim with the required attributes
        Claim mockClaim = new Claim();
        mockClaim.setClaimDate(LocalDate.now());
        mockClaim.setDescription("Test Description");
        mockClaim.setAffectedNails("Test Nails");
        mockClaim.setClaimAmount(100.0);
        mockClaim.setClaimStatus("Review");

        // Mock the behavior of claimRepository to return the mockClaim when saving
        when(claimRepository.save(any(Claim.class))).thenReturn(mockClaim);

        // Call the method to create a Claim
        Claim newClaim = claimService.create(mockClaim);

        // Verify that the returned Claim matches the expected values
        assertEquals(mockClaim.getClaimDate(), newClaim.getClaimDate());
        assertEquals(mockClaim.getDescription(), newClaim.getDescription());
        assertEquals(mockClaim.getAffectedNails(), newClaim.getAffectedNails());
        assertEquals(mockClaim.getClaimAmount(), newClaim.getClaimAmount());
        assertEquals(mockClaim.getClaimStatus(), newClaim.getClaimStatus());

        // Verify that the save method of claimRepository was called with the correct Claim
        verify(claimRepository).save(any(Claim.class));

    }


    @Test
    void testIndexClaims() {
        // Create a mock User with claims
        User userWithClaims = new User();
        Claim mockClaim = new Claim();
        mockClaim.setClaimStatus("Review");
        userWithClaims.setClaims(Collections.singletonList(mockClaim));

        // Mock the behavior of claimRepository to return the expected claims when findByUser_Id is called
        when(claimRepository.findByUser_Id(authUserDetails.user().getId())).thenReturn(Optional.of(userWithClaims.getClaims()));

        // Call the method to be tested
        List<Claim> claims = claimService.index();

        // Verify the repository method was called
        verify(claimRepository).findByUser_Id(authUserDetails.user().getId());

        // Verify the behavior and assertions
        assertEquals(1, claims.size());
        assertEquals("Review", claims.get(0).getClaimStatus());
    }
}
