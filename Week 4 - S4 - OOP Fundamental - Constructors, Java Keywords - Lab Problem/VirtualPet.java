import java.util.Random;


public class VirtualPet {
    // Fields with short names
    private final String id;
    private String name;
    private String spec;
    private int age;
    private int happy;
    private int health;
    private int stage;
   
    // Static final fields
    private static final String[] STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder", "Ghost"};
    private static int total = 0;
    private static Random rand = new Random();
   
    // Main constructor - all parameters
    public VirtualPet(String name, String spec, int age, int happy, int health, int stage) {
        this.id = genId();
        this.name = name;
        this.spec = spec;
        this.age = age;
        this.happy = happy;
        this.health = health;
        this.stage = stage;
        total++;
        System.out.println("Created: " + name + " (" + spec + ") ID: " + id);
    }
   
    // Constructor with name and species - child stage
    public VirtualPet(String name, String spec) {
        this(name, spec, 3, 70, 70, 2);
    }
   
    // Constructor with name only - baby stage
    public VirtualPet(String name) {
        this(name, randSpec(), 1, 60, 60, 1);
    }
   
    // Default constructor - mysterious egg with random species
    public VirtualPet() {
        this("Mystery Egg", randSpec(), 0, 50, 50, 0);
    }
   
    // Static method to generate unique pet IDs
    public static String genId() {
        return "PET-" + (total + 1) + "-" + rand.nextInt(1000);
    }
   
    // Random species helper method
    private static String randSpec() {
        String[] specs = {"Dragon", "Phoenix", "Unicorn", "Griffin"};
        return specs[rand.nextInt(specs.length)];
    }
   
    // Evolve pet based on age and care
    public void evolve() {
        if (health <= 0) {
            stage = 6; // Ghost stage
            System.out.println(name + " became a Ghost! Can haunt other pets.");
            return;
        }
       
        int newStage = stage;
        if (age >= 15) newStage = 5;        // Elder
        else if (age >= 10) newStage = 4;   // Adult  
        else if (age >= 6) newStage = 3;    // Teen
        else if (age >= 3) newStage = 2;    // Child
        else if (age >= 1) newStage = 1;    // Baby
        else newStage = 0;                  // Egg
       
        if (newStage != stage) {
            stage = newStage;
            System.out.println(name + " evolved to: " + STAGES[stage] + "!");
        }
    }
   
    // Feed pet - increases happiness and health
    public void feed() {
        if (health <= 0) {
            System.out.println(name + " is a Ghost and can't be fed!");
            return;
        }
        happy += 10;
        if (happy > 100) happy = 100;
        health += 5;
        if (health > 100) health = 100;
        System.out.println(name + " fed! Happy: " + happy + ", Health: " + health);
    }
   
    // Play with pet - increases happiness, decreases health slightly
    public void play() {
        if (health <= 0) {
            System.out.println(name + " is a Ghost and can't play!");
            return;
        }
        happy += 15;
        if (happy > 100) happy = 100;
        health -= 5;
        if (health < 0) health = 0;
        System.out.println(name + " played! Happy: " + happy + ", Health: " + health);
    }
   
    // Heal pet - increases health
    public void heal() {
        if (health <= 0) {
            System.out.println(name + " is a Ghost and can't be healed!");
            return;
        }
        health += 20;
        if (health > 100) health = 100;
        System.out.println(name + " healed! Health: " + health);
    }
   
    // Simulate a day - ages pet and random stat changes
    public void simDay() {
        if (health <= 0) {
            System.out.println(name + " (Ghost) haunts the daycare...");
            return;
        }
       
        age++;
        // Random happiness change (-5 to +5)
        happy += rand.nextInt(11) - 5;
        if (happy > 100) happy = 100;
        if (happy < 0) happy = 0;
       
        // Random health change (-3 to +3)
        health += rand.nextInt(7) - 3;
        if (health > 100) health = 100;
        if (health < 0) health = 0;
       
        System.out.println("Day passed for " + name + " - Age: " + age +
                          ", Happy: " + happy + ", Health: " + health);
        evolve();
    }
   
    // Get current evolution stage
    public String getStatus() {
        return STAGES[stage];
    }
   
    // Get total pets created
    public static int getTotal() {
        return total;
    }
   
    public static void main(String[] args) {
        System.out.println("=== VIRTUAL PET DAYCARE SIMULATOR ===\n");
       
        // Create pet daycare with different constructors
        VirtualPet pet1 = new VirtualPet();                    // Default - Egg
        VirtualPet pet2 = new VirtualPet("Fluffy");            // Name only - Baby
        VirtualPet pet3 = new VirtualPet("Sparky", "Dragon");  // Name + Species - Child
        VirtualPet pet4 = new VirtualPet("Luna", "Phoenix", 8, 80, 80, 3); // Full constructor - Teen
       
        VirtualPet[] daycare = {pet1, pet2, pet3, pet4};
       
        System.out.println("\nStarting simulation with " + getTotal() + " pets...\n");
       
        // Simulate 10 days
        for (int day = 1; day <= 10; day++) {
            System.out.println("=== DAY " + day + " ===");
           
            for (VirtualPet pet : daycare) {
                pet.simDay();
               
                if (pet.health > 0) {
                    // Random care actions
                    if (rand.nextBoolean()) pet.feed();
                    if (rand.nextBoolean()) pet.play();
                    if (pet.health < 50 && rand.nextBoolean()) pet.heal();
                }
            }
            System.out.println();
        }
       
        // Final status report
        System.out.println("=== DAYCARE FINAL REPORT ===");
        for (VirtualPet pet : daycare) {
            System.out.println(pet.name + " (" + pet.spec + "):");
            System.out.println("  Stage: " + pet.getStatus());
            System.out.println("  Age: " + pet.age + " days");
            System.out.println("  Health: " + pet.health + "/100");
            System.out.println("  Happiness: " + pet.happy + "/100");
            System.out.println("  ID: " + pet.id);
            System.out.println();
        }
       
        System.out.println("Total pets created: " + getTotal());
    }
}
