import java.util.Scanner;

public class MtBullerAdmin {
    public static Scanner scanner = new Scanner(System.in);
    private static TravelPackage newPackage;


    public static void main(String[] args) {
        MtBullerResort resort = new MtBullerResort();
        Scanner scanner = new Scanner(System.in);
        
        // Adding initial customers
        resort.addCustomer(new Customer("C1", "Alice", "0435551234", "beginner", "101"));
        resort.addCustomer(new Customer("C2", "Bob", "0425555678", "intermediate", "202"));
        resort.addCustomer(new Customer("C3", "Charlie", "0465559876", "expert", "303"));
        
        // Interaction with the user
        boolean running = true;
        while (running) {
            MtBullerResort.clearConsole();
            
            

            int choice = 0;
            boolean validChoice = false;
            
            // Validate user input to ensure only numbers between 1 and 11 are allowed
            while (!validChoice) {
                try {
                    
                    MtBullerResort.printHeading("                        MENU            ");
                    System.out.println("1. Display all Accommodations");
                    System.out.println("2. Available Accommodations and Rooms");
                    System.out.println("3. Add customer");
                    System.out.println("4. List customers");
                    System.out.println("5. Create package");
                    System.out.println("6. Add Lift Pass to Package");
                    System.out.println("7. Add lesson fees to package");
                    System.out.println("8. List packages");
                    System.out.println("9. Save packages to file");
                    System.out.println("10. Read packages from file");
                    System.out.println("11. Quit");
                    MtBullerResort.printSeparator(60);

                    System.out.print("*Please enter your choice *(1-11): ");
                    choice = Integer.parseInt(scanner.nextLine()); // Parse input as an integer
                    
                    // Check if the choice is valid (between 1 and 11)
                    if (choice >= 1 && choice <= 11) {
                        validChoice = true; // If valid, exit the loop
                    } else {
                        MtBullerResort.clearConsole();
                        throw new IllegalArgumentException("Invalid choice. Please select a number between 1 and 11.");
                    }
                } catch (NumberFormatException e) {
                    MtBullerResort.clearConsole();
                    System.out.println("Please enter a valid number.");
                } catch (IllegalArgumentException e) {
                    MtBullerResort.clearConsole();
                    System.out.println(e.getMessage()); // Handle custom exception message
                }
            }

            // Switch statement for the valid choice
            switch (choice) {
                case 1:
                    resort.showRooms();

                    break;

                case 2:
                    resort.displayRooms(); // Show available rooms for each accommodation

                    break;

                case 3:
                    resort.addCustomerProcess();  // Call the method from MtBullerResort

                    break;

                case 4:
                    resort.listCustomers();  // Call the listCustomers method to display the customers
                    MtBullerResort.anythingToContinue();  // Pause to allow the user to read the output
                    break;

                case 5:
                resort.createPackageProcess();  // Call the method from MtBullerResort
                    MtBullerResort.anythingToContinue();
                    break;

                case 6:
                resort.addLiftPassToPackageProcess();  // Call the method from MtBullerResort
                MtBullerResort.anythingToContinue();
                    break;

                case 7:
                    resort.addLessonFeesToPackageProcess();  // Call the method from MtBullerResort
                    MtBullerResort.anythingToContinue();
                    break;

                case 8:
                    resort.listPackages();
                    MtBullerResort.anythingToContinue();;
                    break;

                case 9:
                MtBullerResort.clearConsole();
                    System.out.println("Saving all packages to file...");
                    resort.savePackagesToFile("packages.dat");  // Specify the filename
                    MtBullerResort.anythingToContinue();
                    break;

                case 10:
                MtBullerResort.clearConsole();
                    System.out.println("Reading packages from file...");
                    resort.readPackagesFromFile("packages.dat");  // Specify the filename
                    MtBullerResort.anythingToContinue();
                    break;

                case 11:
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
