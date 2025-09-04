public class MovieTicket {
    // Fields with short names
    private String movie;
    private String theatre;
    private int seat;
    private double price;
   
    // Full constructor - sets all details
    public MovieTicket(String movie, String theatre, int seat, double price) {
        this.movie = movie;
        this.theatre = theatre;
        this.seat = seat;
        this.price = price;
    }
   
    // Constructor with movie name and seat number - default theatre "PVR"
    public MovieTicket(String movie, int seat) {
        this(movie, "PVR", seat, 200.0);
    }
   
    // Constructor with movie name - default price = 200
    public MovieTicket(String movie) {
        this(movie, "PVR", 1, 200.0);
    }
   
    // Default constructor - assigns "Unknown" movie
    public MovieTicket() {
        this("Unknown", "PVR", 1, 200.0);
    }
   
    // Method to print ticket details
    public void printTicket() {
        System.out.println("=== MOVIE TICKET ===");
        System.out.println("Movie: " + movie);
        System.out.println("Theatre: " + theatre);
        System.out.println("Seat Number: " + seat);
        System.out.println("Price: Rs." + price);
        System.out.println("====================");
        System.out.println();
    }
   
    public static void main(String[] args) {
        System.out.println("=== MOVIE TICKET BOOKING SYSTEM ===\n");
       
        // Create tickets using different constructors
       
        // Default constructor
        MovieTicket t1 = new MovieTicket();
        System.out.println("Ticket 1 (Default):");
        t1.printTicket();
       
        // Constructor with movie name only
        MovieTicket t2 = new MovieTicket("Avengers Endgame");
        System.out.println("Ticket 2 (Movie Name Only):");
        t2.printTicket();
       
        // Constructor with movie name and seat number
        MovieTicket t3 = new MovieTicket("Spider-Man", 15);
        System.out.println("Ticket 3 (Movie + Seat):");
        t3.printTicket();
       
        // Full constructor
        MovieTicket t4 = new MovieTicket("The Dark Knight", "INOX", 25, 350.0);
        System.out.println("Ticket 4 (Full Details):");
        t4.printTicket();
       
        // More examples with full constructor
        MovieTicket t5 = new MovieTicket("Inception", "Cinepolis", 8, 280.0);
        System.out.println("Ticket 5 (Premium Booking):");
        t5.printTicket();
       
        System.out.println("Total tickets booked: 5");
        System.out.println("Thank you for using our booking system!");
    }
}
