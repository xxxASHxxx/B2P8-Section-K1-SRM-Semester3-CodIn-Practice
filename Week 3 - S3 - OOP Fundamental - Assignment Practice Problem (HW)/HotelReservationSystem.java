import java.util.Scanner;


public class HotelReservationSystem {
   
    static class Room {
        // Instance attributes
        private String roomNumber;
        private String roomType;
        private double pricePerNight;
        private boolean isAvailable;
        private int maxOccupancy;
       
        // Constructor
        public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.maxOccupancy = maxOccupancy;
            this.isAvailable = true;
        }
       
        // Method to set room availability
        public void setAvailability(boolean available) {
            this.isAvailable = available;
        }
       
        // Method to display room information
        public void displayRoom() {
            String status = isAvailable ? "Available" : "Occupied";
            System.out.println("Room " + roomNumber + " | " + roomType +
                             " | $" + String.format("%.2f", pricePerNight) + "/night" +
                             " | Max: " + maxOccupancy + " guests | " + status);
        }
       
        // Getter methods
        public String getRoomNumber() { return roomNumber; }
        public String getRoomType() { return roomType; }
        public double getPricePerNight() { return pricePerNight; }
        public boolean isAvailable() { return isAvailable; }
        public int getMaxOccupancy() { return maxOccupancy; }
    }
   
    static class Guest {
        // Instance attributes
        private String guestId;
        private String guestName;
        private String phoneNumber;
        private String email;
        private String[] bookingHistory;
        private int historyCount;
        private static final int MAX_HISTORY = 20;
       
        // Constructor
        public Guest(String guestId, String guestName, String phoneNumber, String email) {
            this.guestId = guestId;
            this.guestName = guestName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.bookingHistory = new String[MAX_HISTORY];
            this.historyCount = 0;
        }
       
        // Method to add booking to history
        public void addBookingToHistory(String bookingId) {
            if (historyCount < MAX_HISTORY) {
                bookingHistory[historyCount] = bookingId;
                historyCount++;
            }
        }
       
        // Method to display guest information
        public void displayGuest() {
            System.out.println("Guest ID: " + guestId);
            System.out.println("Name: " + guestName);
            System.out.println("Phone: " + phoneNumber);
            System.out.println("Email: " + email);
            System.out.println("Total Bookings: " + historyCount);
        }
       
        // Method to display booking history
        public void displayBookingHistory() {
            System.out.println("\nBooking History for " + guestName + ":");
            if (historyCount == 0) {
                System.out.println("No previous bookings found.");
            } else {
                for (int i = 0; i < historyCount; i++) {
                    System.out.println((i + 1) + ". Booking ID: " + bookingHistory[i]);
                }
            }
        }
       
        // Getter methods
        public String getGuestId() { return guestId; }
        public String getGuestName() { return guestName; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getEmail() { return email; }
        public int getHistoryCount() { return historyCount; }
    }
   
    static class Booking {
        // Instance attributes
        private String bookingId;
        private Guest guest;
        private Room room;
        private String checkInDate;
        private String checkOutDate;
        private double totalAmount;
        private int numberOfNights;
        private boolean isActive;
       
        // Static variables
        private static int totalBookings = 0;
        private static double hotelRevenue = 0.0;
        private static String hotelName = "Grand Palace Hotel";
        private static int[] roomTypeBookings = new int[4]; // Standard, Deluxe, Suite, Presidential
       
        // Constructor
        public Booking(Guest guest, Room room, String checkInDate, String checkOutDate, int numberOfNights) {
            this.bookingId = generateBookingId();
            this.guest = guest;
            this.room = room;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.numberOfNights = numberOfNights;
            this.totalAmount = calculateBill();
            this.isActive = true;
           
            totalBookings++;
            hotelRevenue += totalAmount;
            updateRoomTypeBookings(room.getRoomType());
           
            // Add booking to guest history
            guest.addBookingToHistory(bookingId);
        }
       
        // Method to generate unique booking ID
        private String generateBookingId() {
            return "BK" + String.format("%04d", totalBookings + 1);
        }
       
        // Method to calculate total bill
        public double calculateBill() {
            return room.getPricePerNight() * numberOfNights;
        }
       
        // Method to update room type booking statistics
        private void updateRoomTypeBookings(String roomType) {
            switch (roomType.toLowerCase()) {
                case "standard":
                    roomTypeBookings[0]++;
                    break;
                case "deluxe":
                    roomTypeBookings[1]++;
                    break;
                case "suite":
                    roomTypeBookings[2]++;
                    break;
                case "presidential":
                    roomTypeBookings[3]++;
                    break;
            }
        }
       
        // Method to display booking details
        public void displayBooking() {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("             BOOKING CONFIRMATION");
            System.out.println("=".repeat(50));
            System.out.println("Hotel: " + hotelName);
            System.out.println("Booking ID: " + bookingId);
            System.out.println("Guest: " + guest.getGuestName());
            System.out.println("Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
            System.out.println("Check-in: " + checkInDate);
            System.out.println("Check-out: " + checkOutDate);
            System.out.println("Number of Nights: " + numberOfNights);
            System.out.println("Price per Night: $" + String.format("%.2f", room.getPricePerNight()));
            System.out.println("Total Amount: $" + String.format("%.2f", totalAmount));
            System.out.println("Status: " + (isActive ? "Active" : "Cancelled"));
            System.out.println("=".repeat(50));
        }
       
        // Method to cancel booking
        public void cancelBooking() {
            if (isActive) {
                this.isActive = false;
                room.setAvailability(true);
                hotelRevenue -= totalAmount;
                System.out.println("SUCCESS: Booking " + bookingId + " has been cancelled.");
                System.out.println("Room " + room.getRoomNumber() + " is now available.");
            } else {
                System.out.println("ERROR: Booking is already cancelled.");
            }
        }
       
        // Static method to set hotel name
        public static void setHotelName(String name) {
            hotelName = name;
        }
       
        // Static method to get total bookings
        public static int getTotalBookings() {
            return totalBookings;
        }
       
        // Static method to get total revenue
        public static double getTotalRevenue() {
            return hotelRevenue;
        }
       
        // Static method to get hotel name
        public static String getHotelName() {
            return hotelName;
        }
       
        // Static method to get most popular room type
        public static String getMostPopularRoomType() {
            String[] roomTypes = {"Standard", "Deluxe", "Suite", "Presidential"};
            int maxBookings = 0;
            String mostPopular = "None";
           
            for (int i = 0; i < roomTypeBookings.length; i++) {
                if (roomTypeBookings[i] > maxBookings) {
                    maxBookings = roomTypeBookings[i];
                    mostPopular = roomTypes[i];
                }
            }
           
            return mostPopular + " (" + maxBookings + " bookings)";
        }
       
        // Getter methods
        public String getBookingId() { return bookingId; }
        public Guest getGuest() { return guest; }
        public Room getRoom() { return room; }
        public boolean isActive() { return isActive; }
        public double getTotalAmount() { return totalAmount; }
    }
   
    // Hotel Management Class
    static class HotelManager {
        private Room[] rooms;
        private Guest[] guests;
        private Booking[] bookings;
        private int roomCount;
        private int guestCount;
        private int bookingCount;
        private static final int MAX_ROOMS = 50;
        private static final int MAX_GUESTS = 200;
        private static final int MAX_BOOKINGS = 500;
       
        // Constructor
        public HotelManager() {
            this.rooms = new Room[MAX_ROOMS];
            this.guests = new Guest[MAX_GUESTS];
            this.bookings = new Booking[MAX_BOOKINGS];
            this.roomCount = 0;
            this.guestCount = 0;
            this.bookingCount = 0;
            initializeRooms();
        }
       
        // Method to initialize sample rooms
        private void initializeRooms() {
            // Standard Rooms
            addRoom(new Room("101", "Standard", 89.99, 2));
            addRoom(new Room("102", "Standard", 89.99, 2));
            addRoom(new Room("103", "Standard", 89.99, 2));
           
            // Deluxe Rooms
            addRoom(new Room("201", "Deluxe", 129.99, 3));
            addRoom(new Room("202", "Deluxe", 129.99, 3));
            addRoom(new Room("203", "Deluxe", 129.99, 3));
           
            // Suite Rooms
            addRoom(new Room("301", "Suite", 199.99, 4));
            addRoom(new Room("302", "Suite", 199.99, 4));
           
            // Presidential Suite
            addRoom(new Room("401", "Presidential", 349.99, 6));
            addRoom(new Room("402", "Presidential", 349.99, 6));
        }
       
        // Method to add room
        public void addRoom(Room room) {
            if (roomCount < MAX_ROOMS) {
                rooms[roomCount] = room;
                roomCount++;
            }
        }
       
        // Method to add guest
        public void addGuest(Guest guest) {
            if (guestCount < MAX_GUESTS) {
                guests[guestCount] = guest;
                guestCount++;
            }
        }
       
        // Method to find guest by ID
        public Guest findGuestById(String guestId) {
            for (int i = 0; i < guestCount; i++) {
                if (guests[i].getGuestId().equals(guestId)) {
                    return guests[i];
                }
            }
            return null;
        }
       
        // Method to find room by number
        public Room findRoomByNumber(String roomNumber) {
            for (int i = 0; i < roomCount; i++) {
                if (rooms[i].getRoomNumber().equals(roomNumber)) {
                    return rooms[i];
                }
            }
            return null;
        }
       
        // Method to find booking by ID
        public Booking findBookingById(String bookingId) {
            for (int i = 0; i < bookingCount; i++) {
                if (bookings[i].getBookingId().equals(bookingId)) {
                    return bookings[i];
                }
            }
            return null;
        }
       
        // Method to check availability by room type
        public void checkAvailability(String roomType) {
            System.out.println("\nAvailable " + roomType + " Rooms:");
            System.out.println("-".repeat(60));
            boolean found = false;
           
            for (int i = 0; i < roomCount; i++) {
                if (rooms[i].getRoomType().equalsIgnoreCase(roomType) && rooms[i].isAvailable()) {
                    rooms[i].displayRoom();
                    found = true;
                }
            }
           
            if (!found) {
                System.out.println("No available rooms of type: " + roomType);
            }
        }
       
        // Method to display all available rooms
        public void displayAvailableRooms() {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                AVAILABLE ROOMS");
            System.out.println("=".repeat(60));
           
            boolean hasAvailable = false;
            for (int i = 0; i < roomCount; i++) {
                if (rooms[i].isAvailable()) {
                    rooms[i].displayRoom();
                    hasAvailable = true;
                }
            }
           
            if (!hasAvailable) {
                System.out.println("No rooms currently available.");
            }
            System.out.println("=".repeat(60));
        }
       
        // Method to make reservation
        public Booking makeReservation(String guestId, String roomNumber, String checkIn, String checkOut, int nights) {
            Guest guest = findGuestById(guestId);
            Room room = findRoomByNumber(roomNumber);
           
            if (guest == null) {
                System.out.println("ERROR: Guest not found.");
                return null;
            }
           
            if (room == null) {
                System.out.println("ERROR: Room not found.");
                return null;
            }
           
            if (!room.isAvailable()) {
                System.out.println("ERROR: Room is not available.");
                return null;
            }
           
            if (nights <= 0) {
                System.out.println("ERROR: Number of nights must be positive.");
                return null;
            }
           
            // Create booking
            Booking booking = new Booking(guest, room, checkIn, checkOut, nights);
            room.setAvailability(false);
           
            if (bookingCount < MAX_BOOKINGS) {
                bookings[bookingCount] = booking;
                bookingCount++;
            }
           
            System.out.println("SUCCESS: Reservation confirmed!");
            return booking;
        }
       
        // Method to cancel reservation
        public void cancelReservation(String bookingId) {
            Booking booking = findBookingById(bookingId);
            if (booking != null) {
                booking.cancelBooking();
            } else {
                System.out.println("ERROR: Booking not found.");
            }
        }
       
        // Method to get occupancy rate
        public double getOccupancyRate() {
            int occupiedRooms = 0;
            for (int i = 0; i < roomCount; i++) {
                if (!rooms[i].isAvailable()) {
                    occupiedRooms++;
                }
            }
            return roomCount > 0 ? (double) occupiedRooms / roomCount * 100 : 0;
        }
       
        // Method to display hotel statistics
        public void displayHotelStatistics() {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("              HOTEL STATISTICS");
            System.out.println("=".repeat(50));
            System.out.println("Hotel Name: " + Booking.getHotelName());
            System.out.println("Total Rooms: " + roomCount);
            System.out.println("Total Guests: " + guestCount);
            System.out.println("Total Bookings: " + Booking.getTotalBookings());
            System.out.println("Occupancy Rate: " + String.format("%.1f", getOccupancyRate()) + "%");
            System.out.println("Total Revenue: $" + String.format("%.2f", Booking.getTotalRevenue()));
            System.out.println("Most Popular Room Type: " + Booking.getMostPopularRoomType());
            System.out.println("=".repeat(50));
        }
       
        // Method to display all bookings
        public void displayAllBookings() {
            System.out.println("\nALL BOOKINGS:");
            System.out.println("=".repeat(60));
           
            if (bookingCount == 0) {
                System.out.println("No bookings found.");
            } else {
                for (int i = 0; i < bookingCount; i++) {
                    System.out.println("Booking " + (i + 1) + ":");
                    bookings[i].displayBooking();
                }
            }
        }
    }
   
    // Main method with menu-driven interface
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelManager hotel = new HotelManager();
       
        System.out.println("WELCOME TO " + Booking.getHotelName().toUpperCase());
        System.out.println("=".repeat(60));
       
        while (true) {
            System.out.println("\nHOTEL MANAGEMENT SYSTEM - MAIN MENU");
            System.out.println("1. Guest Registration");
            System.out.println("2. View Available Rooms");
            System.out.println("3. Check Room Availability by Type");
            System.out.println("4. Make Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. View Guest Information");
            System.out.println("7. View All Bookings");
            System.out.println("8. Hotel Statistics");
            System.out.println("9. Change Hotel Name");
            System.out.println("10. Exit");
            System.out.print("Choose an option (1-10): ");
           
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
           
            switch (choice) {
                case 1:
                    System.out.print("Enter Guest ID: ");
                    String guestId = scanner.nextLine();
                    System.out.print("Enter Guest Name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                   
                    Guest newGuest = new Guest(guestId, guestName, phone, email);
                    hotel.addGuest(newGuest);
                    System.out.println("SUCCESS: Guest registered successfully!");
                    newGuest.displayGuest();
                    break;
               
                case 2:
                    hotel.displayAvailableRooms();
                    break;
               
                case 3:
                    System.out.print("Enter room type (Standard/Deluxe/Suite/Presidential): ");
                    String roomType = scanner.nextLine();
                    hotel.checkAvailability(roomType);
                    break;
               
                case 4:
                    System.out.print("Enter Guest ID: ");
                    String resGuestId = scanner.nextLine();
                    System.out.print("Enter Room Number: ");
                    String roomNumber = scanner.nextLine();
                    System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
                    String checkIn = scanner.nextLine();
                    System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
                    String checkOut = scanner.nextLine();
                    System.out.print("Enter Number of Nights: ");
                    int nights = scanner.nextInt();
                    scanner.nextLine();
                   
                    Booking booking = hotel.makeReservation(resGuestId, roomNumber, checkIn, checkOut, nights);
                    if (booking != null) {
                        booking.displayBooking();
                    }
                    break;
               
                case 5:
                    System.out.print("Enter Booking ID to cancel: ");
                    String cancelId = scanner.nextLine();
                    hotel.cancelReservation(cancelId);
                    break;
               
                case 6:
                    System.out.print("Enter Guest ID: ");
                    String searchGuestId = scanner.nextLine();
                    Guest foundGuest = hotel.findGuestById(searchGuestId);
                    if (foundGuest != null) {
                        foundGuest.displayGuest();
                        foundGuest.displayBookingHistory();
                    } else {
                        System.out.println("Guest not found.");
                    }
                    break;
               
                case 7:
                    hotel.displayAllBookings();
                    break;
               
                case 8:
                    hotel.displayHotelStatistics();
                    break;
               
                case 9:
                    System.out.print("Enter new hotel name: ");
                    String newHotelName = scanner.nextLine();
                    Booking.setHotelName(newHotelName);
                    System.out.println("Hotel name updated to: " + newHotelName);
                    break;
               
                case 10:
                    System.out.println("Thank you for using " + Booking.getHotelName() + " Management System!");
                    scanner.close();
                    return;
               
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


