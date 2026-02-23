import java.util.Optional;

public interface EligibilityRule {
    // Returns an empty Optional if eligible, or a String reason if not eligible.
    Optional<String> check(StudentProfile s);
}