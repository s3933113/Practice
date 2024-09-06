import java.util.ArrayList;

public class MtBullerResort {
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> packages = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addAccommodation(Accommodation accommodation) {
        accommodations.add(accommodation);
    }

    public void displayAccommodations() {
        for (Accommodation accommodation : accommodations) {
            System.out.println(accommodation);
        }
    }

    public Accommodation findAccommodationById(String id) {
        for (Accommodation accommodation : accommodations) {
            if (accommodation.getId().equals(id) && accommodation.isAvailable()) {
                return accommodation;
            }
        }
        return null;
    }

    public void createPackage(String customerId, String startDate, int duration) {
        TravelPackage newPackage = new TravelPackage(customerId, startDate, duration);
        System.out.println("Choose an accommodation by ID:");
        displayAccommodations();
        String accommodationId = Main.scanner.next();
        Accommodation accommodation = findAccommodationById(accommodationId);
        if (accommodation != null) {
            newPackage.addAccommodation(accommodation);
            accommodation.setAvailable(false);
        }
        packages.add(newPackage);
    }

    public void listCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void listPackages() {
        for (TravelPackage pkg : packages) {
            System.out.println(pkg);
        }
    }

    // Methods to add lift pass, lessons, save/load packages to/from file can be added similarly.
}
