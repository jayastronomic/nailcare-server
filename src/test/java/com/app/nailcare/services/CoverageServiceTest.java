package com.app.nailcare.services;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.models.Subscription;
import com.app.nailcare.models.User;
import com.app.nailcare.repositories.CoverageRepository;
import com.app.nailcare.security.AuthUserDetails;
import com.app.nailcare.services.CoverageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoverageServiceTest {

    @InjectMocks
    private CoverageService coverageService;

    @Mock
    private CoverageRepository coverageRepository;

    @Mock
    private AuthUserDetails authUserDetails;

    private static final UUID userId = UUID.randomUUID();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        authUserDetails = new AuthUserDetails(
                new User("test@gmail.com", "password")
        );

        // Mock the behavior of authUserDetails to return a mock User with a mock Subscription
        when(authUserDetails.user()).thenReturn(new User("test@gmail.com", "password"));
        when(authUserDetails.user().getSubscription()).thenReturn(new Subscription());

        Authentication authentication = new UsernamePasswordAuthenticationToken(authUserDetails, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testIndexCoverages() {
        // Create some sample coverages
        Coverage coverage1 = new Coverage();
        Coverage coverage2 = new Coverage();
        List<Coverage> expectedCoverages = List.of(coverage1, coverage2);

        // Stub the behavior of coverageRepository to return the expected coverages
        when(coverageRepository.findAll()).thenReturn(expectedCoverages);

        // Call the method to be tested
        List<Coverage> retrievedCoverages = coverageService.index();

        // Verify that the repository method was called
        verify(coverageRepository).findAll();

        // Verify that the returned coverages match the expected coverages
        assertEquals(expectedCoverages, retrievedCoverages);
    }

    @Test
    void testShowCoverage() {
        // Create a sample coverage and an ID
        UUID coverageId = UUID.randomUUID();
        Coverage expectedCoverage = new Coverage();

        // Stub the behavior of coverageRepository to return the expected coverage when findById is called with the given ID
        when(coverageRepository.findById(coverageId)).thenReturn(Optional.of(expectedCoverage));

        // Call the method to be tested
        Coverage retrievedCoverage = coverageService.show(coverageId);

        // Verify that the repository method was called with the correct ID
        verify(coverageRepository).findById(coverageId);

        // Verify that the returned coverage matches the expected coverage
        assertEquals(expectedCoverage, retrievedCoverage);
    }

    @Test
    void testGetUserCoverage() {
        // Create a mock Coverage and mock Subscription
        Coverage mockCoverage = new Coverage();
        Subscription mockSubscription = new Subscription();
        mockSubscription.setCoverage(mockCoverage);

        // Stub the behavior of authUserDetails to return the mock User with the mock Subscription
        when(authUserDetails.user().getSubscription()).thenReturn(mockSubscription);

        // Call the method to be tested
        Coverage userCoverage = coverageService.getUserCoverage();

        // Verify that the returned coverage matches the mock Coverage from the Subscription
        assertEquals(mockCoverage, userCoverage);
    }
}
