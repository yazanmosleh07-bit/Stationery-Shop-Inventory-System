import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;          // shared counter, gives each product a unique id

    private String name;
    private double price;
    private int id;
    private int quantity;

    //constructors
    public Product() {
        this.id = nextId++;
    }

    public Product(String name, double price, int quantity) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int q) { quantity = q; }

    // methods that control the quantity
    public void addStock(int amount) {
        quantity += amount;
    }

    public void removeStock(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
        } else {
            System.out.println("Can not remove " + amount + ", only " + quantity + " in stock.");
        }
    }

    // price for buying qty units (does NOT change stock)
    public double calculatePrice(int qty) {
        return price * qty;
    }

    public void getInfo() {
        System.out.println("Name: " + name + " | Price: " + price + " | Quantity: " + quantity);
    }
}