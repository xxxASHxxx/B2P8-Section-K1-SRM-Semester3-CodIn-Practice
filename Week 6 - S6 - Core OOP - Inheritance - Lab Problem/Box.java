// Parent class
class Box {
    protected String contents;
    protected boolean isPacked;
    
    public Box(String contents) {
        this.contents = contents;
        this.isPacked = false;
        System.out.println("Box created for: " + contents);
    }
    
    public void pack() {
        if (!isPacked) {
            System.out.println("Packing " + contents + " in a standard box");
            isPacked = true;
            System.out.println("Box is now packed and sealed");
        } else {
            System.out.println("Box is already packed");
        }
    }
    
    public void unpack() {
        if (isPacked) {
            System.out.println("Opening the box seal");
            System.out.println("Unpacking " + contents + " from the box");
            isPacked = false;
            System.out.println("Box is now empty and open");
        } else {
            System.out.println("Box is already unpacked");
        }
    }
    
    public void displayStatus() {
        System.out.println("Box Status - Contents: " + contents + ", Packed: " + isPacked);
    }
}

// Child class extending Box
class GiftBox extends Box {
    private String wrappingPaper;
    private String ribbon;
    private String giftTag;
    
    public GiftBox(String contents, String wrappingPaper, String ribbon, String giftTag) {
        super(contents); // Call parent constructor
        this.wrappingPaper = wrappingPaper;
        this.ribbon = ribbon;
        this.giftTag = giftTag;
        System.out.println("Gift box created with special decorations");
    }
    
    @Override
    public void pack() {
        // First call parent pack() method to do basic packing
        super.pack();
        
        // Add gift-specific functionality after parent functionality
        if (isPacked) {
            System.out.println("--- Gift Enhancement Process ---");
            System.out.println("Wrapping with " + wrappingPaper + " paper");
            System.out.println("Tying with " + ribbon + " ribbon");
            System.out.println("Attaching gift tag: " + giftTag);
            System.out.println("Beautiful gift box is ready for presentation!");
        }
    }
    
    @Override
    public void unpack() {
        if (isPacked) {
            System.out.println("--- Gift Unwrapping Process ---");
            System.out.println("Reading gift tag: " + giftTag);
            System.out.println("Carefully removing " + ribbon + " ribbon");
            System.out.println("Unwrapping " + wrappingPaper + " paper");
            
            // Then call parent unpack() method for basic unpacking
            super.unpack();
            
            System.out.println("Gift has been beautifully unwrapped!");
        } else {
            super.unpack(); // Call parent method for consistency
        }
    }
    
    public void displayGiftDetails() {
        displayStatus(); // Inherited method
        System.out.println("Gift Details - Paper: " + wrappingPaper + 
                         ", Ribbon: " + ribbon + ", Tag: " + giftTag);
    }
}

// Test class
public class BoxTest {
    public static void main(String[] args) {
        System.out.println("=== Regular Box Demo ===");
        Box regularBox = new Box("Books");
        regularBox.pack();
        regularBox.displayStatus();
        System.out.println();
        regularBox.unpack();
        
        System.out.println("\n" + "=".repeat(50));
        
        System.out.println("=== Gift Box Demo (Enhanced with super) ===");
        GiftBox giftBox = new GiftBox("Jewelry", "Golden", "Red Silk", "Happy Birthday!");
        
        System.out.println("\n--- Packing Process ---");
        giftBox.pack(); // Uses super.pack() + gift enhancements
        
        System.out.println("\n--- Status Check ---");
        giftBox.displayGiftDetails();
        
        System.out.println("\n--- Unpacking Process ---");
        giftBox.unpack(); // Uses gift unwrapping + super.unpack()
        
        System.out.println("\n--- Trying to pack again ---");
        giftBox.pack(); // Test already packed scenario
        
        System.out.println("\n--- Trying to unpack again ---");
        giftBox.unpack(); // Test already unpacked scenario
    }
}
