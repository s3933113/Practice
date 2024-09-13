import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MtBullerResort {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> packages = new ArrayList<>();
    private static ArrayList<String> rooms;
    private ArrayList<String> assignedRooms;

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
    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers have been added.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    // Show available rooms
    public void showRooms() {
        System.out.println("Single Rooms: Room 101 Room 102 Room 103 Room 104 Room 105 Type: Single Room Price: 120");
        System.out.println("Double Rooms: Room 201 Room 202 Room 203 Room 204 Room 205 Type: double Room Price: 150");
        System.out.println("Queen Rooms: Room 301 Room 302 Room 303 Room 304 Room 305 Type: Queen Room Price: 180");
        System.out.println("King Rooms: Room 401 Room 402 Room 403 Room 404 Room 405 Type: King Room Price: 210");
    }

    // Display available rooms
    public void displayRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No available rooms.");
        } else {
            System.out.println("Available Rooms:");
            for (String room : rooms) {
                System.out.println(room);
            }
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

    // List all packages
    public void listPackages() {
        if (packages.isEmpty()) {
            System.out.println("No packages found.");
        } else {
            for (TravelPackage pkg : packages) {
                System.out.println(pkg);  // Call the toString() method of TravelPackage
            }
        }
    }
}
