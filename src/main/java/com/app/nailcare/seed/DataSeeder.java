import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Coverage basicPlan = new Coverage("Basic Plan", 1, 10.99, "Basic coverage for 1 nail.", true, "Nail Insurance");
        Coverage standardPlan = new Coverage("Standard Plan", 2, 15.99, "Standard coverage for 2 nails.", true, "Nail Insurance");
        Coverage premiumPlan = new Coverage("Premium Plan", 10, 29.99, "Premium coverage for all nails.", true, "Nail Insurance");

        coverageRepository.save(basicPlan);
        coverageRepository.save(standardPlan);
        coverageRepository.save(premiumPlan);
    }
}