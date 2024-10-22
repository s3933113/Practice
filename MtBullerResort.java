import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MtBullerResort {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> packages = new ArrayList<>();
    private static ArrayList<String> rooms;
    private ArrayList<String> assignedRooms;
    public static Scanner scanner = new Scanner(System.in);


    // Constructor
    public MtBullerResort() {
        rooms = new ArrayList<>();
        assignedRooms = new ArrayList<>();

        // Add rooms 101 to 105 for Single Room
        for (int i = 101; i <= 105; i++) {
            rooms.add("Room " + i);
        }

        // Add rooms 201 to 205 for Double Room
        for (int i = 201; i <= 205; i++) {
            rooms.add("Room " + i);
        }

        // Add rooms 301 to 305 for Queen Room
        for (int i = 301; i <= 305; i++) {
            rooms.add("Room " + i);
        }

        // Add rooms 401 to 405 for King Room
        for (int i = 401; i <= 405; i++) {
            rooms.add("Room " + i);
        }
    }


    // Save packages to file
    public void savePackagesToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(packages);  // Serialize the list of packages
            System.out.println("Packages have been saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving packages: " + e.getMessage());
        }
    }

    // Read packages from file
    public void readPackagesFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            packages = (ArrayList<TravelPackage>) in.readObject();  // Deserialize the list of packages
            System.out.println("Packages have been loaded from " + filename);

            // Display the packages after reading them
            for (TravelPackage pkg : packages) {
                System.out.println(pkg);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading packages: " + e.getMessage());
        }
    }

    // Add a customer
    public void addCustomer(Customer customer) {
        String roomNumber = customer.getRoomNumber();
        String formattedRoomNumber = "Room " + roomNumber.replace("Room ", "");
        rooms.remove(formattedRoomNumber);  // Remove room from available rooms
        assignedRooms.add(formattedRoomNumber);  // Add room to assigned rooms
        customers.add(customer);  // Add customer to the list
        System.out.println("Customer added: " + customer);
        System.out.println(formattedRoomNumber + " is now unavailable.");
    }

    // List all customers
    public String listCustomers() {
        StringBuilder customerList = new StringBuilder();
        if (customers.isEmpty()) {
            customerList.append("No customers have been added.");
        } else {
            for (Customer customer : customers) {
                customerList.append(customer.toString()).append("\n");
            }
        }
        return customerList.toString();
    }
    

    // Show available rooms
    public static String showRooms() {
        StringBuilder sb = new StringBuilder();
        sb.append("Single Rooms: Room 101 Room 102 Room 103 Room 104 Room 105 Type: Single Room Price: 120\n");
        sb.append("Double Rooms: Room 201 Room 202 Room 203 Room 204 Room 205 Type: Double Room Price: 150\n");
        sb.append("Queen Rooms: Room 301 Room 302 Room 303 Room 304 Room 305 Type: Queen Room Price: 180\n");
        sb.append("King Rooms: Room 401 Room 402 Room 403 Room 404 Room 405 Type: King Room Price: 210\n");
        return sb.toString();  // Return the string
    }
    // Display available rooms
    public String displayRooms() {
        if (rooms.isEmpty()) {
            return "No available rooms.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Available Rooms:\n");
            for (String room : rooms) {
                sb.append(room).append("\n");
            }
            return sb.toString();  // Return the string
        }
    }

    // Assign a room to a customer
    public boolean assignRoom(String roomNumber) {
        if (rooms.contains(roomNumber)) {
            rooms.remove(roomNumber);  // Remove room from available rooms
            assignedRooms.add(roomNumber);  // Add room to assigned rooms
            System.out.println("Assigned " + roomNumber + " to the customer.");
            return true;  // Room successfully assigned
        } else {
            System.out.println("Room " + roomNumber + " is already assigned or does not exist.");
            return false;  // Room assignment failed
        }
    }

    // Display assigned rooms
    public void displayAssignedRooms() {
        if (assignedRooms.isEmpty()) {
            System.out.println("No rooms have been assigned yet.");
        } else {
            System.out.println("Assigned Rooms:");
            for (String room : assignedRooms) {
                System.out.println(room);
            }
        }
    }

    // Create a package for a customer
    public TravelPackage createPackage(String customerId, String startDate, int duration) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            TravelPackage travelPackage = new TravelPackage(customerId, startDate, duration, customer.getRoomNumber());
            packages.add(travelPackage);
            return travelPackage;
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
            return null;
        }
    }

    // Add lift pass to package
    public void addLiftPassToPackage(String customerId, int liftPassDays) {
        TravelPackage foundPackage = findPackageByCustomerId(customerId);
        if (foundPackage != null) {
            foundPackage.addLiftPass(liftPassDays);
            System.out.println("Lift pass for " + liftPassDays + " days has been added to the package for customer ID: " + customerId);
        } else {
            System.out.println("Package not found for customer ID: " + customerId);
        }
    }

    // Find a customer by ID
    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }
        return null;
    }

    // Find a package by customer ID
    public TravelPackage findPackageByCustomerId(String customerId) {
        for (TravelPackage travelPackage : packages) {
            if (((String) travelPackage.getCustomerId()).equalsIgnoreCase(customerId)) {
                return travelPackage;
            }
        }
        return null;
    }

    public void addLessonFeesToPackage(String customerId, String skiingLevel, int numberOfLessons) {
        TravelPackage foundPackage = findPackageByCustomerId(customerId);
        if (foundPackage != null) {
            foundPackage.addLessonFee(skiingLevel, numberOfLessons);
            System.out.println("Lesson fees added for customer ID: " + customerId);
        } else {
            System.out.println("Package not found for customer ID: " + customerId);
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) System.out.println();
    }

    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

    public static void printSeparator(int length) {
        for (int i = 0; i < length; i++) System.out.print("*");
        System.out.println();
    }

    public static void printHeading(String title) {
        printSeparator(60);
        System.out.println(title);
        printSeparator(60);
    }

    public void addCustomerProcess() {
        // Collect customer details through dialog boxes
        String id = JOptionPane.showInputDialog(null, "Enter Customer ID:");
        String name = JOptionPane.showInputDialog(null, "Enter Customer Name:");
        String phone = JOptionPane.showInputDialog(null, "Enter Phone Number:");
        String roomNumber = JOptionPane.showInputDialog(null, "Enter Room Number:");

        // Assuming default skiing level for simplicity
        String skiingLevel = "beginner";

        // Add customer to the system
        addCustomer(new Customer(id, name, phone, skiingLevel, roomNumber));
        
        JOptionPane.showMessageDialog(null, "Customer added successfully!");
    }



    public void createPackageProcess() {
        String customerId = JOptionPane.showInputDialog(null, "Enter Customer ID for the package:");
        String startDate = JOptionPane.showInputDialog(null, "Enter start date (YYYY-MM-DD):");
        String durationStr = JOptionPane.showInputDialog(null, "Enter duration (days):");

        try {
            int duration = Integer.parseInt(durationStr);
            TravelPackage newPackage = createPackage(customerId, startDate, duration);
            if (newPackage != null) {
                JOptionPane.showMessageDialog(null, "Package created successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Customer not found!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration. Please enter a number.");
        }
    }
    
    
    // Option 6
    public void addLiftPassToPackageProcess() {
        clearConsole();
        printHeading("Add Lift Pass to Package");
    
        // Consume any leftover newline character

    
        // Ask for the customer ID
        System.out.print("Enter Customer ID for the package: ");
        String customerId = scanner.nextLine().trim();  // Use trim() to remove any extra spaces
    
        // Check if customerId is empty
        if (customerId.isEmpty()) {
            System.out.println("Error: Customer ID cannot be empty.");
            return;  // Exit the method if input is invalid
        }
    
        // Search for the package
        TravelPackage foundPackage = findPackageByCustomerId(customerId);
    
        // Check if the package exists
        if (foundPackage != null) {
            System.out.println("Package found for Customer ID: " + customerId);
    
            // Ask for lift pass details
            int liftPassDays = 0;
            boolean validLiftPass = false;
    
            while (!validLiftPass) {
                try {
                    System.out.print("Enter the number of days for the lift pass: ");
                    String input = scanner.nextLine();  // Use nextLine() instead of nextInt()
                    liftPassDays = Integer.parseInt(input);
    
                    // Ensure valid input for the number of days
                    if (liftPassDays > 0 && liftPassDays <= 30) {
                        validLiftPass = true;
                    } else {
                        System.out.println("The number of lift pass days must be between 1 and 30.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
    
            // Add the lift pass to the package
            foundPackage.addLiftPass(liftPassDays);
            System.out.println("Lift pass for " + liftPassDays + " days has been added to the package.");
            anythingToContinue();

        } else {

            System.out.println("No package found for Customer ID: " + customerId);
            
        }
    }
    

    public void addLessonFeesToPackageProcess() {
        clearConsole();
        printHeading("Add Lesson Fees to Package");
    
        try {
            String lessonCustomerId = ""; // Initialize an empty string
            
            // Loop to ask for Customer ID until a non-empty ID is provided
            while (lessonCustomerId.isEmpty()) {
                System.out.print("Enter Customer ID for the package: ");
                lessonCustomerId = scanner.nextLine().trim();  // Trim to remove whitespace
    
                if (lessonCustomerId.isEmpty()) {
                    System.out.println("Error: Customer ID cannot be empty. Please try again.");
                }
            }
    
            // Search for the customer's package
            TravelPackage lessonPackage = findPackageByCustomerId(lessonCustomerId);
    
            // Check if the package exists for the customer
            if (lessonPackage != null) {
                System.out.println("Package found for Customer ID: " + lessonCustomerId);
    
                // Ask for skiing level
                boolean validSkiingLevel = false;
                String skiingLevel = "";
    
                // Loop until a valid skiing level is provided
                while (!validSkiingLevel) {
                    System.out.print("Enter skiing level (beginner, intermediate, expert): ");
                    skiingLevel = scanner.nextLine();
    
                    // Check if the input matches one of the three valid options
                    if (skiingLevel.equalsIgnoreCase("beginner") || 
                        skiingLevel.equalsIgnoreCase("intermediate") || 
                        skiingLevel.equalsIgnoreCase("expert")) {
                        validSkiingLevel = true; // Valid input, exit the loop
                    } else {
                        clearConsole();
                        System.out.println("Invalid input. Please enter one of the following: beginner, intermediate, expert.");
                    }
                }
    
                // Ask for the number of lessons
                int numberOfLessons = 0;
                boolean validLessonInput = false;
    
                while (!validLessonInput) {
                    try {
                        System.out.print("Enter the number of lessons: ");
                        numberOfLessons = Integer.parseInt(scanner.nextLine());  // Convert input to an integer
                        if (numberOfLessons > 0) {
                            validLessonInput = true; // Valid input, exit the loop
                        } else {
                            throw new IllegalArgumentException("The number of lessons must be greater than 0.");
                        }
                    } catch (NumberFormatException e) {
                        clearConsole();
                        System.out.println("Invalid input. Please enter a valid number for lessons.");
                    } catch (IllegalArgumentException e) {
                        clearConsole();
                        System.out.println(e.getMessage());  // Print custom error message for invalid lesson input
                    }
                }
    
                // Add the lesson fees to the package
                lessonPackage.addLessonFee(skiingLevel, numberOfLessons);
                System.out.println("Lesson fees added to the package.");
            } else {
                System.out.println("No package found for Customer ID: " + lessonCustomerId);
            }
        } catch (Exception e) {
            clearConsole();
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    


    

    // List all packages
    public void listPackages() {
        MtBullerResort.clearConsole();
        if (packages.isEmpty()) {
            System.out.println("No packages found.");
        } else {
            for (TravelPackage pkg : packages) {
                System.out.println(pkg);  // Call the toString() method of TravelPackage
            }
        }
    }

    

    public Object getAvailableRooms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableRooms'");
    }
}
