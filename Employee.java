import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;

    private final int id;
    private String name;
    private double baseSalary;

    public Employee(String name, double baseSalary) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be empty.");
        }
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Salary can't be negative.");
        }
        this.id = nextId++;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    protected double getBaseSalary() { return baseSalary; }

    public double calculateSalary() { return baseSalary; }
    public String getRole() { return "Employee"; }

    // called after loading from disk so new ids don't repeat old ones
    public static void syncNextId(int highestId) {
        if (highestId >= nextId) nextId = highestId + 1;
    }

    @Override
    public String toString() {
        return getRole() + " #" + id + " | " + name + " | Salary: " + calculateSalary();
    }
}