// Complete Constructor Chaining Example: Light and LED Classes
class Light {
    private int brightness;
    private String color;
    private boolean isOn;
    
    // Default constructor
    public Light() {
        this(50, "White");
        System.out.println("Light() - Default constructor");
    }
    
    // Constructor with brightness only
    public Light(int brightness) {
        this(brightness, "White");
        System.out.println("Light(int) - Brightness constructor");
    }
    
    // Full constructor
    public Light(int brightness, String color) {
        System.out.println("Light(int, String) - Full constructor");
        this.brightness = brightness;
        this.color = color;
        this.isOn = false;
    }
    
    public void turnOn() { isOn = true; }
    
    public void displayInfo() {
        System.out.println("Light: " + brightness + "%, " + color + ", " + (isOn ? "ON" : "OFF"));
    }
}

class LED extends Light {
    private String ledType;
    private int powerConsumption;
    
    // Default constructor
    public LED() {
        super();
        this.ledType = "Standard";
        this.powerConsumption = 5;
        System.out.println("LED() - Default LED constructor");
    }
    
    // Constructor with brightness - uses this() chaining
    public LED(int brightness) {
        this(brightness, "RGB");
        System.out.println("LED(int) - Brightness constructor");
    }
    
    // Constructor with brightness and type
    public LED(int brightness, String ledType) {
        super(brightness);
        this.ledType = ledType;
        this.powerConsumption = brightness / 10;
        System.out.println("LED(int, String) - Type constructor");
    }
    
    // Full constructor
    public LED(int brightness, String color, String ledType, int power) {
        super(brightness, color);
        this.ledType = ledType;
        this.powerConsumption = power;
        System.out.println("LED(int, String, String, int) - Full constructor");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("LED Type: " + ledType + ", Power: " + powerConsumption + "W");
    }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        System.out.println("=== Light Constructor Chaining ===");
        Light light1 = new Light();
        light1.displayInfo();
        
        System.out.println("\n=== LED Constructor Chaining ===");
        LED led1 = new LED();
        led1.displayInfo();
        
        System.out.println("\n=== LED with this() Chaining ===");
        LED led2 = new LED(80);
        led2.displayInfo();
        
        System.out.println("\n=== LED Full Constructor ===");
        LED led3 = new LED(100, "Red", "High-Power", 15);
        led3.displayInfo();
    }
}
