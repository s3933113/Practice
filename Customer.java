public class Customer {
    private String id;           // Unique ID for the customer
    private String name;         // Customer's name
    private String phoneNumber;  // Customer's phone number
    private String skiingLevel;  // Skiing level (beginner, intermediate, expert)
    private String roomNumber;   // Assigned room number

    public Customer(String id, String name, String phoneNumber, String skiingLevel, String roomNumber) {
        this.id = id;
        this.name = name;
        setPhoneNumber(phoneNumber);  // Validate phone number
        setSkiingLevel(skiingLevel);  // Validate skiing level
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

    // Validate phone number with exception handling
    public void setPhoneNumber(String phoneNumber) {
        try {
            if (phoneNumber.matches("\\d{10}")) {  // Example: 10-digit number validation
                this.phoneNumber = phoneNumber;
            } else {
                throw new IllegalArgumentException("Invalid phone number format. Must be 10 digits.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());  // Catch and print the error
        }
    }

    // Validate skiing level with exception handling
    public void setSkiingLevel(String skiingLevel) {
        try {
            if (skiingLevel.equalsIgnoreCase("beginner") || skiingLevel.equalsIgnoreCase("intermediate") || skiingLevel.equalsIgnoreCase("expert")) {
                this.skiingLevel = skiingLevel;
            } else {
                throw new IllegalArgumentException("This customer doesn't add lesson yet.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());  // Catch and print the error
        }
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "ID : " + id + ", Name : " + name +"\n"+
               "Phone Number : " + phoneNumber + "\n"+
               "Room Number : " + roomNumber;
    }
}