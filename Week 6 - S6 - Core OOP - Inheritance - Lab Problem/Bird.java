// Parent class
class Bird {
    protected String name;
    
    public Bird(String name) {
        this.name = name;
    }
    
    public void fly() {
        System.out.println(name + " is flying");
    }
    
    public void displayInfo() {
        System.out.println("Bird: " + name);
    }
}

// Child class - Penguin (cannot fly)
class Penguin extends Bird {
    public Penguin(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        System.out.println(name + " cannot fly, but can swim!");
    }
}

// Child class - Eagle (excellent flyer)
class Eagle extends Bird {
    public Eagle(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        System.out.println(name + " soars high in the sky with powerful wings!");
    }
}

// Test class demonstrating polymorphism
public class BirdTest {
    public static void main(String[] args) {
        // Create array of Bird references for polymorphism
        Bird[] birds = {
            new Bird("Generic Bird"),
            new Penguin("Emperor Penguin"), 
            new Eagle("Golden Eagle"),
            new Penguin("King Penguin"),
            new Eagle("Bald Eagle")
        };
        
        System.out.println("=== Demonstrating Method Overriding and Polymorphism ===");
        
        // Call fly() method on each bird - demonstrates runtime polymorphism
        for (Bird bird : birds) {
            bird.displayInfo();
            bird.fly();  // Different implementation based on actual object type
            System.out.println();
        }
        
        System.out.println("=== Individual Object Testing ===");
        Bird genericBird = new Bird("Robin");
        Bird penguin = new Penguin("Adelie");  // Polymorphic reference
        Bird eagle = new Eagle("Hawk");        // Polymorphic reference
        
        genericBird.fly();
        penguin.fly();     // Calls Penguin's overridden method
        eagle.fly();       // Calls Eagle's overridden method
    }
}
