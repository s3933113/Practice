public class TravelPackage {
    private String customerId;  // Customer who owns the package
    private String startDate;   // Start date of the package
    private int duration;       // Duration in days
    private int liftPass;       // Duration of lift pass in days
    private String accommodation;  // Assigned accommodation (room number)
    private double liftPassCost;   // Cost for the lift pass
    private double lessonFee;      // Cost for the skiing lesson

    // Constructor to initialize the package
    public TravelPackage(String customerId, String startDate, int duration, String accommodation) {
        this.customerId = customerId;
        this.startDate = startDate;
        this.duration = duration;
        this.accommodation = accommodation;
        this.liftPassCost = 0;  // Default cost is 0
        this.lessonFee = 0;     // Default cost is 0
    }

    // Method to add a lift pass
    public void addLiftPass(int days) {
        this.liftPass = days;  // Assign lift pass days
        this.liftPassCost = days * 50;  // Example cost calculation: $50 per day
    }

    // Method to add lessons to the package
    public void addLessonFee(String level, int lessons) {
        switch (level.toLowerCase()) {
            case "beginner":
                lessonFee = lessons * 30;  // $30 per lesson for beginner
                break;
            case "intermediate":
                lessonFee = lessons * 50;  // $50 per lesson for intermediate
                break;
            case "expert":
                lessonFee = lessons * 70;  // $70 per lesson for expert
                break;
        }
    }

    // Method to calculate the total cost
    public double calculateTotalCost() {
        return liftPassCost + lessonFee;  // Total cost is the sum of lift pass and lesson fee
    }

    @Override
    public String toString() {
        return "Travel Package for Customer ID: " + customerId +
               "\nStart Date: " + startDate +
               "\nDuration: " + duration + " days" +
               "\nAccommodation: " + accommodation +
               "\nLift Pass: " + liftPass + " days, Cost: $" + liftPassCost +
               "\nLesson Fee: $" + lessonFee +
               "\nTotal Cost: $" + calculateTotalCost();
    }
}
