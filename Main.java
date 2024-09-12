import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) System.out.println();
    }

    public static void printSeparator(int length) {
        for (int i = 0; i < length; i++) System.out.print("*");
        System.out.println();
    }

    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

    public static void printHeading(String title) {
        printSeparator(60);
        System.out.println(title);
        printSeparator(60);
    }

    public static void main(String[] args) {
        MtBullerResort resort = new MtBullerResort();
        
        // Adding initial customers
        resort.addCustomer(new Customer("C1", "Alice", "555-1234", "beginner", "101"));
        resort.addCustomer(new Customer("C2", "Bob", "555-5678", "intermediate", "202"));
        resort.addCustomer(new Customer("C3", "Charlie", "555-9876", "expert", "303"));

        // Interaction with the user
        boolean running = true;
        while (running) {
            clearConsole();
            printHeading("Mountain Buller Resort");
            System.out.println("1. Display all accommodations");
            System.out.println("2. Available Accommodations and Rooms");
            System.out.println("3. Add customer");
            System.out.println("4. List customers");
            System.out.println("5. Create package");
            System.out.println("6. List packages");
            System.out.println("7. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                    clearConsole();
                    printSeparator(60);
                    resort.showRooms();
                    printSeparator(60);
                    anythingToContinue();
                    break;

                case 2:
                    clearConsole();
                    printHeading("Available Accommodations and Rooms");
                    resort.displayRooms(); // Show available rooms for each accommodation
                    anythingToContinue();
                    break;

                    case 3:
                    clearConsole();
                    printHeading("Add Customer");
                
                    // Collect customer details
                    System.out.println("Enter customer ID:");
                    String id = scanner.next();  // Read customer ID
                
                    System.out.println("Enter customer name:");
                    String name = scanner.next();  // Read customer name
                
                    System.out.println("Enter skiing level (beginner, intermediate, expert):");
                    String level = scanner.next();  // Read skiing level
                
                    System.out.println("Enter phone number:");
                    String phoneNumber = scanner.next();  // Read phone number
                
                    // Handle room number input and format
                    System.out.println("Enter room number to assign (e.g., 101):");
                    String roomNumber = scanner.next();  // Read room number as input
                    String formattedRoomNumber = "Room " + roomNumber;  // Add "Room " prefix
                
                    // Check if the room is available and assign it to the customer
                    if (resort.assignRoom(formattedRoomNumber)) {
                        // If the room was successfully assigned, create the customer
                        Customer newCustomer = new Customer(id, name, phoneNumber, level, formattedRoomNumber);
                        resort.addCustomer(newCustomer);  // Add the customer to the list
                        System.out.println("Customer added with room number: " + formattedRoomNumber);
                    } else {
                        // If room assignment failed
                        System.out.println("Failed to assign the room. The room may already be assigned or not available.");
                    }
                
                    anythingToContinue();  // Pause to allow the user to read the output
                    break;  // Ensure to add a break statement after case 3
                
                
            


                case 4:
                    clearConsole();
                    resort.listCustomers();
                    printSeparator(60);
                    anythingToContinue();
                    break;

                case 5:
                    System.out.println("Enter customer ID:");
                    String customerId = scanner.next();
                    System.out.println("Enter start date (YYYY-MM-DD):");
                    String startDate = scanner.next();
                    System.out.println("Enter duration (days):");
                    int duration = scanner.nextInt();

                    
                    break;

                case 6:
                    clearConsole();
                    resort.listPackages();
                    anythingToContinue();
                    break;

                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        scanner.close();
    }
}