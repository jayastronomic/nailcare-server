package com.app.nailcare.seed;

import com.app.nailcare.models.Coverage;
import com.app.nailcare.repositories.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CoverageRepository coverageRepository;

    @Autowired
    public DataSeeder(CoverageRepository coverageRepository) {
        this.coverageRepository = coverageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert sample coverage plans
        Coverage basicPlan = new Coverage("Basic Plan", "Basic coverage for 1 nail.", 1, 10.99, new ArrayList<>());
        Coverage standardPlan = new Coverage("Standard Plan", "Standard coverage for 2 nails.", 2, 15.99, new ArrayList<>());
        Coverage premiumPlan = new Coverage("Premium Plan",  "Premium coverage for all nails.", 10, 29.99, new ArrayList<>());

        coverageRepository.save(basicPlan);
        coverageRepository.save(standardPlan);
        coverageRepository.save(premiumPlan);
    }
}