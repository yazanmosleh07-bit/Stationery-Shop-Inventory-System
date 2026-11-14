import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {
    //a version ID that keeps saved files compatible when I update the classes
    private static final long serialVersionUID = 1L;
    private InventoryClass inventory = new InventoryClass();
    private ArrayList<Employee> staff = new ArrayList<>();


    // class getters
    public InventoryClass getInventory() { return inventory; }
    public int getStaffSize() { return staff.size(); }
    public Employee getEmployee(int i) { return staff.get(i); }

    public void addEmployee(Employee e) { staff.add(e); }
    public boolean removeEmployeeAt(int i) {
        if (i < 0 || i >= staff.size()) return false;
        staff.remove(i);
        return true;
    }

    // keep employee ids unique after loading a saved shop
    public void syncIds() {
        int highest = 0;
        for (Employee e : staff) highest = Math.max(highest, e.getId());
        //Employee.syncNextId(highest);
    }
}