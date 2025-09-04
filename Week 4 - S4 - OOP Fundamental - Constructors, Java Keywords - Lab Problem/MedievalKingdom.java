import java.util.Arrays;

// Abstract base class for all magical structures
abstract class MagicalStructure {
    protected String name;
    protected int power;
    protected String loc;
    protected boolean active;
    
    // Main constructor
    public MagicalStructure(String name, int power, String loc, boolean active) {
        this.name = name;
        this.power = power;
        this.loc = loc;
        this.active = active;ā
    }
    
    // Constructor with default active state
    public MagicalStructure(String name, int power, String loc) {
        this(name, power, loc, true);
    }
    
    // Constructor with default location and active state
    public MagicalStructure(String name, int power) {
        this(name, power, "Unknown Location", true);
    }
    
    // Abstract method each structure must implement
    public abstract void castMagicSpell();
    
    public void displayInfo() {
        System.out.println(name + " - Power: " + power + ", Location: " + loc + 
                          ", Active: " + (active ? "Yes" : "No"));
    }
}

// Wizard Tower class
class WizardTower extends MagicalStructure {
    private int cap;
    private String[] spells;
    
    // Full constructor
    public WizardTower(String name, int power, String loc, int cap, String[] spells) {
        super(name, power, loc);
        this.cap = cap;
        this.spells = spells;
    }
    
    // Constructor with basic spells
    public WizardTower(String name, int power, String loc) {
        this(name, power, loc, 5, new String[]{"Fireball", "Lightning", "Shield"});
    }
    
    // Empty tower constructor
    public WizardTower(String name, String loc) {
        this(name, 10, loc, 3, new String[]{"Basic Magic"});
    }
    
    public void castMagicSpell() {
        System.out.println(name + " casts spells: " + Arrays.toString(spells));
    }
    
    public int getCap() { return cap; }
    public void setCap(int cap) { this.cap = cap; }
}

// Enchanted Castle class
class EnchantedCastle extends MagicalStructure {
    private int def;
    private boolean bridge;
    
    // Impregnable fortress constructor
    public EnchantedCastle(String name, int power, String loc, int def, boolean bridge) {
        super(name, power, loc);
        this.def = def;
        this.bridge = bridge;
    }
    
    // Royal castle constructor
    public EnchantedCastle(String name, String loc, boolean bridge) {
        this(name, 25, loc, 50, bridge);
    }
    
    // Simple fort constructor
    public EnchantedCastle(String name, String loc) {
        this(name, 15, loc, 20, false);
    }
    
    public void castMagicSpell() {
        System.out.println(name + " activates magical defenses! Defense: " + def);
    }
    
    public int getDef() { return def; }
    public void setDef(int def) { this.def = def; }
}

// Mystic Library class
class MysticLibrary extends MagicalStructure {
    private int books;
    private String lang;
    
    // Ancient collection constructor
    public MysticLibrary(String name, int power, String loc, int books, String lang) {
        super(name, power, loc);
        this.books = books;
        this.lang = lang;
    }
    
    // Basic library constructor
    public MysticLibrary(String name, String loc, int books) {
        this(name, 12, loc, books, "Common");
    }
    
    // Small library constructor
    public MysticLibrary(String name, String loc) {
        this(name, loc, 100);
    }
    
    public void castMagicSpell() {
        System.out.println(name + " reveals ancient knowledge! Books: " + books + 
                          " (" + lang + " language)");
    }
    
    public int getBooks() { return books; }
}

// Dragon Lair class
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasure;
    
    // Full lair constructor
    public DragonLair(String name, int power, String loc, String dragonType, int treasure) {
        super(name, power, loc);
        this.dragonType = dragonType;
        this.treasure = treasure;
    }
    
    // Standard dragon constructor
    public DragonLair(String name, String loc, String dragonType) {
        this(name, 30, loc, dragonType, 5000);
    }
    
    // Basic lair constructor
    public DragonLair(String name, String loc) {
        this(name, loc, "Fire Dragon");
    }
    
    public void castMagicSpell() {
        System.out.println(name + " unleashes " + dragonType + " power! Treasure: " + treasure);
    }
    
    public int getTreasure() { return treasure; }
}

// Kingdom Manager class
class KingdomManager {
    // Check if structures can interact
    public static boolean canInteract(MagicalStructure s1, MagicalStructure s2) {
        if (!s1.active || !s2.active) return false;
        
        if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) return true;
        if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) return true;
        if (s1 instanceof WizardTower && s2 instanceof WizardTower) return true;
        
        return false;
    }
    
    // Perform magic battle between structures
    public static String performBattle(MagicalStructure att, MagicalStructure def) {
        int attPower = att.power;
        int defPower = def.power;
        
        if (att instanceof DragonLair) attPower += 10; // Dragons are fierce
        if (def instanceof EnchantedCastle) defPower += 15; // Castles have defenses
        
        if (attPower > defPower) {
            return att.name + " defeats " + def.name + "!";
        } else {
            return def.name + " withstands " + att.name + "'s attack!";
        }
    }
    
    // Calculate total kingdom magic power
    public static int calcTotalPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) {
            if (s.active) total += s.power;
        }
        return total;
    }
    
    // Apply special combination effects
    public static void applySpecialEffects(MagicalStructure[] structures) {
        for (int i = 0; i < structures.length; i++) {
            for (int j = i + 1; j < structures.length; j++) {
                MagicalStructure s1 = structures[i];
                MagicalStructure s2 = structures[j];
                
                // Tower + Library = Knowledge boost
                if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) {
                    WizardTower tower = (WizardTower) s1;
                    tower.setCap(tower.getCap() * 2);
                    System.out.println("Knowledge boost! " + tower.name + " spell capacity doubled!");
                }
                
                // Castle + Dragon = Dragon guard
                if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) {
                    EnchantedCastle castle = (EnchantedCastle) s1;
                    castle.setDef(castle.getDef() * 3);
                    System.out.println("Dragon guard! " + castle.name + " defense tripled!");
                }
            }
        }
    }
    
    // Categorize structures and calculate taxes
    public static void manageKingdom(MagicalStructure[] structures) {
        int towers = 0, castles = 0, libraries = 0, lairs = 0;
        int totalTax = 0;
        
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) {
                towers++;
                totalTax += 100; // Tower tax
            } else if (s instanceof EnchantedCastle) {
                castles++;
                totalTax += 200; // Castle tax
            } else if (s instanceof MysticLibrary) {
                libraries++;
                totalTax += 50; // Library tax
            } else if (s instanceof DragonLair) {
                lairs++;
                totalTax += 300; // Dragon tax
            }
        }
        
        System.out.println("\n=== KINGDOM STATISTICS ===");
        System.out.println("Towers: " + towers + ", Castles: " + castles + 
                          ", Libraries: " + libraries + ", Dragon Lairs: " + lairs);
        System.out.println("Total Tax Revenue: " + totalTax + " gold");
        
        // Determine specialization
        if (towers >= 3) {
            System.out.println("Kingdom Specialization: MAGIC-FOCUSED");
        } else if (castles >= 2) {
            System.out.println("Kingdom Specialization: DEFENSE-FOCUSED");
        } else if (libraries >= 2) {
            System.out.println("Kingdom Specialization: KNOWLEDGE-FOCUSED");
        } else if (lairs >= 1) {
            System.out.println("Kingdom Specialization: DRAGON-PROTECTED");
        } else {
            System.out.println("Kingdom Specialization: BALANCED");
        }
    }
}

public class MedievalKingdom {
    public static void main(String[] args) {
        System.out.println("=== MEDIEVAL KINGDOM BUILDER ===\n");
        
        // Create various magical structures using different constructors
        MagicalStructure[] kingdom = {
            new WizardTower("Mystic Spire", "North Hill"),
            new WizardTower("Arcane Tower", 20, "East Gate", 8, 
                           new String[]{"Teleport", "Invisibility", "Time Stop"}),
            new EnchantedCastle("Royal Fortress", "Central Plaza"),
            new EnchantedCastle("Dragon Keep", "Mountain Peak", true),
            new MysticLibrary("Ancient Archive", "Scholar Quarter"),
            new MysticLibrary("Grand Library", 18, "City Center", 5000, "Draconic"),
            new DragonLair("Ember Cavern", "Volcano Slopes"),
            new DragonLair("Crystal Lair", 35, "Crystal Caves", "Ice Dragon", 10000)
        };
        
        // Display all structures
        System.out.println("Kingdom Structures:");
        for (MagicalStructure s : kingdom) {
            s.displayInfo();
        }
        
        // Test magic spells
        System.out.println("\n=== CASTING SPELLS ===");
        for (MagicalStructure s : kingdom) {
            s.castMagicSpell();
        }
        
        // Apply special combination effects
        System.out.println("\n=== SPECIAL COMBINATIONS ===");
        KingdomManager.applySpecialEffects(kingdom);
        
        // Test interactions
        System.out.println("\n=== STRUCTURE INTERACTIONS ===");
        MagicalStructure tower = kingdom[0];
        MagicalStructure library = kingdom[4];
        System.out.println("Can " + tower.name + " interact with " + library.name + "? " + 
                          KingdomManager.canInteract(tower, library));
        
        // Test battles
        System.out.println("\n=== MAGIC BATTLES ===");
        System.out.println(KingdomManager.performBattle(kingdom[6], kingdom[2])); // Dragon vs Castle
        System.out.println(KingdomManager.performBattle(kingdom[1], kingdom[7])); // Tower vs Dragon
        
        // Kingdom management
        System.out.println("Total Kingdom Power: " + KingdomManager.calcTotalPower(kingdom));
        KingdomManager.manageKingdom(kingdom);
    }
}
