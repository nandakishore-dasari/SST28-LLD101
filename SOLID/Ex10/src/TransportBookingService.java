public class TransportBookingService {
    private final IDistanceCalculator distanceCalculator;
    private final IDriverAllocator driverAllocator;
    private final IPaymentGateway paymentGateway;

    public TransportBookingService(IDistanceCalculator dc, IDriverAllocator da, IPaymentGateway pg) {
        this.distanceCalculator = dc;
        this.driverAllocator = da;
        this.paymentGateway = pg;
    }

    public void book(TripRequest req) {
        double km = distanceCalculator.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverAllocator.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        // Pricing logic (can be extracted further if needed)
        double fare = 50.0 + km * 6.6666666667;
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentGateway.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}