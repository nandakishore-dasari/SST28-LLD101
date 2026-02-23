import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricing roomPricing;
    private final AddOnPricing addOnPricing;

    public HostelFeeCalculator(FakeBookingRepo repo, RoomPricing rp, AddOnPricing ap) {
        this.repo = repo;
        this.roomPricing = rp;
        this.addOnPricing = ap;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = roomPricing.getBaseRate(req.roomType);
        
        for (AddOn a : req.addOns) {
            total = total.plus(addOnPricing.getPrice(a));
        }
        
        return total;
    }
}