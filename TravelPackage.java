public class TravelPackage {
    private String customerId;
    private String startDate;
    private int duration;
    private Accommodation accommodation;
    private double liftPass;
    private double lessonFee;

    public TravelPackage(String customerId, String startDate, int duration) {
        this.customerId = customerId;
        this.startDate = startDate;
        this.duration = duration;
        this.liftPass = 0;
        this.lessonFee = 0;
    }

    public void addAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public void addLiftPass(int days) {
        if (days >= 5) {
            liftPass = days * 26 * 0.9; // 10% discount for 5 days or more
        } else {
            liftPass = days * 26;
        }
    }

    public void addLessonFee(String level, int lessons) {
        switch (level.toLowerCase()) {
            case "Lift Pass Only":
                lessonFee = lessons * 15;
                break;
            case "Lift Pass and Basic Lesson":
                lessonFee = lessons * 20;
                break;
            case "Lift Pass and Full Lesson":
                lessonFee = lessons * 25;
                break;
        }
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Start Date: " + startDate + ", Duration: " + duration +
               "\nAccommodation: " + (accommodation != null ? accommodation.toString() : "None") +
               "\nLift Pass: $" + liftPass + ", Lesson Fee: $" + lessonFee;
    }
    
}
     