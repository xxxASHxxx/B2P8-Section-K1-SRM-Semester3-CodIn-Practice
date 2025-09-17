// Parent class
class Fruit {
    protected String color;
    protected String taste;
    
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
    
    public void displayInfo() {
        System.out.println("Color: " + color + ", Taste: " + taste);
    }
}

// Child class extending Fruit
class Apple extends Fruit {
    protected String variety;
    
    public Apple(String color, String taste, String variety) {
        super(color, taste);  // Call parent constructor
        this.variety = variety;
    }
    
    public void displayAppleInfo() {
        System.out.println("Apple - Color: " + color + ", Taste: " + taste + ", Variety: " + variety);
    }
}

// Test class
public class FruitTest {
    public static void main(String[] args) {
        // Create Apple object
        Apple apple = new Apple("Red", "Sweet", "Fuji");
        
        // Access inherited fields and methods
        apple.displayInfo();        // From parent class
        apple.displayAppleInfo();   // From child class
        
        // Direct access to inherited protected fields
        System.out.println("Direct access - Color: " + apple.color);
        System.out.println("Direct access - Taste: " + apple.taste);
        System.out.println("Direct access - Variety: " + apple.variety);
    }
}
