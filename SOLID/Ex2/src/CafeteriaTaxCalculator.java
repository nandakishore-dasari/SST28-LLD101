public class CafeteriaTaxCalculator implements TaxCalculator {
    @Override
    public double getTaxRate(String type) {
        if ("student".equalsIgnoreCase(type)) return 5.0;
        if ("staff".equalsIgnoreCase(type)) return 2.0;
        return 8.0;
    }

    @Override
    public double calculateTax(String type, double subtotal) {
        return subtotal * (getTaxRate(type) / 100.0);
    }
}