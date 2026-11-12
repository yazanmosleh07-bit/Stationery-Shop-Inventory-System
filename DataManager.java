import java.io.*;

public class DataManager {
    private static final String FILE_NAME = "shop.dat";

    // saves the whole shop (inventory + staff) to disk
    public void save(Shop shop) {
        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(shop);
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    // loads the shop from disk, or returns a new empty shop if there is no file yet
    public Shop load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new Shop();   // first run
        }

        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(file))) {
            return (Shop) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load data: " + e.getMessage());
            return new Shop();
        }
    }
}