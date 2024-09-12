import java.util.ArrayList;

public class MtBullerResort {
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<TravelPackage> packages = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer); // Check if the customer is being added
    }
    

    public void listCustomers() {
        System.out.println("Listing customers...");
        if (customers.isEmpty()) {
            System.out.println("No customers in the list.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Customer: " + customer);
            }
        }
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
        System.out.println("Creating package for Customer ID: " + customerId + ", Start Date: " + startDate + ", Duration: " + duration);
        
        TravelPackage newPackage = new TravelPackage(customerId, startDate, duration);
        System.out.println("Choose an accommodation by ID:");
        displayAccommodations();
        String accommodationId = Main.scanner.next();
        
        Accommodation accommodation = findAccommodationById(accommodationId);
        if (accommodation != null) {
            newPackage.addAccommodation(accommodation);
            accommodation.setAvailable(false);
            packages.add(newPackage);  // Ensure this happens
            System.out.println("Package added successfully!");
        } else {
            System.out.println("Accommodation not found or unavailable.");
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
