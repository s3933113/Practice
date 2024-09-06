public class Accommodation {
    private String id;
    private String type; // Hotel, Lodge, or Apartment
    private double price;
    private boolean available;

    public Accommodation(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.available = true; // Default to available
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Type: " + type + ", Price: $" + price + ", Available: " + available;
    }
}
