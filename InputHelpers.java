import java.util.InputMismatchException;
import java.util.Scanner;
public class InputHelpers {
    private static Scanner input = new Scanner(System.in);

    // ---------------------------------------------------------------
    // input helpers: always check for errors and negative numbers
    // ---------------------------------------------------------------
    
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                //storing input value
                int value = input.nextInt();
                input.nextLine();
                //checking input value
                if (value < 0) {
                    System.out.println("Value can't be negative.");
                    continue;
                }
                return value;
            } 
            // catching errors
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                //storing input value
                double value = input.nextDouble();
                input.nextLine();
                // checking input value
                if (value < 0) {
                    System.out.println("Value can't be negative.");
                    continue;
                }
                return value;
            } 
            //cathing errors
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }
    }

}
