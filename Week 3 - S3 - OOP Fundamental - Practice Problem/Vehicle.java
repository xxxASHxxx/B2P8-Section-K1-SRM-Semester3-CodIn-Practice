public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;
    protected boolean isRunning;
    protected String vehicleType;
   
    public Vehicle(String make, String model, int year, double fuelLevel, String vehicleType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
        this.isRunning = false;
        this.vehicleType = vehicleType;
    }
   
    public void startVehicle() {
        if (fuelLevel > 0) {
            isRunning = true;
            System.out.println("The " + year + " " + make + " " + model + " is now running.");
        } else {
            System.out.println("Cannot start - no fuel remaining!");
        }
    }
   
    public void stopVehicle() {
        isRunning = false;
        System.out.println("The " + make + " " + model + " has been turned off.");
    }
   
    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println("Added " + amount + " gallons. Fuel level: " + fuelLevel + " gallons");
    }
   
    public void displayVehicleInfo() {
        String status = isRunning ? "Running" : "Stopped";
        System.out.println(vehicleType + " Details:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Fuel Level: " + fuelLevel + " gallons");
        System.out.println("Status: " + status);
        System.out.println("------------------");
    }
   
    public static void main(String[] args) {
        Car familyCar = new Car("Honda", "Pilot", 2021, 12.5);
        Truck workTruck = new Truck("Ford", "F-150", 2020, 18.0, 1500);
        Motorcycle sportBike = new Motorcycle("Yamaha", "R6", 2022, 4.2, true);
       
        Vehicle[] fleet = {familyCar, workTruck, sportBike};
       
        System.out.println("=== Starting All Vehicles ===");
        for (Vehicle vehicle : fleet) {
            vehicle.startVehicle();
            vehicle.displayVehicleInfo();
        }
       
        System.out.println("=== Demonstrating Unique Behaviors ===");
        familyCar.openTrunk();
        workTruck.loadCargo(800);
        sportBike.performWheelStand();
       
        // How does this show reusability?
        // The Vehicle class provides a foundation that eliminates duplicate code.
        // Instead of writing separate start/stop/refuel methods for each vehicle type,
        // we write them once and inherit them everywhere. This reduces maintenance
        // and ensures consistent behavior across all vehicle types.
       
        System.out.println("\n=== Refueling All Vehicles ===");
        for (Vehicle vehicle : fleet) {
            vehicle.refuel(5.0);
        }
       
        // How could this be extended for new vehicle types?
        // Adding a new vehicle type like Bus or Airplane only requires:
        // 1. Create a new class that extends Vehicle
        // 2. Add type-specific features (like Bus.openDoors() or Airplane.takeOff())
        // 3. The new type automatically gets all basic vehicle functionality
        // 4. It can be used in Vehicle arrays and loops without code changes
       
        System.out.println("\n=== Testing Truck-Specific Features ===");
        workTruck.loadCargo(700);
        workTruck.unloadCargo(500);
        workTruck.displayVehicleInfo();
       
        // What are the benefits over writing separate classes?
        // 1. Code reuse: Common functionality written once, used everywhere
        // 2. Consistency: All vehicles behave the same for basic operations
        // 3. Maintainability: Bug fixes in Vehicle class benefit all subclasses
        // 4. Polymorphism: Can treat different vehicle types uniformly in arrays/loops
        // 5. Extensibility: Adding features to Vehicle automatically benefits all types
       
        System.out.println("\n=== Final Status Check ===");
        for (Vehicle vehicle : fleet) {
            vehicle.stopVehicle();
        }
    }
}


class Car extends Vehicle {
    private boolean trunkOpen;
   
    public Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel, "Car");
        this.trunkOpen = false;
    }
   
    public void openTrunk() {
        trunkOpen = true;
        System.out.println("Trunk opened on the " + make + " " + model);
    }
   
    public void closeTrunk() {
        trunkOpen = false;
        System.out.println("Trunk closed on the " + make + " " + model);
    }
}


class Truck extends Vehicle {
    private double cargoCapacity;
    private double currentCargo;
   
    public Truck(String make, String model, int year, double fuelLevel, double cargoCapacity) {
        super(make, model, year, fuelLevel, "Truck");
        this.cargoCapacity = cargoCapacity;
        this.currentCargo = 0.0;
    }
   
    public void loadCargo(double weight) {
        if (currentCargo + weight <= cargoCapacity) {
            currentCargo += weight;
            System.out.println("Loaded " + weight + " lbs. Current cargo: " + currentCargo + " lbs");
        } else {
            System.out.println("Cannot load - would exceed capacity of " + cargoCapacity + " lbs");
        }
    }
   
    public void unloadCargo(double weight) {
        if (weight <= currentCargo) {
            currentCargo -= weight;
            System.out.println("Unloaded " + weight + " lbs. Remaining cargo: " + currentCargo + " lbs");
        } else {
            System.out.println("Cannot unload - only " + currentCargo + " lbs in truck");
        }
    }
}


class Motorcycle extends Vehicle {
    private boolean hasSideCar;
   
    public Motorcycle(String make, String model, int year, double fuelLevel, boolean hasSideCar) {
        super(make, model, year, fuelLevel, "Motorcycle");
        this.hasSideCar = hasSideCar;
    }
   
    public void performWheelStand() {
        if (isRunning) {
            System.out.println("The " + make + " " + model + " performs an impressive wheelstand!");
        } else {
            System.out.println("Start the motorcycle first before attempting stunts!");
        }
    }
   
    public void leanIntoTurn() {
        if (isRunning) {
            System.out.println("Leaning into the turn on the " + make + " " + model);
        }
    }
}


