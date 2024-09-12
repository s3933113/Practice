import java.util.ArrayList;

public class MtBullerResort {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> packages = new ArrayList<>();
    private static ArrayList<String> rooms;
    private ArrayList<String> assignedRooms;

    public void addCustomer(Customer customer) {
        String roomNumber = customer.getRoomNumber();
        
        // Ensure the room number is formatted correctly
        String formattedRoomNumber = "Room " + roomNumber.replace("Room ", "");
        
        if (rooms.contains(formattedRoomNumber)) {  // Check if the formatted room is available
            rooms.remove(formattedRoomNumber);  // Remove the room from available rooms
            assignedRooms.add(formattedRoomNumber);  // Add room to assigned rooms
            customers.add(customer);  // Add the customer to the list
            System.out.println("Customer added: " + customer); 
            System.out.println(formattedRoomNumber + " is now unavailable.");
        } else {
            System.out.println("Room " + formattedRoomNumber + " is already assigned or not available.");
        }
    }

    public void listCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
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
            System.out.println("Room " + roomNumber + " is already assigned or not available.");
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
