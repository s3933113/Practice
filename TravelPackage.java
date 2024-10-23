import java.io.Serializable;

public class TravelPackage implements Serializable {
    private String customerId;
    private String startDate;
    private int duration;
    private String accommodation;
    private String skiingLevel;  // New field for skiing level
    private int liftPass;        // Number of lift pass days

    // Constructor
    public TravelPackage(String customerId, String startDate, int duration, String accommodation, String skiingLevel) {
        this.customerId = customerId;
        this.startDate = startDate;
        this.duration = duration;
        this.accommodation = accommodation;
        this.skiingLevel = skiingLevel;  // Initialize skiing level
        this.liftPass = 0;               // Default lift pass is 0
    }

    // Method to add lift pass
    public void addLiftPass(int days) {
        this.liftPass += days;
    }

    // Method to calculate total cost
    public double calculateTotalCost() {
        // Assume lift pass costs $50 per day (adjust as needed)
        double liftPassCost = liftPass * 50;
        return liftPassCost;  // Only return the lift pass cost
    }

    // Overriding toString() to display the package details
    @Override
    public String toString() {
        return "------------------------------" + "\n" +
               "Customer ID: " + customerId + "\n" +
               "Start Date: " + startDate + "\n" +
               "Duration: " + duration + " days\n" +
               "Room Number: " + accommodation + "\n" + 
               "Lift Pass: " + liftPass + " days\n" +
               "Skiing Level: " + skiingLevel + "\n" +
               "------------------------------";
    }

    // Getter for customerId
    public String getCustomerId() {
        return customerId;
    }
}
