import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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


    // Method to save packages to a file
    public void savePackagesToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(packages); // Serialize the list of packages
            System.out.println("Packages have been saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving packages: " + e.getMessage());
        }
    }

    // Method to read packages from a file
    public void readPackagesFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            packages = (ArrayList<TravelPackage>) in.readObject(); // Deserialize the list of packages
            System.out.println("Packages have been loaded from " + filename);
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
    private static String[][] accommodations = {
        {"101", "Single Room", "120"},
        {"102", "Single Room", "120"},
        {"103", "Single Room", "120"},
        {"104", "Single Room", "120"},
        {"105", "Single Room", "120"},
        {"201", "Double Room", "150"},
        {"202", "Double Room", "150"},
        {"203", "Double Room", "150"},
        {"204", "Double Room", "150"},
        {"205", "Double Room", "150"},
        {"301", "Queen Room ", "180"},
        {"302", "Queen Room ", "180"},
        {"303", "Queen Room ", "180"},
        {"304", "Queen Room ", "180"},
        {"305", "Queen Room ", "180"},
        {"401", "King Room  ", "210"},
        {"402", "King Room  ", "210"},
        {"403", "King Room  ", "210"},
        {"404", "King Room  ", "210"},
        {"405", "King Room  ", "210"}
    };

    // Method to show rooms
    public static String showRooms() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of Accommodations:\n\n");

        // Loop through the accommodations array and format the details
        for (String[] room : accommodations) {
            sb.append("Room ").append(room[0])  // Room number
              .append(" | Type: ").append(room[1])  // Room type
              .append(" | Price: $").append(room[2])  // Room price
              .append("\n");
        }

        return sb.toString();  // Return the formatted room details
    }

    public static void main(String[] args) {
        // Test the showRooms() method
        System.out.println(showRooms());

    }

    public List<String> getAvailableRoomsAsList() {
    if (rooms.isEmpty()) {
        return new ArrayList<>();  // Return an empty list if no rooms are available
    } else {
        return new ArrayList<>(rooms);  // Return the available rooms as a list
    }
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
    public TravelPackage createPackage(String customerId, String startDate, int duration, String skiingLevel) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            // Create the package with skiingLevel and add it to the packages list
            TravelPackage travelPackage = new TravelPackage(customerId, startDate, duration, customer.getRoomNumber(), skiingLevel);
            packages.add(travelPackage);
            return travelPackage;
        } else {
            return null;  // Return null if the customer is not found
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
        // Create text fields for customer details
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
    
        // Fetch available rooms and create a JComboBox for room selection
        List<String> availableRooms = getAvailableRoomsAsList();
        JComboBox<String> roomComboBox = new JComboBox<>(availableRooms.toArray(new String[0]));
    
        // Create a panel to hold the input fields and the JComboBox
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Customer ID:"));
        panel.add(idField);
        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Room Number:"));
        panel.add(roomComboBox); // Add the JComboBox for room selection
    
        // Show a dialog with the form to collect input
        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Customer", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String roomNumber = (String) roomComboBox.getSelectedItem();
    
            // Validation to ensure no fields are left empty
            if (id.isEmpty() || name.isEmpty() || phone.isEmpty() || roomNumber == null) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields before adding a customer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Do not proceed if validation fails
            }
    
            // Validation for phone number: must be 10 digits
            if (!phone.matches("\\d{10}")) {  // Regex to check if the phone contains exactly 10 digits
                JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digits.", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return; // Do not proceed if validation fails
            }
    
            // Assuming default skiing level for simplicity
            String skiingLevel = "beginner";
    
            // Add customer to the system
            addCustomer(new Customer(id, name, phone, skiingLevel, roomNumber));
    
            // Show success message
            JOptionPane.showMessageDialog(null, "Customer added successfully!");
        }
    }
    
    public void createPackageProcess() {
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No customers available. Please add customers first.");
            return;
        }
    
        JComboBox<String> customerIdComboBox = new JComboBox<>();
        for (Customer customer : customers) {
            customerIdComboBox.addItem(customer.getId());
        }
    
        JComboBox<String> yearComboBox = new JComboBox<>(new String[]{"2024", "2025", "2026", "2027", "2028", "2029", "2030"});
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
        JComboBox<String> dayComboBox = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
    
        JComboBox<String> skiingLevelComboBox = new JComboBox<>(new String[]{"beginner", "intermediate", "expert"});
    
        JTextField durationField = new JTextField();
        JTextField liftPassField = new JTextField();
    
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Select Customer ID:"));
        panel.add(customerIdComboBox);
        panel.add(new JLabel("Select Start Date (YYYY-MM-DD):"));
        panel.add(new JPanel(new GridLayout(1, 3)) {{
            add(yearComboBox);
            add(monthComboBox);
            add(dayComboBox);
        }});
        panel.add(new JLabel("Enter Duration (days):"));
        panel.add(durationField);
        panel.add(new JLabel("Add Lift Pass (days):"));
        panel.add(liftPassField);
        panel.add(new JLabel("Select Skiing Level:"));
        panel.add(skiingLevelComboBox);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Create Package", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedCustomerId = (String) customerIdComboBox.getSelectedItem();
            String startYear = (String) yearComboBox.getSelectedItem();
            String startMonth = (String) monthComboBox.getSelectedItem();
            String startDay = (String) dayComboBox.getSelectedItem();
            String startDate = startYear + "-" + startMonth + "-" + startDay;
            String durationStr = durationField.getText().trim();
            String liftPassStr = liftPassField.getText().trim();
            String skiingLevel = (String) skiingLevelComboBox.getSelectedItem();
    
            if (durationStr.isEmpty() || liftPassStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields before creating a package.");
                return;
            }
    
            try {
                int duration = Integer.parseInt(durationStr);
                int liftPassDays = Integer.parseInt(liftPassStr);
    
                TravelPackage newPackage = createPackage(selectedCustomerId, startDate, duration, skiingLevel);
    
                if (newPackage != null) {
                    newPackage.addLiftPass(liftPassDays);
                    JOptionPane.showMessageDialog(null, "Package created successfully with lift pass and skiing level!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Customer not found or package creation failed.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers for duration and lift pass.");
            }
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
                
                System.out.println("Lesson fees added to the package.");
            } else {
                System.out.println("No package found for Customer ID: " + lessonCustomerId);
            }
        } catch (Exception e) {
            clearConsole();
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
    //list all packages
    public String listPackages() {
        if (packages.isEmpty()) {
            return "No travel packages available.";
        }

        StringBuilder packageList = new StringBuilder();
        packageList.append("List of Travel Packages:\n");
        for (TravelPackage pkg : packages) {
            packageList.append(pkg.toString()).append("\n");  // Make sure toString() works for TravelPackage
        }
        return packageList.toString();
    }


    
    

    

    public Object getAvailableRooms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableRooms'");
    }
}