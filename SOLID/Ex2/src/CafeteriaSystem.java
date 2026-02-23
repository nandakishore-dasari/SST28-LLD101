import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repository;
    private final TaxCalculator taxCalc;
    private final DiscountProvider discountProv;
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceRepository repo, TaxCalculator tc, DiscountProvider dp) {
        this.repository = repo;
        this.taxCalc = tc;
        this.discountProv = dp;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            subtotal += menu.get(l.itemId).price * l.qty;
        }

        double taxPct = taxCalc.getTaxRate(customerType);
        double tax = taxCalc.calculateTax(customerType, subtotal);
        double discount = discountProv.calculateDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = formatter.format(invId, lines, menu, subtotal, taxPct, tax, discount, total);
        
        System.out.print(printable);
        repository.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + repository.countLines(invId) + ")");
    }
}