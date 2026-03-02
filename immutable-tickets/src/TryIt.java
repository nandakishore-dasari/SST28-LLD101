import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.List;

public class TryIt {
    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing");
        System.out.println("Initial: " + t1);

        // Service returns NEW instances
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        IncidentTicket t3 = service.escalateToCritical(t2);

        System.out.println("After updates (t1): " + t1); // Unchanged
        System.out.println("Final Result (t3): " + t3);

        // Demonstrate protection against list mutation
        try {
            List<String> tags = t3.getTags();
            tags.add("HACK");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccess: Internal tags list is immutable!");
        }
    }
}