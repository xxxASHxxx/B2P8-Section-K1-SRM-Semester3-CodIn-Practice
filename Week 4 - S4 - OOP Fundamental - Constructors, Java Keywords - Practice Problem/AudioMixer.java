public class AudioMixer {
    private String mod;
    private int chn;
    private boolean bt;
    private double vol;
    private String[] dev;
    private int cnt;
   
    // No-argument constructor using this() chaining
    public AudioMixer() {
        // Call three-parameter constructor with defaults
        this("StandardMix-8", 8, false);
    }
   
    // Two-parameter constructor using this() chaining
    public AudioMixer(String m, int c) {
        // Call three-parameter constructor with bluetooth disabled
        this(m, c, false);
    }
   
    // Three-parameter constructor using this() chaining
    public AudioMixer(String m, int c, boolean b) {
        // Call main constructor with default max volume (120.0)
        this(m, c, b, 120.0);
    }
   
    // Main constructor - all parameters
    public AudioMixer(String m, int c, boolean b, double v) {
        // Initialize all fields
        this.mod = m;
        this.chn = c;
        this.bt = b;
        this.vol = v;
       
        // Initialize devices array based on channels
       this.dev = new String[c];
  this.cnt = 0;
       
        // Print constructor execution message
        System.out.println("Creating " + m + " with " + c + " channels");
    }
   
    public void connectDevice(String deviceName) {
        if (cnt < dev.length) {
            dev[cnt] = deviceName;
            cnt++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }
   
    public void displayMixerStatus() {
        System.out.println("\n=== " + mod + " STATUS ===");
        System.out.println("Channels: " + chn);
        System.out.println("Bluetooth: " + (bt ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + vol + " dB");
        System.out.println("Connected Devices: " + cnt + "/" + chn);
       
        for (int i = 0; i < cnt; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + dev[i]);
        }
    }
   
    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===\n");
       
        // Create mixer using no-argument constructor
        AudioMixer m1 = new AudioMixer();
        m1.connectDevice("Piano");
        m1.connectDevice("Guitar");
       
        // Create mixer using two-parameter constructor
        AudioMixer m2 = new AudioMixer("ProMix-16", 16);
        m2.connectDevice("Microphone");
        m2.connectDevice("Drum Kit");
        m2.connectDevice("Bass Guitar");
       
        // Create mixer using three-parameter constructor
        AudioMixer m3 = new AudioMixer("StudioMax-24", 24, true);
        m3.connectDevice("Synthesizer");
        m3.connectDevice("Violin");
       
        // Create mixer using full constructor
        AudioMixer m4 = new AudioMixer("ProfessionalMix-32", 32, true, 140.0);
        m4.connectDevice("Orchestra");
        m4.connectDevice("Backup Vocals");
        m4.connectDevice("Sound Effects");
       
        // Display status of all mixers
        m1.displayMixerStatus();
        m2.displayMixerStatus();
        m3.displayMixerStatus();
        m4.displayMixerStatus();
       
        System.out.println("\n=== CONSTRUCTOR CHAINING NOTES ===");
        System.out.println("Each constructor calls the next one in chain:");
        System.out.println("No-args -> 3-param -> Main constructor");
        System.out.println("2-param -> 3-param -> Main constructor");
        System.out.println("Only main constructor does actual initialization!");
    }
}


