public class Car {
    private String brand;
    private String model;
    private int year;
    private String color;
    private boolean isRunning;
   
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }
   
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("The " + color + " " + brand + " " + model + " engine is now running!");
        } else {
            System.out.println("Engine is already running.");
        }
    }
   
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println("The " + brand + " " + model + " engine has been turned off.");
        } else {
            System.out.println("Engine is already off.");
        }
    }
   
    public void displayInfo() {
        String status = isRunning ? "Running" : "Parked";
        System.out.println("Car Details:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Status: " + status);
        System.out.println("Age: " + getAge() + " years old");
        System.out.println("------------------------");
    }
   
    public int getAge() {
        return 2025 - year;
    }
   
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Camry", 2018, "Silver");
        Car friendsCar = new Car("Honda", "Civic", 2020, "Blue");
        Car familyCar = new Car("Ford", "Explorer", 2015, "Black");
       
        System.out.println("=== Testing My Car ===");
        myCar.displayInfo();
        myCar.startEngine();
        myCar.displayInfo();
        myCar.startEngine();
        myCar.stopEngine();
       
        System.out.println("\n=== Testing Friend's Car ===");
        friendsCar.displayInfo();
        friendsCar.startEngine();
        friendsCar.displayInfo();
       
        System.out.println("\n=== Testing Family Car ===");
        familyCar.displayInfo();
        familyCar.startEngine();
        familyCar.stopEngine();
        familyCar.stopEngine();
       
        // How is this similar to real-world cars?
        // Just like real cars, each Car object has its own unique characteristics
        // (brand, model, year, color) that don't change once the car is "manufactured" (created).
        // Each car can independently be started or stopped without affecting other cars,
        // mimicking how real cars operate independently in the real world.
        // The age calculation reflects how we determine a real car's age based on model year.
        // Most importantly, each car maintains its own state - when I start my car,
        // it doesn't start my neighbor's car, just like these objects maintain separate states.
       
        System.out.println("\n=== Final Status Check ===");
        myCar.displayInfo();
        friendsCar.displayInfo();
        familyCar.displayInfo();
    }
}
