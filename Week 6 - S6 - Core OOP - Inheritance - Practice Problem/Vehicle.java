import java.util.Random;

// Vehicle.java
public class Vehicle {
    // Protected fields for inheritance
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;
    
    // Private fields requiring getter/setter access
    private String registrationNumber;
    private boolean isRunning;
    
    // Default constructor
    public Vehicle() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 2020;
        this.engineType = "Standard";
        this.registrationNumber = generateRegistration();
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }
    
    // Parameterized constructor
    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = generateRegistration();
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }
    
    // Basic vehicle operations
    public void start() {
        this.isRunning = true;
        System.out.println("Vehicle started");
    }
    
    public void stop() {
        this.isRunning = false;
        System.out.println("Vehicle stopped");
    }
    
    public String getVehicleInfo() {
        return String.format("Brand: %s, Model: %s, Year: %d, Engine: %s, Registration: %s, Running: %s",
                brand, model, year, engineType, registrationNumber, isRunning);
    }
    
    public void displaySpecs() {
        System.out.println("=== Vehicle Specifications ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Engine Type: " + engineType);
    }
    
    // Getter/setter methods
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    
    public boolean isRunning() {
        return isRunning;
    }
    
    private String generateRegistration() {
        Random rand = new Random();
        return "REG" + (1000 + rand.nextInt(9000));
    }
}

// Car class extending Vehicle
class Car extends Vehicle {
    // Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;
    
    // Default constructor
    public Car() {
        super(); // Explicit call to parent constructor
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }
    
    // Parameterized constructor
    public Car(String brand, String model, int year, String engineType, 
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Explicit call to parent constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }
    
    // Override parent methods
    @Override
    public void start() {
        super.start(); // Call parent implementation first
        System.out.println("Car engine warming up...");
        System.out.println("Dashboard lights initialized");
    }
    
    @Override
    public void displaySpecs() {
        super.displaySpecs(); // Show vehicle specs first
        System.out.println("=== Car Specifications ===");
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
    }
    
    // Car-specific methods
    public void openTrunk() {
        System.out.println("Trunk opened");
    }
    
    public void playRadio() {
        System.out.println("Radio playing music");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testing Constructor Chaining ===");
        
        // Test default constructor
        System.out.println("\n1. Creating Car with default constructor:");
        Car car1 = new Car();
        
        // Test parameterized constructor
        System.out.println("\n2. Creating Car with parameterized constructor:");
        Car car2 = new Car("Toyota", "Camry", 2023, "V6", 4, "Hybrid", "Automatic");
        
        System.out.println("\n=== Testing Inheritance ===");
        
        // Access protected fields from parent
        System.out.println("\n3. Accessing inherited protected fields:");
        System.out.println("Car2 brand: " + car2.brand);
        System.out.println("Car2 model: " + car2.model);
        
        // Call inherited methods
        System.out.println("\n4. Calling inherited methods:");
        System.out.println(car2.getVehicleInfo());
        
        // Call overridden methods
        System.out.println("\n5. Testing overridden methods:");
        car2.start(); // Overridden method
        car2.displaySpecs(); // Overridden method
        
        System.out.println("\n=== Testing Method Resolution ===");
        
        // Methods that exist only in Car
        System.out.println("\n6. Car-specific methods:");
        car2.openTrunk();
        car2.playRadio();
        
        // Methods that exist in both Vehicle and Car
        System.out.println("\n7. Testing polymorphic behavior:");
        Vehicle vehicle = new Car("Honda", "Civic", 2022, "4-Cylinder", 2, "Petrol", "Manual");
        vehicle.start(); // Calls overridden Car.start()
        vehicle.displaySpecs(); // Calls overridden Car.displaySpecs()
        
        // Stop the vehicles
        System.out.println("\n8. Stopping vehicles:");
        car2.stop();
        vehicle.stop();
    }
}
