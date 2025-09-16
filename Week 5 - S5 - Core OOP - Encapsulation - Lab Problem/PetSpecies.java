public final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;
   
    public PetSpecies(String speciesName, String[] evolutionStages,
                     int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.trim().isEmpty()) {
            throw new IllegalArgumentException("Species name cannot be null or empty");
        }
        if (evolutionStages == null || evolutionStages.length == 0) {
            throw new IllegalArgumentException("Evolution stages cannot be null or empty");
        }
        if (maxLifespan <= 0) {
            throw new IllegalArgumentException("Max lifespan must be positive");
        }
        if (habitat == null || habitat.trim().isEmpty()) {
            throw new IllegalArgumentException("Habitat cannot be null or empty");
        }
       
        this.speciesName = speciesName;
        this.evolutionStages = evolutionStages.clone(); // Defensive copy
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }
   
    public String getSpeciesName() { return speciesName; }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }
   
    // Defensive copy for array getter
    public String[] getEvolutionStages() {
        return evolutionStages.clone();
    }
   
    @Override
    public String toString() {
        return "PetSpecies{name='" + speciesName + "', habitat='" + habitat +
               "', maxLifespan=" + maxLifespan + "}";
    }
}
import java.util.*;


public class VirtualPet {
    // Private immutable core
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;
   
    // Private mutable state
    private String petName;
    private int age;
    private int happiness;
    private int health;
   
    // Protected static constants
    protected static final String[] DEFAULT_EVOLUTION_STAGES =
        {"Baby", "Young", "Adult", "Elder"};
   
    // Package-private constants
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
   
    // Public global constant
    public static final String PET_SYSTEM_VERSION = "2.0";
   
    // Default species for constructors
    private static final PetSpecies DEFAULT_SPECIES =
        new PetSpecies("Generic", DEFAULT_EVOLUTION_STAGES, 50, "Home");
   
    // Constructor chaining - all chain to main constructor
    public VirtualPet() {
        this("Pet_" + System.currentTimeMillis(), DEFAULT_SPECIES, 50, 50);
    }
   
    public VirtualPet(String petName) {
        this(petName, DEFAULT_SPECIES, 50, 50);
    }
   
    public VirtualPet(String petName, PetSpecies species) {
        this(petName, species, 50, 50);
    }
   
    // Main constructor with full validation
    public VirtualPet(String petName, PetSpecies species, int happiness, int health) {
        if (petName == null || petName.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be null or empty");
        }
        if (species == null) {
            throw new IllegalArgumentException("Species cannot be null");
        }
       
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.birthTimestamp = System.currentTimeMillis();
        this.age = 0;
        this.happiness = validateStat(happiness);
        this.health = validateStat(health);
    }
   
    // JavaBean getters and setters
    public String getPetName() { return petName; }
   
    public void setPetName(String petName) {
        if (petName == null || petName.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be null or empty");
        }
        this.petName = petName;
    }
   
    public int getAge() { return age; }
   
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }
   
    public int getHappiness() { return happiness; }
   
    public void setHappiness(int happiness) {
        this.happiness = validateStat(happiness);
    }
   
    public int getHealth() { return health; }
   
    public void setHealth(int health) {
        this.health = validateStat(health);
    }
   
    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
   
    // Public interface methods
    public void feedPet(String foodType) {
        if (foodType == null) return;
       
        int bonus = calculateFoodBonus(foodType);
        modifyHealth(bonus);
        modifyHappiness(bonus / 2);
        checkEvolution();
    }
   
    public void playWithPet(String gameType) {
        if (gameType == null) return;
       
        int effect = calculateGameEffect(gameType);
        modifyHappiness(effect);
        modifyHealth(-effect / 4); // Playing tires the pet slightly
        checkEvolution();
    }
   
    // Protected calculation methods
    protected int calculateFoodBonus(String foodType) {
        switch (foodType.toLowerCase()) {
            case "premium": return 15;
            case "healthy": return 10;
            case "treat": return 8;
            case "basic": return 5;
            default: return 3;
        }
    }
   
    protected int calculateGameEffect(String gameType) {
        switch (gameType.toLowerCase()) {
            case "fetch": return 12;
            case "puzzle": return 15;
            case "training": return 10;
            case "walk": return 8;
            default: return 5;
        }
    }
   
    // Private internal logic methods
    private void modifyHappiness(int change) {
        happiness = Math.max(0, Math.min(MAX_HAPPINESS, happiness + change));
    }
   
    private void modifyHealth(int change) {
        health = Math.max(0, Math.min(MAX_HEALTH, health + change));
    }
   
    private void checkEvolution() {
        String[] stages = species.getEvolutionStages();
        if (age < stages.length - 1 && happiness > 80 && health > 80) {
            age++;
        }
    }
   
    // Private helper methods
    private int validateStat(int stat) {
        if (stat < 0 || stat > 100) {
            throw new IllegalArgumentException("Stat must be between 0 and 100");
        }
        return stat;
    }
   
    private String generatePetId() {
        return "PET_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
   
    // Package-private debugging method
    String getInternalState() {
        return String.format("ID:%s, Born:%d, Stage:%s",
            petId, birthTimestamp,
            species.getEvolutionStages()[Math.min(age, species.getEvolutionStages().length-1)]);
    }
   
    // Standard Object methods
    @Override
    public String toString() {
        String stage = species.getEvolutionStages()[Math.min(age, species.getEvolutionStages().length-1)];
        return String.format("%s (%s %s) - Health:%d, Happiness:%d",
            petName, stage, species.getSpeciesName(), health, happiness);
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VirtualPet)) return false;
        VirtualPet other = (VirtualPet) obj;
        return Objects.equals(petId, other.petId);
    }
   
    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }
}
public class DragonPet {
    private final VirtualPet basePet;
    private final String dragonType;
    private final String breathWeapon;
    private int fireEnergy;
   
    public DragonPet(String name, String dragonType, String breathWeapon) {
        PetSpecies dragonSpecies = new PetSpecies("Dragon",
            new String[]{"Hatchling", "Wyvern", "Drake", "Ancient Dragon"},
            200, "Mountain Cave");
        this.basePet = new VirtualPet(name, dragonSpecies, 60, 70);
        this.dragonType = validateDragonType(dragonType);
        this.breathWeapon = validateBreathWeapon(breathWeapon);
        this.fireEnergy = 50;
    }
   
    // JavaBean methods
    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
   
    public int getFireEnergy() { return fireEnergy; }
   
    public void setFireEnergy(int fireEnergy) {
        if (fireEnergy < 0 || fireEnergy > 100) {
            throw new IllegalArgumentException("Fire energy must be between 0 and 100");
        }
        this.fireEnergy = fireEnergy;
    }
   
    // Delegate to base pet
    public String getName() { return basePet.getPetName(); }
    public void setName(String name) { basePet.setPetName(name); }
    public int getHealth() { return basePet.getHealth(); }
    public int getHappiness() { return basePet.getHappiness(); }
   
    public void feedDragon(String foodType) {
        basePet.feedPet(foodType);
        if ("meat".equals(foodType)) {
            fireEnergy = Math.min(100, fireEnergy + 10);
        }
    }
   
    public void breatheFire() {
        if (fireEnergy >= 20) {
            fireEnergy -= 20;
            basePet.setHappiness(Math.min(100, basePet.getHappiness() + 5));
        }
    }
   
    private String validateDragonType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Dragon type cannot be null or empty



