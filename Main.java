import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // the variable that stores the user's input
    public static final Scanner input = new Scanner(System.in);
    // loads and saves the whole shop (inventory + staff) from/to disk
    public static final DataManager dataManger = new DataManager();
    // the shop that owns the inventory and the employees
    public static Shop shop;
    // shortcut to the shop's inventory so the product methods stay short
    public static InventoryClass Stock;
    // stores the user's choice
    public static int choice;
    // stores the state of the main loop
    public static boolean running = true;

    public static void main(String[] args) {
        // loads save data from disk (or an empty shop on the first run)
        shop = dataManger.load();
        shop.syncIds();
        Stock = shop.getInventory();

        mainLoop();

        input.close();
    }

    // holds the main loop of the program and its actions
    public static void mainLoop() {
        while (running) {
            // prints out the start prompt
            startProgram();

            // catching errors
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please Enter A Valid Input");
                input.nextLine();
                continue;
            }

            takeChoice(choice);
        }
    }

    // runs the action that matches the user's menu choice
    public static void takeChoice(int option) {
        switch (option) {
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
            case 5:
                employeeMenu();
                break;
            case 0:
                System.out.println("Exiting the program...");
                System.out.println("Saving..");
                dataManger.save(shop);
                running = false;
                break;
            default:
                System.out.println(" ");
                System.out.println("Invalid option, choose 0-5.");
                continuePromt();
                break;
        }
    }

    // prints out the main menu of the CLI
    public static void startProgram() {
        System.out.println("=========================================");
        System.out.println("      STATIONERY SHOP MANAGEMENT");
        System.out.println("=========================================");
        System.out.println("  Welcome! What would you like to do?");
        System.out.println();
        System.out.println("  1. Sell a product      (customer sale)");
        System.out.println("  2. Buy stock           (restock from supplier)");
        System.out.println("  3. Check stock");
        System.out.println("  4. Add to stock");
        System.out.println("  5. Manage employees");
        System.out.println();
        System.out.println("  0. Exit");
        System.out.println("-----------------------------------------");
        System.out.print("  Enter your choice: ");
    }

    // ---------------------------------------------------------------
    // input helpers: always check for errors and negative numbers
    // ---------------------------------------------------------------
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

    public static void continuePromt() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Press Enter To Continue");
        System.out.println(" ");
        System.out.println(" ");
        input.nextLine();
    }

    // ---------------------------------------------------------------
    // products and stock
    // ---------------------------------------------------------------

    // takes a product object and adds it to the inventory
    public static void addToList(Product prod) {
        Stock.addProduct(prod);
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
        dataManger.save(shop);
    }

    public static void AddToStock() {
        createProduct();
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

    // restocks a product: shows the list, asks which product and how many, then adds to its quantity
    public static void restock() {
        if (Stock.isEmpty()) {
            System.out.println("Nothing to restock, stock is empty.");
            return;
        }

        checkStock();
        int index = readInt("Product number to restock: ") - 1;
        if (index < 0 || index >= Stock.getStockSize()) {
            System.out.println("Invalid product number.");
            return;
        }

        int qty = readInt("Quantity to buy: ");
        Product p = Stock.getStockIndex(index);

        p.addStock(qty);
        System.out.println("Bought " + qty + " x " + p.getName());

        dataManger.save(shop);
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

        // calculate the total, then subtract the sold amount from the stock
        double total = p.calculatePrice(qty);
        p.removeStock(qty);

        // confirm the sale to the user
        System.out.println("Sold " + qty + " x " + p.getName() + " | Total: " + total);
        dataManger.save(shop);
    }

    // ---------------------------------------------------------------
    // employees
    // ---------------------------------------------------------------
    public static void employeeMenu() {
        System.out.println("1. Add employee  2. List employees  3. Remove employee");
        int option = readInt("Choose: ");
        switch (option) {
            case 1:
                addEmployee();
                break;
            case 2:
                listEmployees();
                continuePromt();
                break;
            case 3:
                removeEmployee();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public static void addEmployee() {
        System.out.print("Name: ");
        String name = input.nextLine();
        double salary = readDouble("Base salary: ");
        int type = readInt("Role (1 = Cashier, 2 = Manager): ");

        try {
            Employee e;
            if (type == 2) {
                double bonus = readDouble("Bonus: ");
                e = new Manager(name, salary, bonus);
            } else {
                e = new Cashier(name, salary);
            }
            shop.addEmployee(e);
            dataManger.save(shop);
            System.out.println("Added: " + e);
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not add employee: " + ex.getMessage());
        }
    }

    public static void listEmployees() {
        if (shop.getStaffSize() == 0) {
            System.out.println("No employees yet.");
            return;
        }
        for (int i = 0; i < shop.getStaffSize(); i++) {
            System.out.println((i + 1) + ". " + shop.getEmployee(i));   // polymorphism
        }
    }

    public static void removeEmployee() {
        listEmployees();
        if (shop.getStaffSize() == 0) return;
        int index = readInt("Employee number to remove: ") - 1;
        if (shop.removeEmployeeAt(index)) {
            dataManger.save(shop);
            System.out.println("Removed.");
        } else {
            System.out.println("Invalid employee number.");
        }
    }
}