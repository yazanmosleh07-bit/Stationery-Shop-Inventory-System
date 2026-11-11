import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    // the variable that stores the user's input
    public static final Scanner input = new Scanner(System.in);
    // a arraylist variable that holds all the products and let it shrink and grow 
    public static final InventoryClass Stock = new InventoryClass();



    public static void main(String[] args) {
        int choice;
        // stores the state
        boolean loopRunning = true;

        // main loop
        while (loopRunning) {
            startProgram();
            try{
            choice = input.nextInt();
            input.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Please Enter A Valid Input");
                input.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("selling a product...");
                    sellProduct();
                    continuePromt();
                    break;
                case 2:
                    restock();
                    continuePromt();
                    break;
                case 3:
                    checkStock();
                    continuePromt();
                    break;
                case 4:
                    AddToStock();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    loopRunning = false;
                    break;
                default:
                    System.out.println(" ");
                    System.out.println("Invalid option, choose 0-4.");
                    continuePromt();
                    break;
            }

        }
        
        

        input.close();
    }

    // helpers so taking inputs always checks for errors or negative numbers
    public static int readInt(String prompt) {
    while (true) {
        System.out.print(prompt);
        try {
            int value = input.nextInt();
            input.nextLine();
            if (value < 0) {
                System.out.println("Value can't be negative.");
                continue;
            }
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            input.nextLine();
        }
    }
    }

    public static void restock(){
        checkStock();
        int index = readInt("Product number to restock: ") - 1;
        
        int qty = readInt("Quantity to buy: ");

        // get the chosen product from the list
        Product p = Stock.getStockIndex(index);

        p.setQuantity(p.getQuantity() + qty);

        System.out.println("Bought " + qty + " x " + p.getName());
        
    }
    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = input.nextDouble();
                input.nextLine();
                if (value < 0) {
                    System.out.println("Value can't be negative.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    // prints out the main menu of the CLI
    public static void startProgram(){
        System.out.println("=========================================");
        System.out.println("      STATIONERY SHOP MANAGEMENT");
        System.out.println("=========================================");
        System.out.println("  Welcome! What would you like to do?");
        System.out.println();
        System.out.println("  1. Sell a product      (customer sale)");
        System.out.println("  2. Buy stock           (restock from supplier)");
        System.out.println("  3. Check stock");
        System.out.println("  4. Add to stock");
        System.out.println();
        System.out.println("  0. Exit");
        System.out.println("-----------------------------------------");
        System.out.print("  Enter your choice: ");
        }

    
    // take a product class object and adds it to the arraylist Stock
    public static void addToList(Product prod){
        Stock.addProduct(prod);
    }

    public static void continuePromt(){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Press Enter To Continue");
        System.out.println(" ");
        System.out.println(" ");
        input.nextLine();
    }


    // creates a product then adds it to the stock
    public static void createProduct() {
    System.out.print("Enter the name: ");
    String name = input.nextLine();
    double price = readDouble("Enter the price: ");
    int quantity = readInt("Enter quantity: ");

    Product p = new Product(name, price, quantity);
    Stock.addProduct(p);
    System.out.printf("Added: %s | Price: %.2f | Quantity: %d%n", name, price, quantity);
    }



    
    public static void checkStock() {
        if (Stock.isEmpty()) {
            System.out.println("Stock is empty.");
            return;
        }

        for (int i = 0; i < Stock.getStockSize(); i++) {
            System.out.println("========================================");
            System.out.print((i + 1) + " ");
            Stock.getStockIndex(i).getInfo();
            System.out.println("========================================");
        }
    }


    // sells a product: shows the list, asks which product and how many, then reduces its quantity
    public static void sellProduct() {
        // nothing to sell if the list has no products
        if (Stock.isEmpty()) {
            System.out.println("Nothing to sell, stock is empty.");
            return;
        }

        // display all products so the user can pick one by number
        checkStock();

        // the menu shows 1..n but the ArrayList is 0-based, so subtract 1 to get the index
        int index = readInt("Product number to sell: ") - 1;

        // reject numbers outside the list (0, negatives, or bigger than the list size)
        if (index < 0 || index >= Stock.getStockSize()) {
            System.out.println("Invalid product number.");
            return;
        }

        // how many units the customer is buying
        int qty = readInt("Quantity to sell: ");

        // get the chosen product from the list
        Product p = Stock.getStockIndex(index);

        // can't sell more than what we have
        if (qty > p.getQuantity()) {
            System.out.println("Not enough stock. Available: " + p.getQuantity());
            return;
        }

        // subtract the sold amount from the product's quantity
        p.calculatePrice(qty);

        // confirm the sale to the user
        System.out.println("Sold " + qty + " x " + p.getName());
    }
    public static void AddToStock(){
        createProduct();
    }
}

