// MultilevelInheritanceDemo.java

// Base class Animal
class Animal {
    // Protected fields for inheritance
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;
    
    // Constructor
    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }
    
    // Basic animal methods
    public void eat() {
        System.out.println("Animal is eating");
    }
    
    public void sleep() {
        System.out.println("Animal is sleeping");
    }
    
    public void move() {
        System.out.println("Animal is moving");
    }
    
    public String getAnimalInfo() {
        return String.format("Species: %s, Habitat: %s, Lifespan: %d years, Wildlife: %s", 
                species, habitat, lifespan, isWildlife);
    }
}

// Intermediate class Mammal
class Mammal extends Animal {
    // Mammal-specific fields
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod;
    
    // Constructor
    public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                  String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife); // Call parent constructor
        this.furColor = furColor;
        this.hasWarmBlood = true; // Always true for mammals
        this.gestationPeriod = gestationPeriod;
        System.out.println("Mammal constructor: Adding mammal traits");
    }
    
    // Override parent method
    @Override
    public void move() {
        super.move(); // Call parent implementation
        System.out.println("Mammal is walking/running");
    }
    
    // Mammal-specific methods
    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }
    
    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

// Final class Dog
class Dog extends Mammal {
    // Dog-specific fields
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;
    
    // Constructor 1: Basic dog with defaults
    public Dog(String breed) {
        super("Canis lupus", "Domestic", 12, false, "Brown", 63);
        this.breed = breed;
        this.isDomesticated = true;
        this.loyaltyLevel = 8;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }
    
    // Constructor 2: Detailed dog with all parameters
    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod, String breed, 
               boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }
    
    // Constructor 3: Copy constructor
    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod, other.breed,
             other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
        System.out.println("Dog copy constructor called");
    }
    
    // Override methods from inheritance chain
    @Override
    public void eat() {
        super.eat();
        System.out.println("Wagging tail while eating");
    }
    
    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }
    
    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }
    
    // Dog-specific methods
    public void bark() {
        System.out.println("Woof! Woof!");
    }
    
    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }
    
    public void showLoyalty() {
        System.out.println("Loyalty level: " + loyaltyLevel + "/10 - Very loyal!");
    }
    
    // Demonstrate inheritance chain
    public void demonstrateInheritance() {
        System.out.println("\n=== Demonstrating Inheritance Chain ===");
        // Animal level
        System.out.println("Animal level:");
        System.out.println(getAnimalInfo());
        
        // Mammal level  
        System.out.println("Mammal level:");
        nurse();
        regulateTemperature();
        
        // Dog level
        System.out.println("Dog level:");
        bark();
        fetch();
        showLoyalty();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testing Multilevel Constructor Chaining ===");
        
        // Test basic constructor
        System.out.println("\n1. Basic Dog Constructor:");
        Dog dog1 = new Dog("Golden Retriever");
        
        // Test detailed constructor
        System.out.println("\n2. Detailed Dog Constructor:");
        Dog dog2 = new Dog("Canis lupus", "Home", 15, false, "Golden", 63, 
                          "Labrador", true, 10, "Swimming");
        
        // Test copy constructor
        System.out.println("\n3. Copy Constructor:");
        Dog dog3 = new Dog(dog1);
        
        System.out.println("\n=== Testing Method Overriding ===");
        
        // Test overridden methods
        System.out.println("\n4. Testing eat() method:");
        dog1.eat();
        
        System.out.println("\n5. Testing move() method:");
        dog1.move();
        
        System.out.println("\n6. Testing sleep() method:");
        dog1.sleep();
        
        System.out.println("\n=== Testing Inheritance Access ===");
        
        // Access inherited fields
        System.out.println("\n7. Accessing inherited fields:");
        System.out.println("Species: " + dog1.species);
        System.out.println("Fur Color: " + dog1.furColor);
        System.out.println("Has Warm Blood: " + dog1.hasWarmBlood);
        
        System.out.println("\n=== Testing IS-A Relationships ===");
        
        // Test instanceof
        System.out.println("\n8. Testing instanceof:");
        System.out.println("dog1 instanceof Dog: " + (dog1 instanceof Dog));
        System.out.println("dog1 instanceof Mammal: " + (dog1 instanceof Mammal));
        System.out.println("dog1 instanceof Animal: " + (dog1 instanceof Animal));
        
        // Demonstrate inheritance chain
        dog1.demonstrateInheritance();
        
        System.out.println("\n=== Polymorphism Test ===");
        
        // Test polymorphic behavior
        Animal animal = new Dog("Beagle");
        animal.move(); // Calls Dog's overridden move()
        animal.eat();  // Calls Dog's overridden eat()
    }
}
