public class Cashier extends Employee {
    private static final long serialVersionUID = 1L;
    private int salesCount;

    public Cashier(String name, double baseSalary) {
        super(name, baseSalary);
    }

    public void recordSale() { salesCount++; }
    public int getSalesCount() { return salesCount; }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + salesCount * 0.5;   // small bonus per sale
    }

    @Override
    public String getRole() { return "Cashier"; }
}