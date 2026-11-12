public class Manager extends Employee {
    private static final long serialVersionUID = 1L;
    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        if (bonus < 0) throw new IllegalArgumentException("Bonus can't be negative.");
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() { return getBaseSalary() + bonus; }

    @Override
    public String getRole() { return "Manager"; }
}