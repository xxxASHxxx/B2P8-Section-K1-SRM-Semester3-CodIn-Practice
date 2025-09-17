// Base class
class Instrument {
    protected String name;
    protected String material;
    
    public Instrument(String name, String material) {
        System.out.println("Instrument constructor called");
        this.name = name;
        this.material = material;
    }
    
    public void play() {
        System.out.println("Playing " + name + " made of " + material);
    }
    
    public void displayInfo() {
        System.out.println("Instrument: " + name + ", Material: " + material);
    }
}

// Child class 1 - Piano
class Piano extends Instrument {
    protected int keys;
    protected String type; // acoustic, digital
    
    public Piano(String name, String material, int keys, String type) {
        super(name, material);
        System.out.println("Piano constructor called");
        this.keys = keys;
        this.type = type;
    }
    
    @Override
    public void play() {
        System.out.println("Playing " + keys + "-key " + type + " " + name + " with beautiful melodies");
    }
    
    public void displayPiano() {
        System.out.println("Piano: " + name + ", Keys: " + keys + ", Type: " + type + ", Material: " + material);
    }
}

// Child class 2 - Guitar
class Guitar extends Instrument {
    protected int strings;
    protected String guitarType; // acoustic, electric
    
    public Guitar(String name, String material, int strings, String guitarType) {
        super(name, material);
        System.out.println("Guitar constructor called");
        this.strings = strings;
        this.guitarType = guitarType;
    }
    
    @Override
    public void play() {
        System.out.println("Strumming " + strings + "-string " + guitarType + " " + name + " with rhythm");
    }
    
    public void displayGuitar() {
        System.out.println("Guitar: " + name + ", Strings: " + strings + ", Type: " + guitarType + ", Material: " + material);
    }
}

// Child class 3 - Drum
class Drum extends Instrument {
    protected String drumType; // snare, bass, tom
    protected int diameter; // in inches
    
    public Drum(String name, String material, String drumType, int diameter) {
        super(name, material);
        System.out.println("Drum constructor called");
        this.drumType = drumType;
        this.diameter = diameter;
    }
    
    @Override
    public void play() {
        System.out.println("Beating " + diameter + "\" " + drumType + " " + name + " with powerful beats");
    }
    
    public void displayDrum() {
        System.out.println("Drum: " + name + ", Type: " + drumType + ", Diameter: " + diameter + "\", Material: " + material);
    }
}

// Test class
public class InstrumentTest {
    public static void main(String[] args) {
        // Create array of Instrument references (Hierarchical inheritance)
        Instrument[] orchestra = {
            new Piano("Grand Piano", "Mahogany Wood", 88, "Acoustic"),
            new Guitar("Fender Stratocaster", "Maple Wood", 6, "Electric"),
            new Drum("Snare Drum", "Birch Wood", "Snare", 14),
            new Piano("Yamaha Digital", "Plastic", 61, "Digital"),
            new Guitar("Martin Acoustic", "Rosewood", 6, "Acoustic"),
            new Drum("Bass Drum", "Oak Wood", "Bass", 22)
        };
        
        System.out.println("\n=== Orchestra Performance (Polymorphism Demo) ===");
        for (Instrument instrument : orchestra) {
            instrument.displayInfo();
            instrument.play(); // Calls overridden method based on actual type
            System.out.println();
        }
        
        System.out.println("=== Individual Instrument Details ===");
        Piano piano = new Piano("Stein
