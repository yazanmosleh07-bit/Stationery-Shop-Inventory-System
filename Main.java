import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Main {
    // the variable that stores the user's input
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        // stores the state
        boolean loopRunning = true;

        // main loop
        while (loopRunning) {
            startProgram();
            try{
            choice = input.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Please Enter A Valid Input");
                input.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("selling a product...");
                    showProductList();
                    continuePromt();
                    break;
                case 2:
                    System.out.println("Buying a product..");
                    continuePromt();
                    break;
                case 3:
                    break;
                case 4:
                    AddToStock();
                    break;
                default:
                    break;
            }

        }
        
        

        input.close();
    }

    public static void continuePromt(){
        System.out.println(" ");
        System.out.println("Press Enter To Continue");
        System.out.println(" ");
        System.out.println(" ");
        input.nextLine();
    }

    public static void createProduct(){
        int steps = 3;
        double price = 0;
        int quantity = 0;
        String name = "";
        // takes input to fill the quantity,price and name to create a new product class
        for (int i = 0;i < steps;i++){
            if(i == 0){
                System.out.println("Enter Quantity");
                quantity = input.nextInt();
            }
            if(i == 1){
                System.out.println("Enter the Price");
                price = input.nextDouble(); 
                input.nextLine();// consumes leftover newline
            }
            if(i == 2){
                System.out.println("Enter the name");
                name = input.nextLine();
            }
        }
        
        Product p = new Product(name, price, quantity);
            
        System.out.println("Name: "+ p.getName()+ " "+"Price: "+p.getPrice()+" "+"Quantity: "+" "+p.getQuantity());
        continuePromt();
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

    public static void showProductList(){

    }


    public static void checkStock(){

    }


    public static void sellProduct(){
        
    }

    public static void AddToStock(){
        createProduct();
    }
}