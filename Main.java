import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static TravelPackage newPackage;

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
        Scanner scanner = new Scanner(System.in);
        
        // Adding initial customers
        resort.addCustomer(new Customer("C1", "Alice", "0435551234", "beginner", "101"));
        resort.addCustomer(new Customer("C2", "Bob", "0425555678", "intermediate", "202"));
        resort.addCustomer(new Customer("C3", "Charlie", "0465559876", "expert", "303"));

        // Interaction with the user
        boolean running = true;
        while (running) {
            clearConsole();
            printHeading("Mountain Buller Resort");

            int choice = 0;
            boolean validChoice = false;
            
            // Validate user input to ensure only numbers between 1 and 11 are allowed
            while (!validChoice) {
                try {
                    clearConsole();
                    printHeading("                        MENU            ");
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
                    printSeparator(60);

                    System.out.print("*Please enter your choice *(1-11): ");
                    choice = Integer.parseInt(scanner.nextLine()); // Parse input as an integer
                    
                    // Check if the choice is valid (between 1 and 11)
                    if (choice >= 1 && choice <= 11) {
                        validChoice = true; // If valid, exit the loop
                    } else {
                        throw new IllegalArgumentException("Invalid choice. Please select a number between 1 and 11.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage()); // Handle custom exception message
                }
            }

            // Switch statement for the valid choice
            switch (choice) {
                case 1:
                    clearConsole();
                    printHeading("All Accommodations\"");
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

                    String id = "", name = "", level = "", phoneNumber = "", roomNumber = "";
                    boolean validInput = false;

                    // Collect customer details
                    System.out.println("Enter customer ID:");
                    id = scanner.next();  // Read customer ID

                    System.out.println("Enter customer name:");
                    name = scanner.next();  // Read customer name

                    validInput = false;  // Reset for phone number validation

                    // Validate phone number input
                    while (!validInput) {
                        try {
                            System.out.println("Enter phone number (10 digits):");
                            phoneNumber = scanner.next();
                            if (!phoneNumber.matches("\\d{10}")) {
                                throw new IllegalArgumentException("Invalid phone number format. Please enter exactly 10 digits.");
                            }
                            validInput = true;  // Exit loop if valid
                        } catch (IllegalArgumentException e) {
                            clearConsole();
                            System.out.println(e.getMessage());  // Catch and display the error message
                        }
                    }

                    validInput = false;  // Reset for room validation

                    // Validate room number input
                    while (!validInput) {
                        try {
                            System.out.println("Enter room number to assign (e.g., 101):");
                            roomNumber = scanner.next();  // Read room number as input
                            String formattedRoomNumber = "Room " + roomNumber;  // Add "Room " prefix

                            // Check if the room is available
                            if (!resort.assignRoom(formattedRoomNumber)) {
                                throw new IllegalArgumentException("Room " + roomNumber + " is not available or does not exist.");
                            }

                            validInput = true;  // If room is valid, exit the loop
                            System.out.println("Room " + roomNumber + " has been assigned.");
                        } catch (IllegalArgumentException e) {
                            clearConsole();
                            System.out.println(e.getMessage());  // Catch and display the error message
                        }
                    }
                    clearConsole();

                    // If the room was successfully assigned, create the customer
                    resort.addCustomer(new Customer(id, name, phoneNumber, level, roomNumber));
                    System.out.println("Customer added with room number: " + roomNumber);

                    anythingToContinue();  // Pause to allow the user to read the output
                    break;

                case 4:
                    clearConsole();
                    printHeading("Customer List");
                    resort.listCustomers();  // Call the listCustomers method to display the customers
                    printSeparator(60);
                    anythingToContinue();  // Pause to allow the user to read the output
                    break;

                case 5:
                    clearConsole();
                    // Prompt for Customer ID
                    System.out.print("Enter Customer ID for the package: ");
                    String customerId = scanner.nextLine();

                    // Search for the customer
                    Customer foundCustomer = resort.findCustomerById(customerId);

                    // Check if the customer exists
                    if (foundCustomer != null) {
                        // Customer found
                        System.out.println("Customer found: " + foundCustomer.getName());

                        // Ask for start date and duration
                        boolean validDate = false;
                        String startDate = "";
                        
                        while (!validDate) {
                            try {
                                
                                System.out.print("Enter start date (YYYY-MM-DD): ");
                                startDate = scanner.nextLine();
                        
                                // Validate using regex for the format YYYY-MM-DD
                                if (!startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                    throw new IllegalArgumentException("**Invalid date format. Please enter the date in the format YYYY-MM-DD.");
                                }
                        
                                validDate = true;  // Exit the loop if the date format is valid
                            } catch (IllegalArgumentException e) {
                                clearConsole();
                                System.out.println(e.getMessage());  // Show custom error message
                            }
                        }

                        System.out.print("*");
                        int duration = 0;
                        boolean validDuration = false;
                        
                        while (!validDuration) {
                            try {
                                System.out.print("Enter duration (days): ");
                                String input = scanner.nextLine(); // Read the input as a string
                                
                                // Try to convert the input to an integer
                                duration = Integer.parseInt(input);
                        
                                // Check if the duration is within the valid range (1 to 30)
                                if (duration > 0 && duration <= 30) {
                                    validDuration = true;  // Valid input, exit the loop
                                } else {
                                    throw new IllegalArgumentException("**Duration must be between 1 and 30 days.");
                                }
                            } catch (NumberFormatException e) {
                                clearConsole();
                                System.out.println("Invalid input. Please enter a number.");
                            } catch (IllegalArgumentException e) {
                                clearConsole();
                                System.out.println(e.getMessage());  // Print custom error message for out-of-range values
                            }
                        }                        
                        // Create the package
                        TravelPackage newPackage = resort.createPackage(customerId, startDate, duration);
                        System.out.println("Done...!");
                        anythingToContinue();
                    } else {
                        clearConsole();
                        // Customer not found, display an error message
                        System.out.println("Error: Customer with ID " + customerId + " not found. Please try again.");
                        anythingToContinue();
                    }
                    break;

                case 6:
                    clearConsole();
                    printHeading("Add Lift Pass to Package");

                    // Reuse the existing customerId variable (do not declare it again)
                    System.out.print("Enter Customer ID for the package: ");
                    customerId = scanner.nextLine();  // Reassign value to existing variable

                    // Search for the package
                    TravelPackage foundPackage = resort.findPackageByCustomerId(customerId);

                    // Check if the package exists
                    if (foundPackage != null) {
                        // Package found
                        System.out.println("Package found for Customer ID: " + customerId);

                        // Ask for lift pass details
                        System.out.print("Enter the number of days for the lift pass: ");
                        int liftPassDays = scanner.nextInt();
                        scanner.nextLine();  // Clear the newline

                        // Add the lift pass to the found package
                        foundPackage.addLiftPass(liftPassDays);
                        System.out.println("Lift pass for " + liftPassDays + " days has been added to the package.");
                    } else {
                        // Package not found
                        System.out.println("No package found for Customer ID: " + customerId);
                    }

                    anythingToContinue();
                    break;

                    case 7:
                    clearConsole();
                    printHeading("Add Lesson Fees to Package");

                    // Ask for Customer ID to find the package
                    System.out.print("Enter Customer ID for the package: ");
                    String lessonCustomerId = scanner.nextLine();

                    // Search for the package
                    TravelPackage lessonPackage = resort.findPackageByCustomerId(lessonCustomerId);

                    // Check if the package exists
                    if (lessonPackage != null) {
                        System.out.println("Package found for Customer ID: " + lessonCustomerId);

                        // Ask for skiing level
                        System.out.print("Enter skiing level (beginner, intermediate, expert): ");
                        String skiingLevel = scanner.nextLine();

                        // Ask for number of lessons
                        System.out.print("Enter the number of lessons: ");
                        int numberOfLessons = scanner.nextInt();
                        scanner.nextLine();  // Clear the newline

                        // Add lesson fees to the package
                        resort.addLessonFeesToPackage(lessonCustomerId, skiingLevel, numberOfLessons);
                    } else {
                        System.out.println("No package found for Customer ID: " + lessonCustomerId);
                    }

                    anythingToContinue();
                    break;

                case 8:
                    clearConsole();
                    resort.listPackages();
                    anythingToContinue();
                    break;

                case 9:
                    clearConsole();
                    System.out.println("Saving all packages to file...");
                    resort.savePackagesToFile("packages.dat");  // Specify the filename
                    anythingToContinue();
                    break;

                case 10:
                    clearConsole();
                    System.out.println("Reading packages from file...");
                    resort.readPackagesFromFile("packages.dat");  // Specify the filename
                    anythingToContinue();
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
