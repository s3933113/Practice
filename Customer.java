public class Customer {
    private String id;
    private String name;
    private String skiingLevel; // beginner, intermediate, expert

    public Customer(String id, String name, String skiingLevel) {
        this.id = id;
        this.name = name;
        this.skiingLevel = skiingLevel;
    }
    

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSkiingLevel() {
        return skiingLevel;
    }

    

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Skiing Level: " + skiingLevel;
    }
}
