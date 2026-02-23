import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final InputParser parser = new InputParser();
    private final Validator validator = new Validator();

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
    }

    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String, String> data = parser.parse(raw);
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord student = new StudentRecord(
            id, 
            data.get("name"), 
            data.get("email"), 
            data.get("phone"), 
            data.get("program")
        );

        repository.save(student);

        // UI/Printing logic (In a larger app, this would be in a Presenter/View)
        displaySuccess(student);
    }

    private void displaySuccess(StudentRecord rec) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + repository.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}