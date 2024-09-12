
import java.util.ArrayList;

public class Accommodation {
    private String id;
    private String type; // Hotel, Lodge, or Apartment
    private double price;
    private ArrayList<String> rooms; // List of available rooms
    private boolean available; // Tracks whether accommodation has any available rooms

    // Constructor to initialize Accommodation with available rooms
    public Accommodation(String id, String type, double price, ArrayList<String> rooms) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.rooms = new ArrayList<>(rooms); // Initialize with the list of rooms
        this.available = !rooms.isEmpty(); // If rooms are available, mark accommodation as available
    }

    // Getter for Accommodation ID
    public String getId() {
        return id;
    }

    // Getter for Accommodation type
    public String getType() {
        return type;
    }

    // Getter for Accommodation price
    public double getPrice() {
        return price;
    }

    

    // Method to return all rooms
    public ArrayList<String> getRooms() {
        return rooms; // Return the list of available rooms
    }

    // Method to return only available rooms
    public ArrayList<String> getAvailableRooms() {
        return new ArrayList<>(rooms); // Return a copy of the list of available rooms
    }

    // Method to check if the accommodation has available rooms
    public boolean isAvailable() {
        return available; // Return whether this accommodation has available rooms
    }

    // Method to set the availability status of the accommodation
    public void setAvailable(boolean available) {
        this.available = available; // Set the availability of the accommodation
    }

    
    // toString method to display accommodation details
    @Override
    public String toString() {
        return "ID: " + id + ", Type: " + type + ", Price: $" + price +
               ", Available Rooms: " + rooms + ", Available: " + available;
    }
}
