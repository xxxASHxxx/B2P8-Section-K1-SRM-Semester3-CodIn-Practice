// Parent class
class Phone {
    protected String brand;
    protected String model;
    
    // Default constructor
    public Phone() {
        System.out.println("Phone default constructor called");
        this.brand = "Unknown";
        this.model = "Generic";
    }
    
    // Parameterized constructor
    public Phone(String brand, String model) {
        System.out.println("Phone parameterized constructor called");
        this.brand = brand;
        this.model = model;
    }
    
    public void displayPhone() {
        System.out.println("Phone: " + brand + " " + model);
    }
}

// Child class extending Phone
class SmartPhone extends Phone {
    protected String operatingSystem;
    
    // Default constructor
    public SmartPhone() {
        super(); // Calls Phone default constructor
        System.out.println("SmartPhone default constructor called");
        this.operatingSystem = "Android";
    }
    
    // Constructor with OS only
    public SmartPhone(String operatingSystem) {
        super(); // Calls Phone default constructor
        System.out.println("SmartPhone OS constructor called");
        this.operatingSystem = operatingSystem;
    }
    
    // Full parameterized constructor
    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model); // Calls Phone parameterized constructor
        System.out.println("SmartPhone full constructor called");
        this.operatingSystem = operatingSystem;
    }
    
    public void displaySmartPhone() {
        System.out.println("SmartPhone: " + brand + " " + model + " (" + operatingSystem + ")");
    }
}

// Test class
public class PhoneTest {
    public static void main(String[] args) {
        System.out.println("=== Creating SmartPhone with default constructor ===");
        SmartPhone phone1 = new SmartPhone();
        phone1.displaySmartPhone();
        
        System.out.println("\n=== Creating SmartPhone with OS only ===");
        SmartPhone phone2 = new SmartPhone("iOS");
        phone2.displaySmartPhone();
        
        System.out.println("\n=== Creating SmartPhone with all parameters ===");
        SmartPhone phone3 = new SmartPhone("Samsung", "Galaxy S23", "Android");
        phone3.displaySmartPhone();
    }
}
