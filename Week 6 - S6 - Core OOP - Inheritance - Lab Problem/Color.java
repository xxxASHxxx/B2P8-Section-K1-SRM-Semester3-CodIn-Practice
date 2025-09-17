// Grandparent class
class Color {
    protected String name;
    
    public Color(String name) {
        System.out.println("Color constructor called");
        this.name = name;
    }
    
    public void displayColor() {
        System.out.println("Color: " + name);
    }
}

// Parent class extending Color
class PrimaryColor extends Color {
    protected int intensity; // 1-10 scale
    
    public PrimaryColor(String name, int intensity) {
        super(name); // Calls Color constructor
        System.out.println("PrimaryColor constructor called");
        this.intensity = intensity;
    }
    
    public void displayPrimary() {
        System.out.println("Primary Color: " + name + ", Intensity: " + intensity + "/10");
    }
}

// Child class extending PrimaryColor
class RedColor extends PrimaryColor {
    protected String shade; // light, medium, dark
    
    public RedColor(String name, int intensity, String shade) {
        super(name, intensity); // Calls PrimaryColor constructor
        System.out.println("RedColor constructor called");
        this.shade = shade;
    }
    
    public void displayRed() {
        System.out.println("Red Color: " + name + ", Intensity: " + intensity + "/10, Shade: " + shade);
    }
    
    // Method demonstrating access to all inherited fields
    public void displayComplete() {
        System.out.println("Complete Info - Name: " + name + 
                         ", Intensity: " + intensity + 
                         ", Shade: " + shade);
    }
}

// Test class
public class ColorTest {
    public static void main(String[] args) {
        System.out.println("=== Creating RedColor object (Multilevel Inheritance) ===");
        RedColor red = new RedColor("Crimson Red", 8, "Dark");
        
        System.out.println("\n=== Calling methods from all levels ===");
        red.displayColor();     // From Color class
        red.displayPrimary();   // From PrimaryColor class
        red.displayRed();       // From RedColor class
        red.displayComplete();  // From RedColor class (accesses all fields)
        
        System.out.println("\n=== Creating another RedColor object ===");
        RedColor red2 = new RedColor("Rose Red", 5, "Light");
        red2.displayComplete();
        
        System.out.println("\n=== Direct field access ===");
        System.out.println("Name: " + red.name);
        System.out.println("Intensity: " + red.intensity);
        System.out.println("Shade: " + red.shade);
    }
}
