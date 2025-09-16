import java.util.*;


public final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;
   
    public KingdomConfig(String kingdomName, int foundingYear,
                        String[] allowedStructureTypes,
                        Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.trim().isEmpty()) {
            throw new IllegalArgumentException("Kingdom name cannot be null or empty");
        }
        if (foundingYear < 0) {
            throw new IllegalArgumentException("Founding year cannot be negative");
        }
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0) {
            throw new IllegalArgumentException("Allowed structure types cannot be null or empty");
        }
        if (resourceLimits == null) {
            throw new IllegalArgumentException("Resource limits cannot be null");
        }
       
        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = allowedStructureTypes.clone(); // Defensive copy
       
        // Deep copy of resource limits map
        this.resourceLimits = new HashMap<>();
        for (Map.Entry<String, Integer> entry : resourceLimits.entrySet()) {
            this.resourceLimits.put(entry.getKey(), entry.getValue());
        }
    }
   
    // Only getters, no setters
    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
   
    // Return defensive copies
    public String[] getAllowedStructureTypes() {
        return allowedStructureTypes.clone();
    }
   
    public Map<String, Integer> getResourceLimits() {
        Map<String, Integer> copy = new HashMap<>();
        for (Map.Entry<String, Integer> entry : resourceLimits.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }
   
    // Factory methods
    public static KingdomConfig createDefaultKingdom() {
        String[] defaultTypes = {"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"};
        Map<String, Integer> defaultLimits = new HashMap<>();
        defaultLimits.put("Gold", 10000);
        defaultLimits.put("Magic", 5000);
        defaultLimits.put("Population", 1000);
       
        return new KingdomConfig("Default Kingdom", 1200, defaultTypes, defaultLimits);
    }
   
    public static KingdomConfig createFromTemplate(String type) {
        Map<String, Integer> limits = new HashMap<>();
        String[] types;
       
        switch (type.toLowerCase()) {
            case "magical":
                types = new String[]{"WizardTower", "MysticLibrary", "DragonLair"};
                limits.put("Gold", 15000);
                limits.put("Magic", 8000);
                limits.put("Population", 800);
                return new KingdomConfig("Magical Realm", 800, types, limits);
               
            case "fortress":
                types = new String[]{"EnchantedCastle", "WizardTower"};
                limits.put("Gold", 20000);
                limits.put("Magic", 3000);
                limits.put("Population", 1500);
                return new KingdomConfig("Fortress Kingdom", 1000, types, limits);
               
            default:
                return createDefaultKingdom();
        }
    }
   
    @Override
    public String toString() {
        return String.format("KingdomConfig{name='%s', founded=%d, structures=%d, resources=%d}",
            kingdomName, foundingYear, allowedStructureTypes.length, resourceLimits.size());
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof KingdomConfig)) return false;
        KingdomConfig other = (KingdomConfig) obj;
        return Objects.equals(kingdomName, other.kingdomName) &&
               foundingYear == other.foundingYear;
    }
   
    @Override
    public int hashCode() {
        return Objects.hash(kingdomName, foundingYear);
    }
}
import java.util.Objects;
import java.util.UUID;


public class MagicalStructure {
    // Private immutable identity
    private final String structureId;
    private final long constructionTimestamp;
   
    // Private immutable properties
    private final String structureName;
    private final String location;
   
    // Controlled mutable state
    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;
   
    // Package constants
    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
   
    // Global constant
    public static final String MAGIC_SYSTEM_VERSION = "3.0";
   
    // Constructor chaining - basic constructor
    public MagicalStructure(String name, String location) {
        this(name, location, 100); // Default power level
    }
   
    // Constructor with power
    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true); // Default active state
    }
   
    // Main constructor - all others chain to this
    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Structure name cannot be null or empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) {
            throw new IllegalArgumentException("Magic power must be between " +
                MIN_MAGIC_POWER + " and " + MAX_MAGIC_POWER);
        }
       
        this.structureId = generateStructureId();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = power;
        this.isActive = active;
        this.currentMaintainer = "None";
    }
   
    // JavaBean getters and setters
    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }
    public String getStructureId() { return structureId; }
    public long getConstructionTimestamp() { return constructionTimestamp; }
   
    public int getMagicPower() { return magicPower; }
   
    public void setMagicPower(int magicPower) {
        if (magicPower < MIN_MAGIC_POWER || magicPower > MAX_MAGIC_POWER) {
            throw new IllegalArgumentException("Magic power must be between " +
                MIN_MAGIC_POWER + " and " + MAX_MAGIC_POWER);
        }
        this.magicPower = magicPower;
    }
   
    public boolean isActive() { return isActive; }
   
    public void setActive(boolean active) {
        this.isActive = active;
    }
   
    public String getCurrentMaintainer() { return currentMaintainer; }
   
    public void setCurrentMaintainer(String maintainer) {
        this.currentMaintainer = maintainer != null ? maintainer : "None";
    }
   
    private String generateStructureId() {
        return "STR_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
   
    @Override
    public String toString() {
        return String.format("%s at %s (Power: %d, Active: %s)",
            structureName, location, magicPower, isActive);
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MagicalStructure)) return false;
        MagicalStructure other = (MagicalStructure) obj;
        return Objects.equals(structureId, other.structureId);
    }
   
    @Override
    public int hashCode() {
        return Objects.hash(structureId);
    }
}
import java.util.*;


public class WizardTower {
    private final MagicalStructure baseStructure;
    private final int maxSpellCapacity;
    private final List<String> knownSpells;
    private String currentWizard;
   
    // Empty tower constructor
    public WizardTower(String name, String location) {
        this(name, location, 10, new ArrayList<>(), "None");
    }
   
    // Basic spells constructor
    public WizardTower(String name, String location, int capacity) {
        this(name, location, capacity, createBasicSpells(), "Apprentice Wizard");
    }
   
    // Fully equipped constructor
    public WizardTower(String name, String location, int capacity,
                      List<String> spells, String wizard) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Spell capacity must be positive");
        }
       
        this.baseStructure = new MagicalStructure(name, location, 200, true);
        this.maxSpellCapacity = capacity;
        this.knownSpells = new ArrayList<>(spells != null ? spells : new ArrayList<>());
        this.currentWizard = wizard != null ? wizard : "None";
    }
   
    private static List<String> createBasicSpells() {
        return Arrays.asList("Light", "Shield", "Detect Magic");
    }
   
    // JavaBean methods
    public int getMaxSpellCapacity() { return maxSpellCapacity; }
   
    public List<String> getKnownSpells() {
        return new ArrayList<>(knownSpells); // Defensive copy
    }
   
    public boolean addSpell(String spell) {
        if (spell != null && knownSpells.size() < maxSpellCapacity) {
            return knownSpells.add(spell);
        }
        return false;
    }
   
    public String getCurrentWizard() { return currentWizard; }
   
    public void setCurrentWizard(String wizard) {
        this.currentWizard = wizard != null ? wizard : "None";
    }
   
    // Delegate to base structure
    public String getName() { return baseStructure.getStructureName(); }
    public String getLocation() { return baseStructure.getLocation(); }
    public int getMagicPower() { return baseStructure.getMagicPower(); }
    public boolean isActive() { return baseStructure.isActive(); }
   
    @Override
    public String toString() {
        return String.format("Wizard Tower '%s' - Wizard: %s, Spells: %d/%d",
            getName(), currentWizard, knownSpells.size(), maxSpellCapacity);
    }
}
public class EnchantedCastle {
    private final MagicalStructure baseStructure;
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;
   
    // Simple fort constructor
    public EnchantedCastle(String name, String location) {
        this(name, location, "Fort", 50, false);
    }
   
    // Royal castle constructor
    public EnchantedCastle(String name, String location, String type) {
        this(name, location, type, type.equals("Royal") ? 80 : 60, true);
    }
   
    // Impregnable fortress constructor
    public EnchantedCastle(String name, String location, String type,
                          int defenseRating, boolean hasDrawbridge) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Castle type cannot be null or empty");
        }
        if (defenseRating < 0 || defenseRating > 100) {
            throw new IllegalArgumentException("Defense rating must be between 0 and 100");
        }
       
        int magicPower = type.equals("Fortress") ? 300 : 150;
        this.baseStructure = new MagicalStructure(name, location, magicPower, true);
        this.castleType = type;
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }
   
    // JavaBean methods
    public String getCastleType() { return castleType; }
   
    public int getDefenseRating() { return defenseRating; }
   
    public void setDefenseRating(int rating) {
        if (rating < 0 || rating > 100) {
            throw new IllegalArgumentException("Defense rating must be between 0 and 100");
        }
        this.defenseRating = rating;
    }
   
    public boolean hasDrawbridge() { return hasDrawbridge; }
   
    public void setHasDrawbridge(boolean hasDrawbridge) {
        this.hasDrawbridge = hasDrawbridge;
    }
   
    // Delegate to base structure
    public String getName() { return baseStructure.getStructureName(); }
    public String getLocation() { return baseStructure.getLocation(); }
    public int getMagicPower() { return baseStructure.getMagicPower(); }
    public boolean isActive() { return baseStructure.isActive(); }
   
    @Override
    public String toString() {
        return String.format("%s Castle '%s' - Defense: %d, Drawbridge: %s",
            castleType, getName(), defenseRating, hasDrawbridge ? "Yes" : "No");
    }
}
import java.util.*;


public class MysticLibrary {
    private final MagicalStructure baseStructure;
    private final Map<String, String> bookCollection;
    private int knowledgeLevel;
   
    // Few books constructor
    public MysticLibrary(String name, String location) {
        this(name, location, createBasicBooks(), 25);
    }
   
    // Moderate collection constructor
    public MysticLibrary(String name, String location, Map<String, String> books) {
        this(name, location, books, 50);
    }
   
    // Ancient archives constructor
    public MysticLibrary(String name, String location,
                        Map<String, String> books, int knowledgeLevel) {
        if (knowledgeLevel < 0 || knowledgeLevel > 100) {
            throw new IllegalArgumentException("Knowledge level must be between 0 and 100");
        }
       
        this.baseStructure = new MagicalStructure(name, location, 120, true);
        this.bookCollection = new HashMap<>(books != null ? books : new HashMap<>());
        this.knowledgeLevel = knowledgeLevel;
    }
   
    private static Map<String, String> createBasicBooks() {
        Map<String, String> books = new HashMap<>();
        books.put("Magic Basics", "Introduction to magical theory");
        books.put("Herb Lore", "Guide to magical plants");
        books.put("Crystal Magic", "Using crystals for spells");
        return books;
    }
   
    // JavaBean methods
    public Map<String, String> getBookCollection() {
        return new HashMap<>(bookCollection); // Defensive copy
    }
   
    public boolean addBook(String title, String description) {
        if (title != null && description != null) {
            bookCollection.put(title, description);
            return true;
        }
        return false;
    }
   
    public int getKnowledgeLevel() { return knowledgeLevel; }
   
    public void setKnowledgeLevel(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("Knowledge level must be between 0 and 100");
        }
        this.knowledgeLevel = level;
    }
   
    // Delegate to base structure
    public String getName() { return baseStructure.getStructureName(); }
    public String getLocation() { return baseStructure.getLocation(); }
    public int getMagicPower() { return baseStructure.getMagicPower(); }
    public boolean isActive() { return baseStructure.isActive(); }
   
    @Override
    public String toString() {
        return String.format("Mystic Library '%s' - Books: %d, Knowledge: %d",
            getName(), bookCollection.size(), knowledgeLevel);
    }
}
public class DragonLair {
    private final MagicalStructure baseStructure;
    private final String dragonType;
    private long treasureValue;
    private final int territorialRadius;
   
    // Red dragon constructor
    public DragonLair(String name, String location, String dragonType) {
        this(name, location, dragonType, getDefaultTreasure(dragonType),
             getDefaultRadius(dragonType));
    }
   
    // Custom lair constructor
    public DragonLair(String name, String location, String dragonType,
                     long treasureValue, int territorialRadius) {
        if (dragonType == null || dragonType.trim().isEmpty()) {
            throw new IllegalArgumentException("Dragon type cannot be null or empty");
        }
        if (treasureValue < 0) {
            throw new IllegalArgumentException("Treasure value cannot be negative");
        }
        if (territorialRadius <= 0) {
            throw new IllegalArgumentException("Territorial radius must be positive");
        }
       
        int magicPower = getMagicPowerForDragon(dragonType);
        this.baseStructure = new MagicalStructure(name, location, magicPower, true);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
        this.territorialRadius = territorialRadius;
    }
   
    private static long getDefaultTreasure(String dragonType) {
        switch (dragonType.toLowerCase()) {
            case "ancient": return 100000;
            case "adult": return 50000;
            case "young": return 10000;
            default: return 25000;
        }
    }
   
    private static int getDefaultRadius(String dragonType) {
        switch (dragonType.toLowerCase()) {
            case "ancient": return 50;
            case "adult": return 30;
            case "young": return 10;
            default: return 20;
        }
    }
   
    private static int getMagicPowerForDragon(String dragonType) {
        switch (dragonType.toLowerCase()) {
            case "ancient": return 800;
            case "adult": return 500;
            case "young": return 200;
            default: return 350;
        }
    }
   
    // JavaBean methods
    public String getDragonType() { return dragonType; }
   
    public long getTreasureValue() { return treasureValue; }
   
    public void setTreasureValue(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Treasure value cannot be negative");
        }
        this.treasureValue = value;
    }
   
    public int getTerritorialRadius() { return territorialRadius; }
   
    public void addTreasure(long amount) {
        if (amount > 0) {
            treasureValue += amount;
        }
    }
   
    // Delegate to base structure
    public String getName() { return baseStructure.getStructureName(); }
    public String getLocation() { return baseStructure.getLocation(); }
    public int getMagicPower() { return baseStructure.getMagicPower(); }
    public boolean isActive() { return baseStructure.isActive(); }
   
    @Override
    public String toString() {
        return String.format("%s Dragon Lair '%s' - Treasure: %d gold, Territory: %d km",
            dragonType, getName(), treasureValue, territorialRadius);
    }
}
import java.util.*;


public class KingdomManager {
    private final List<Object> structures;
    private final KingdomConfig config;
   
    public KingdomManager(KingdomConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Kingdom config cannot be null");
        }
        this.config = config;
        this.structures = new ArrayList<>();
    }
   
    // Add structure with type validation
    public boolean addStructure(Object structure) {
        if (structure == null) return false;
       
        String category = determineStructureCategory(structure);
        if (Arrays.asList(config.getAllowedStructureTypes()).contains(category)) {
            structures.add(structure);
            return true;
        }
        return false;
    }
   
    public List<Object> getStructures() {
        return new ArrayList<>(structures); // Defensive copy
    }
   
    public KingdomConfig getConfig() { return config; }
   
    // Static method using instanceof for interaction checking
    public static boolean canStructuresInteract(Object s1, Object s2) {
        if (s1 == null || s2 == null) return false;
       
        // WizardTowers can interact with Libraries
        if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) return true;
        if (s1 instanceof MysticLibrary && s2 instanceof WizardTower) return true;
       
        // Castles can interact with any structure for defense
        if (s1 instanceof EnchantedCastle || s2 instanceof EnchantedCastle) return true;
       
        // Dragons are territorial and don't interact well with others
        if (s1 instanceof DragonLair || s2 instanceof DragonLair) return false;
       
        // Base structures can interact
        if (s1 instanceof MagicalStructure && s2 instanceof MagicalStructure) return true;
       
        return false;
    }
   
    // Static method for magic battle using instanceof
    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker == null || defender == null) {
            return "Invalid battle participants";
        }
       
        int attackPower = getMagicPower(attacker);
        int defensePower = getMagicPower(defender);
       
        String attackerType = getStructureTypeName(attacker);
        String defenderType = getStructureTypeName(defender);
       
        // Special instanceof-based battle logic
        if (attacker instanceof DragonLair && defender instanceof EnchantedCastle) {
            EnchantedCastle castle = (EnchantedCastle) defender;
            defensePower += castle.getDefenseRating();
            return String.format("Dragon attacks Castle! Attack: %d vs Defense: %d - %s wins!",
                attackPower, defensePower, attackPower > defensePower ? "Dragon" : "Castle");
        }
       
        if (attacker instanceof WizardTower && defender instanceof DragonLair) {
            WizardTower tower = (WizardTower) attacker;
            attackPower += tower.getKnownSpells().size() * 10;
            return String.format("Wizard challenges Dragon! Magic: %d vs Power: %d - %s wins!",
                attackPower, defensePower, attackPower > defensePower ? "Wizard" : "Dragon");
        }
       
        return String.format("%s vs %s - Power %d vs %d - %s wins!",
            attackerType, defenderType, attackPower, defensePower,
            attackPower > defensePower ? attackerType : defenderType);
    }
   
    // Static method for kingdom power calculation
    public static int calculateKingdomPower(Object[] structures) {
        if (structures == null) return 0;
       
        int totalPower = 0;
        for (Object structure : structures) {
            if (structure == null) continue;
           
            int basePower = getMagicPower(structure);
           
            // Bonus calculations using instanceof
            if (structure instanceof WizardTower) {
                WizardTower tower = (WizardTower) structure;
                basePower += tower.getKnownSpells().size() * 5;
            } else if (structure instanceof EnchantedCastle) {
                EnchantedCastle castle = (EnchantedCastle) structure;
                basePower += castle.getDefenseRating();
            } else if (structure instanceof MysticLibrary) {
                MysticLibrary library = (MysticLibrary) structure;
                basePower += library.getKnowledgeLevel();
            } else if (structure instanceof DragonLair) {
                DragonLair lair = (DragonLair) structure;
                basePower += (int)(lair.getTreasureValue() / 1000); // Gold bonus
            }
           
            totalPower += basePower;
        }
       
        return totalPower;
    }
   
    // Private helper method for structure type identification
    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) return "WizardTower";
        if (structure instanceof EnchantedCastle) return "EnchantedCastle";
        if (structure instanceof MysticLibrary) return "MysticLibrary";
        if (structure instanceof DragonLair) return "DragonLair";
        if (structure instanceof MagicalStructure) return "MagicalStructure";
        return "Unknown";
    }
   
    // Static helper methods
    private static int getMagicPower(Object structure) {
        if (structure instanceof WizardTower) {
            return ((WizardTower) structure).getMagicPower();
        } else if (structure instanceof EnchantedCastle) {
            return ((EnchantedCastle) structure).getMagicPower();
        } else if (structure instanceof MysticLibrary) {
            return ((MysticLibrary) structure).getMagicPower();
        } else if (structure instanceof DragonLair) {
            return ((DragonLair) structure).getMagicPower();
        } else if (structure instanceof MagicalStructure) {
            return ((MagicalStructure) structure).getMagicPower();
        }
        return 0;
    }
   
    private static String getStructureTypeName(Object structure) {
        if (structure instanceof WizardTower) return "Wizard Tower";
        if (structure instanceof EnchantedCastle) return "Enchanted Castle";
        if (structure instanceof MysticLibrary) return "Mystic Library";
        if (structure instanceof DragonLair) return "Dragon Lair";
        if (structure instanceof MagicalStructure) return "Magical Structure";
        return "Unknown Structure";
    }
   
    @Override
    public String toString() {
        return String.format("Kingdom Manager - %s with %d structures",
            config.getKingdomName(), structures.size());
    }
}
import java.util.*;


public class MedievalKingdomDemo {
    public static void main(String[] args) {
        System.out.println("=== Medieval Kingdom Management System ===");
        System.out.println("Magic System Version: " + MagicalStructure.MAGIC_SYSTEM_VERSION);
       
        // Test immutable KingdomConfig
        System.out.println("\n=== Kingdom Configuration ===");
        KingdomConfig defaultKingdom = KingdomConfig.createDefaultKingdom();
        KingdomConfig magicalRealm = KingdomConfig.createFromTemplate("magical");
        KingdomConfig fortress = KingdomConfig.createFromTemplate("fortress");
       
        System.out.println("Default: " + defaultKingdom);
        System.out.println("Magical: " + magicalRealm);
        System.out.println("Fortress: " + fortress);
       
        // Test constructor chaining in MagicalStructure
        System.out.println("\n=== Base Magical Structures ===");
        MagicalStructure simple = new MagicalStructure("Simple Tower", "Forest");
        MagicalStructure powered = new MagicalStructure("Powered Shrine", "Mountain", 500);
        MagicalStructure complete = new MagicalStructure("Complete Temple", "Valley", 800, true);
       
        System.out.println("Simple: " + simple);
        System.out.println("Powered: " + powered);
        System.out.println("Complete: " + complete);
       
        // Test specialized structures with different constructors
        System.out.println("\n=== Specialized Structures ===");
       
        // WizardTower variations
        WizardTower emptyTower = new WizardTower("Empty Tower", "Hilltop");
        WizardTower basicTower = new WizardTower("Basic Tower", "Riverside", 15);
        List<String> advancedSpells = Arrays.asList("Fireball", "Teleport", "Time Stop", "Meteor");
        WizardTower advancedTower = new WizardTower("Advanced Tower", "Cloudpeak", 25,
                                                   advancedSpells, "Archmage Gandalf");
       
        System.out.println("Empty: " + emptyTower);
        System.out.println("Basic: " + basicTower);
        System.out.println("Advanced: " + advancedTower);
       
        // EnchantedCastle variations
        EnchantedCastle simpleFort = new EnchantedCastle("Border Fort", "Northern Pass");
        EnchantedCastle royalCastle = new EnchantedCastle("Royal Palace", "Capital City", "Royal");
        EnchantedCastle fortress2 = new EnchantedCastle("Impregnable Keep", "Iron Mountain",
                                                       "Fortress", 95, true);
       
        System.out.println("Fort: " + simpleFort);
        System.out.println("Royal: " + royalCastle);
        System.out.println("Fortress: " + fortress2);
       
        // MysticLibrary variations
        MysticLibrary smallLibrary = new MysticLibrary("Village Archive", "Town Square");
        Map<String, String> moderateBooks = new HashMap<>();
        moderateBooks.put("Advanced Alchemy", "Potion brewing techniques");
        moderateBooks.put("Dragon Lore", "Study of dragon behavior");
        moderateBooks.put("Dimensional Magic", "Portal creation guide");
        MysticLibrary moderateLibrary = new MysticLibrary("Scholar's Haven", "University", moderateBooks);
       
        System.out.println("Small: " + smallLibrary);
        System.out.println("Moderate: " + moderateLibrary);
       
        // DragonLair variations
        DragonLair youngLair = new DragonLair("Hatchling Cave", "Small Valley", "Young");
        DragonLair adultLair = new DragonLair("Ancient Cavern", "Volcanic Peak", "Ancient", 150000, 60);
       
        System.out.println("Young: " + youngLair);
        System.out.println("Ancient: " + adultLair);
       
        // Test KingdomManager with instanceof usage
        System.out.println("\n=== Kingdom Management ===");
        KingdomManager manager = new KingdomManager(defaultKingdom);
       
        manager.addStructure(advancedTower);
        manager.addStructure(royalCastle);
        manager.addStructure(moderateLibrary);
        manager.addStructure(adultLair);
       
        System.out.println("Manager: " + manager);
       
        // Test structure interactions using instanceof
        System.out.println("\n=== Structure Interactions ===");
        System.out.println("Tower-Library interaction: " +
            KingdomManager.canStructuresInteract(advancedTower, moderateLibrary));
        System.out.println("Castle-Dragon interaction: " +
            KingdomManager.canStructuresInteract(royalCastle, adultLair));
        System.out.println("Dragon-Tower interaction: " +
            KingdomManager.canStructuresInteract(adultLair, advancedTower));
       
        // Test magic battles using instanceof
        System.out.println("\n=== Magic Battles ===");
        System.out.println(KingdomManager.performMagicBattle(adultLair, royalCastle));
        System.out.println(KingdomManager.performMagicBattle(advancedTower, adultLair));
        System.out.println(KingdomManager.performMagicBattle(royalCastle, advancedTower));
       
        // Calculate kingdom power
        Object[] allStructures = {advancedTower, royalCastle, moderateLibrary, adultLair};
        int totalPower = KingdomManager.calculateKingdomPower(allStructures);
        System.out.println("\nTotal Kingdom Power: " + totalPower);
       
        // Test JavaBean compliance
        System.out.println("\n=== JavaBean Testing ===");
        advancedTower.setCurrentWizard("Master Merlin");
        royalCastle.setDefenseRating(90);
        moderateLibrary.setKnowledgeLevel(75);
        adultLair.addTreasure(25000);
       
        System.out.println("Updated Tower: " + advancedTower);
        System.out.println("Updated Castle: " + royalCastle);
        System.out.println("Updated Library: " + moderateLibrary);
        System.out.println("Updated Lair: " + adultLair);
    }
}


