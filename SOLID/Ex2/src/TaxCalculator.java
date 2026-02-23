public interface TaxCalculator {
    double calculateTax(String customerType, double subtotal);
    double getTaxRate(String customerType);
}