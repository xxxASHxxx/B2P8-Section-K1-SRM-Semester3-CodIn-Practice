// Access Modifiers Example: Tool and Hammer Classes
class Tool {
    private String serialNumber;      // Only accessible within Tool class
    protected String material;        // Accessible in Tool and subclasses
    public String brand;             // Accessible everywhere
    String category;                 // Package-private (default access)
    
    public Tool(String serialNumber, String material, String brand, String category) {
        this.serialNumber = serialNumber;
        this.material = material;
        this.brand = brand;
        this.category = category;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    protected void displayToolInfo() {
        System.out.println("Tool Info: " + serialNumber + ", " + material + ", " + brand);
    }
    
    private void internalCheck() {
        System.out.println("Internal check for: " + serialNumber);
    }
    
    public void performCheck() {
        internalCheck();
    }
}

class Hammer extends Tool {
    private double weight;
    
    public Hammer(String serialNumber, String material, String brand, 
                  String category, double weight) {
        super(serialNumber, material, brand, category);
        this.weight = weight;
    }
    
    public void testFieldAccess() {
        System.out.println("=== FIELD ACCESSIBILITY TEST ===");
        
        // ACCESSIBLE: Public field
        System.out.println("Public field 'brand': " + brand);
        
        // ACCESSIBLE: Protected field
        System.out.println("Protected field 'material': " + material);
        
        // ACCESSIBLE: Package-private field
        System.out.println("Package field 'category': " + category);
        
        // NOT ACCESSIBLE: Private field - must use getter
        System.out.println("Private field via getter: " + getSerialNumber());
        
        // ACCESSIBLE: Protected method
        displayToolInfo();
        
        // NOT ACCESSIBLE: Private method internalCheck() - compile error
    }
    
    public void modifyFields() {
        brand = "DeWalt";           // Can modify public
        material = "Steel";         // Can modify protected
        category = "Hand Tool";     // Can modify package-private
        // serialNumber = "NEW123"; // Cannot modify private - compile error
    }
}

public class AccessModifiersDemo {
    public static void main(String[] args) {
        Hammer hammer = new Hammer("H001", "Carbon Steel", "Stanley", 
                                  "Construction", 1.5);
        
        hammer.testFieldAccess();
        hammer.modifyFields();
        
        System.out.println("\n=== EXTERNAL ACCESS ===");
        System.out.println("Public field: " + hammer.brand);
        System.out.println("Via getter: " + hammer.getSerialNumber());
        // System.out.println(hammer.material); // Protected - compile error from external class
    }
}
