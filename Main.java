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
    

    public static void main(String[] args) {
        MtBullerResort resort = new MtBullerResort();

        // Adding initial accommodations
        resort.addAccommodation(new Accommodation("A1", "Single Room", 120));
        resort.addAccommodation(new Accommodation("A2", "Double Room", 150));
        resort.addAccommodation(new Accommodation("A2", "Queen Room", 180));
        resort.addAccommodation(new Accommodation("A2", "King Room", 210));
        // Add more accommodations as needed

        // Adding initial customers
        resort.addCustomer(new Customer("C1", "Alice", "beginner"));
        resort.addCustomer(new Customer("C2", "Bob", "intermediate"));
        resort.addCustomer(new Customer("C3", "Charlie", "expert"));

        // Interaction with the user
        boolean running = true;
        while (running) {
            System.out.println("1. Display all accommodations");
            System.out.println("2. Add customer");
            System.out.println("3. List customers");
            System.out.println("4. Create package");
            System.out.println("5. List packages");
            System.out.println("6. Quit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                clearConsole();
                printSeparator(60);
                    resort.displayAccommodations();
                    printSeparator(60);
                    break;
                case 2:
                    System.out.println("Enter customer ID:");
                    String id = scanner.next();
                    System.out.println("Enter customer name:");
                    String name = scanner.next();
                    System.out.println("Enter skiing level (beginner, intermediate, expert):");
                    String level = scanner.next();
                    resort.addCustomer(new Customer(id, name, level));
                    break;
                case 3:
                clearConsole();
                printSeparator(60);
                    resort.listCustomers();
                    printSeparator(60);
                    break;
                case 4:
                    System.out.println("Enter customer ID:");
                    String customerId = scanner.next();
                    System.out.println("Enter start date (YYYY-MM-DD):");
                    String startDate = scanner.next();
                    System.out.println("Enter duration (days):");
                    int duration = scanner.nextInt();
                    resort.createPackage(customerId, startDate, duration);
                    break;
                case 5:
                    resort.listPackages();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }
}