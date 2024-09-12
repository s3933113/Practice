public class Customer {
    private String id;           // Unique ID for the customer
    private String name;         // Customer's name
    private String phoneNumber;  // Customer's phone number
    private String skiingLevel;  // Skiing level (beginner, intermediate, expert)
    private String roomNumber;   // Assigned room number

    public Customer(String id, String name, String phoneNumber, String skiingLevel, String roomNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.skiingLevel = skiingLevel;
        this.roomNumber = roomNumber; // Initialize room number
    }

    
    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSkiingLevel() {
        return skiingLevel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSkiingLevel(String skiingLevel) {
        this.skiingLevel = skiingLevel;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + 
               ", Phone Number: " + phoneNumber + ", Skiing Level: " + skiingLevel +
               ", Room Number: " + roomNumber;
    }
}