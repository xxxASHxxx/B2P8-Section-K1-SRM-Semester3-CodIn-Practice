public class GameController {
    // Instance variables for controller configuration
    private String brnd;
    private String conn;
    private boolean vib;
    private int bat;
    private double sen;
   
    // Default constructor - creates standard gaming setup
    public GameController() {
        brnd = "GenericPad";
        conn = "USB";
        vib = true;
        bat = 100;
        sen = 1.0;
    }
   
    // Parameterized constructor for custom configuration
    public GameController(String b, String c, boolean v, int bt, double s) {
        brnd = b;
        conn = c;
        vib = v;
       
        // Validate battery level (0-100)
        if (bt < 0 || bt > 100) {
            bat = 100;  // Default to full battery
        } else {
            bat = bt;
        }
       
        // Validate sensitivity (0.1-3.0)
        if (s < 0.1 || s > 3.0) {
            sen = 1.0;  // Default sensitivity
        } else {
            sen = s;
        }
    }
   
    // Two-parameter convenience constructor
    public GameController(String b, String c) {
        brnd = b;
        conn = c;
        vib = true;     // Default vibration on
        bat = 100;      // Default full battery
        sen = 1.0;      // Default sensitivity
    }
   
    // Methods to test functionality
    public void calibrateController() {
        System.out.println("Calibrating " + brnd + " controller...");
    }
   
    public void displayConfiguration() {
        System.out.println("Brand: " + brnd);
        System.out.println("Connection: " + conn);
        System.out.println("Vibration: " + (vib ? "Enabled" : "Disabled"));
        System.out.println("Battery: " + bat + "%");
        System.out.println("Sensitivity: " + sen + "x");
        System.out.println("------------------------");
    }
   
    public void testVibration() {
        if (vib) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }
   
    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===\n");
       
        // Create controller with default constructor
        GameController c1 = new GameController();
        System.out.println("Controller 1 (Default Setup):");
        c1.displayConfiguration();
        c1.calibrateController();
        c1.testVibration();
       
        System.out.println();
       
        // Create controller with full parameterized constructor
        GameController c2 = new GameController("Xbox Elite", "Wireless",
                                              true, 85, 2.5);
        System.out.println("Controller 2 (Custom Setup):");
        c2.displayConfiguration();
        c2.calibrateController();
        c2.testVibration();
       
        System.out.println();
       
        // Create controller with convenience constructor
        GameController c3 = new GameController("PS5 DualSense", "Bluetooth");
        System.out.println("Controller 3 (Brand + Connection):");
        c3.displayConfiguration();
        c3.calibrateController();
        c3.testVibration();
       
        System.out.println("\n=== CONFIGURATION COMPARISON ===");
        System.out.println("All controllers configured successfully!");
    }
}


