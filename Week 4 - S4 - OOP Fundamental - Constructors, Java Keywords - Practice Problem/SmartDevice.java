public class SmartDevice {
    private String name;
    private String loc;
    private boolean online;
    private double power;
    private String[] conn;
    private int count;
   
    // Constructor with parameter names matching field names
    public SmartDevice(String name, String loc, boolean online, double power) {
        // Use this keyword to distinguish between parameters and fields
        this.name = name;
        this.loc = loc;
        this.online = online;
        this.power = power;
        // Initialize connectedDevices array (size 5)
        this.conn = new String[5];
        // Set connectionCount to 0
        this.count = 0;
    }
   
    // Method using this for parameter disambiguation
    public void updateLocation(String loc) {
        // Use this.loc to assign parameter value
        this.loc = loc;
        System.out.println(this.name + " moved to " + this.loc);
    }
   
    public void updatePowerConsumption(double power) {
        // Use this keyword when parameter name matches field
        this.power = power;
        System.out.println("Power consumption updated for " + this.name);
    }
   
    // Method returning this for chaining
    public SmartDevice setOnline(boolean online) {
        // Use this keyword and return this for method chaining
        this.online = online;
        return this;
    }
   
    public SmartDevice connectToDevice(String name) {
        // Add device to connectedDevices array
        if (this.count < this.conn.length) {
            this.conn[this.count] = name;
            this.count++;
            System.out.println(this.name + " connected to " + name);
        }
        return this; // Enable method chaining
    }
   
    public SmartDevice rename(String name) {
        // Use this keyword for disambiguation
        String oldName = this.name;
        this.name = name;
        System.out.println("Device renamed from " + oldName + " to " + this.name);
        return this;
    }
   
    public void displayDeviceInfo() {
        // Use this keyword to access instance variables
        System.out.println("\n=== " + this.name + " INFO ===");
        System.out.println("Location: " + this.loc);
        System.out.println("Status: " + (this.online ? "Online" : "Offline"));
        System.out.println("Power: " + this.power + "W");
        System.out.println("Connections: " + this.count);
       
        for (int i = 0; i < this.count; i++) {
            System.out.println(" -> " + this.conn[i]);
        }
    }
   
    // Method that calls other methods using this
    public void performInitialSetup() {
        // Use this to call other methods
        this.setOnline(true);
        System.out.println(this.name + " initial setup completed");
    }
   
    public static void main(String[] args) {
        System.out.println("=== SMART HOME DEVICE NETWORK ===");
       
        // Create devices with parameter names matching field names
        SmartDevice hub = new SmartDevice("Living Room Hub", "Living Room", false, 15.5);
        SmartDevice light = new SmartDevice("Smart Light", "Bedroom", true, 8.0);
       
        // Test method chaining using returned this
        System.out.println("\n--- Method Chaining Demo ---");
        hub.setOnline(true).connectToDevice("Alexa").rename("Kitchen Hub");
       
        // Demonstrate this keyword in various contexts
        System.out.println("\n--- Parameter Disambiguation ---");
        hub.updateLocation("Kitchen");
        hub.updatePowerConsumption(17.2);
       
        // Show parameter disambiguation scenarios
        light.setOnline(false).connectToDevice("Motion Sensor");
        light.updateLocation("Master Bedroom");
        light.updatePowerConsumption(12.5);
       
        // Method calling other methods using this
        System.out.println("\n--- Initial Setup ---");
        hub.performInitialSetup();
        light.performInitialSetup();
       
        // Display device information
        hub.displayDeviceInfo();
        light.displayDeviceInfo();
       
        // Advanced method chaining example
        System.out.println("\n--- Complex Chaining ---");
        SmartDevice garage = new SmartDevice("Garage Door", "Garage", false, 25.0);
        garage.setOnline(true)
              .connectToDevice("Security Camera")
              .connectToDevice("Motion Detector")
              .rename("Smart Garage Controller");
       
        garage.displayDeviceInfo();
    }
}


