import java.io.*;
// saves data to disk and loads them using java.io and Serializable to make data savable 
public class DataManager implements Serializable {
    private static final String FILE_NAME = "shopData.dat";

    // saves the inventory to disk
    public void save(InventoryClass inventory) {
        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(inventory);
            System.out.println("Data saved.");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    // loads the inventory from disk, or returns a new empty one if there is no file yet
    public InventoryClass load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new InventoryClass();   // first run
        }

        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(file))) {
            return (InventoryClass) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load data: " + e.getMessage());
            return new InventoryClass();
        }
    }
}