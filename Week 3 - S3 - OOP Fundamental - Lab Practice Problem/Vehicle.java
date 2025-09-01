public class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int timesRented;
    private double totalEarnings;
   
    private static int totalVehicles = 0;
    private static double totalRevenue = 0.0;
    private static String companyName = "RentACar Solutions";
    private static int rentalDays = 0;
   
    public Vehicle(String brand, String model, double rentPerDay) {
        this.vehicleId = generateVehicleId();
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.timesRented = 0;
        this.totalEarnings = 0.0;
        totalVehicles++;
        System.out.println("Vehicle added: " + brand + " " + model + " (ID: " + vehicleId + ")");
    }
   
    public boolean rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is currently not available.");
            return false;
        }
       
        double rentAmount = calculateRent(days);
        isAvailable = false;
        timesRented++;
        totalEarnings += rentAmount;
        rentalDays += days;
       
        System.out.println("Vehicle " + brand + " " + model + " rented for " + days + " days.");
        System.out.println("Rental amount: $" + String.format("%.2f", rentAmount));
        return true;
    }
   
    public void returnVehicle() {
        if (isAvailable) {
            System.out.println("Vehicle " + vehicleId + " was not rented out.");
        } else {
            isAvailable = true;
            System.out.println("Vehicle " + brand + " " + model + " has been returned and is now available.");
        }
    }
   
    public double calculateRent(int days) {
        double amount = rentPerDay * days;
        totalRevenue += amount;
        return amount;
    }
   
    public void displayVehicleInfo() {
        String status = isAvailable ? "Available" : "Rented";
        System.out.println("=== Vehicle Information ===");
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent per Day: $" + rentPerDay);
        System.out.println("Status: " + status);
        System.out.println("Times Rented: " + timesRented);
        System.out.println("Total Earnings: $" + String.format("%.2f", totalEarnings));
        System.out.println("========================");
    }
   
    public static void setCompanyName(String name) {
        companyName = name;
        System.out.println("Company name updated to: " + companyName);
    }
   
    public static double getTotalRevenue() {
        return totalRevenue;
    }
   
    public static double getAverageRentPerDay() {
        if (rentalDays == 0) {
            return 0.0;
        }
        return totalRevenue / rentalDays;
    }
   
    public static void displayCompanyStats() {
        System.out.println("\n==========================================");
        System.out.println("         " + companyName.toUpperCase());
        System.out.println("         COMPANY STATISTICS");
        System.out.println("==========================================");
        System.out.println("Total Vehicles in Fleet: " + totalVehicles);
        System.out.println("Total Revenue Generated: $" + String.format("%.2f", totalRevenue));
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Revenue per Day: $" + String.format("%.2f", getAverageRentPerDay()));
        System.out.println("==========================================");
    }
   
    private static String generateVehicleId() {
        return "VEH" + String.format("%03d", totalVehicles + 1);
    }
   
    public String getVehicleId() {
        return vehicleId;
    }
   
    public boolean isAvailable() {
        return isAvailable;
    }
   
    public static void main(String[] args) {
        System.out.println("=== Creating Vehicle Fleet ===");
        Vehicle car1 = new Vehicle("Toyota", "Camry", 45.00);
        Vehicle car2 = new Vehicle("Honda", "Civic", 40.00);
        Vehicle suv1 = new Vehicle("Ford", "Explorer", 65.00);
        Vehicle truck1 = new Vehicle("Chevrolet", "Silverado", 75.00);
        Vehicle luxury1 = new Vehicle("BMW", "X5", 120.00);
       
        System.out.println("\n=== Initial Company Statistics ===");
        Vehicle.displayCompanyStats();
       
        System.out.println("\n=== Demonstrating Static vs Instance Variables ===");
        System.out.println("All vehicles see the same company name (static): " + companyName);
        System.out.println("Car1 vehicle ID (instance): " + car1.getVehicleId());
        System.out.println("Car2 vehicle ID (instance): " + car2.getVehicleId());
        System.out.println("Total vehicles (static): " + Vehicle.totalVehicles);
       
        System.out.println("\n=== Vehicle Rental Operations ===");
        car1.rentVehicle(3);
        car2.rentVehicle(5);
        suv1.rentVehicle(2);
        truck1.rentVehicle(7);
       
        System.out.println("\n=== Attempting to Rent Already Rented Vehicle ===");
        car1.rentVehicle(4);
       
        System.out.println("\n=== Updated Company Statistics After Rentals ===");
        Vehicle.displayCompanyStats();
       
        System.out.println("\n=== Individual Vehicle Information ===");
        car1.displayVehicleInfo();
        car2.displayVehicleInfo();
        suv1.displayVehicleInfo();
       
        System.out.println("\n=== Returning Vehicles ===");
        car1.returnVehicle();
        suv1.returnVehicle();
       
        System.out.println("\n=== Renting Returned Vehicles Again ===");
        car1.rentVehicle(4);
        luxury1.rentVehicle(10);
       
        System.out.println("\n=== Changing Company Name (Static Method) ===");
        Vehicle.setCompanyName("Premium Auto Rentals");
       
        System.out.println("\n=== Final Company Statistics ===");
        Vehicle.displayCompanyStats();
       
        System.out.println("\n=== Final Vehicle Status ===");
        Vehicle[] fleet = {car1, car2, suv1, truck1, luxury1};
        for (Vehicle vehicle : fleet) {
            System.out.println(vehicle.getVehicleId() + " - Available: " + vehicle.isAvailable());
        }
       
        System.out.println("\n=== Static vs Instance Summary ===");
        System.out.println("Static variables (shared by all objects):");
        System.out.println("- Company Name: " + companyName);
        System.out.println("- Total Vehicles: " + Vehicle.totalVehicles);
        System.out.println("- Total Revenue: $" + String.format("%.2f", Vehicle.getTotalRevenue()));
        System.out.println("\nInstance variables (unique to each object):");
        for (int i = 0; i < 3; i++) {
            System.out.println("- " + fleet[i].brand + " " + fleet[i].model +
                             " (ID: " + fleet[i].getVehicleId() +
                             ", Available: " + fleet[i].isAvailable() + ")");
        }
    }
}
