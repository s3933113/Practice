import java.util.ArrayList;
import java.util.Scanner;

public class MtBullerResort {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> packages = new ArrayList<>();
    private static ArrayList<String> rooms;
    private ArrayList<String> assignedRooms;
    private Scanner scanner;

    public void addCustomer(Customer customer) {
        String roomNumber = customer.getRoomNumber();
        
        // Ensure the room number is formatted correctly
        String formattedRoomNumber = "Room " + roomNumber.replace("Room ", "");
        
        // Remove the room from available rooms and add to assigned rooms (assuming it's available)
        rooms.remove(formattedRoomNumber);  // Attempt to remove the room from available rooms
        assignedRooms.add(formattedRoomNumber);  // Add the room to the assigned rooms list
        
        // Add the customer to the customers list
        customers.add(customer);  
        
        // Print confirmation messages
        System.out.println("Customer added: " + customer); 
        System.out.println(formattedRoomNumber + " is now unavailable.");
    }
    
    

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers have been added.");
        } else {
            
            for (Customer customer : customers) {
                System.out.println(customer);  // This will call the toString() method of the Customer class
            }
        }
    }
    
    
    public MtBullerResort() {
        rooms = new ArrayList<>();
        assignedRooms = new ArrayList<>();

        // Add rooms 101 to 103 for Single Room
        for (int i = 101; i <= 105; i++) {
            rooms.add("Room " + i);
            
        }

        // Add rooms 201 to 203 for Double Room
        for (int i = 201; i <= 205; i++) {
            rooms.add("Room " + i);
        }

        // Add rooms 301 to 303 for Queen Room
        for (int i = 301; i <= 305; i++) {
            rooms.add("Room " + i);
        }

        // Add rooms 401 to 403 for King Room
        for (int i = 401; i <= 405; i++) {
            rooms.add("Room " + i);
        }
    }

    public void showRooms() {
        System.out.println("Single Rooms: Room 101 Room 102 Room 103 Room 104 Room 105 Type: Single Room Price: 120");
        System.out.println("Double Rooms:  Room 201 Room 202 Room 203 Room 204 Room 205 Type: double Room Price: 150");
        System.out.println("Queen Rooms:  Room 301 Room 302 Room 303 Room 304 Room 305 Type: Queen Room Price: 180");
        System.out.println("King Rooms:  Room 401 Room 402 Room 403 Room 404 Room 405 Type: King Room Price: 210");
    }
    
      
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

    public TravelPackage createPackage(String customerId, String startDate, int duration) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            // Create a new TravelPackage object
            TravelPackage travelPackage = new TravelPackage(customerId, startDate, duration, customer.getRoomNumber());
    
            // Add the package to the list of packages
            packages.add(travelPackage);
    
            return travelPackage;  // Return the package so we can add lift pass later
        } else {
            System.out.println("Customer with ID " + customerId + " not found.");
            return null;  // Return null if no customer found
        }
    }
    
    
    
    
    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equalsIgnoreCase(customerId)) {  // Use equalsIgnoreCase for case-insensitive match
                return customer;
            }
        }
        return null;  // Return null if no match is found
    }
    
    
    
    
  

    public void listPackages() {
        if (packages.isEmpty()) {
        System.out.println("No packages found.");
        } else {
           for (TravelPackage pkg : packages) {
            System.out.println(pkg);  // This should invoke TravelPackage's toString() method
        }
    }
}



    // Methods to add lift pass, lessons, save/load packages to/from file can be added similarly.
}