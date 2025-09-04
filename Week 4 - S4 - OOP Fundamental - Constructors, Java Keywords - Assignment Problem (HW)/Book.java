public class Book {
    // Fields with short names
    private String title;
    private String author;
    private String isbn;
    private boolean avail;
   
    // Constructor with all details
    public Book(String title, String author, String isbn, boolean avail) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.avail = avail;
    }
   
    // Constructor with title and author
    public Book(String title, String author) {
        this(title, author, "N/A", true);
    }
   
    // Default constructor - empty book
    public Book() {
        this("Unknown Title", "Unknown Author", "N/A", true);
    }
   
    // Borrow book - sets available = false
    public void borrowBook() {
        if (avail) {
            avail = false;
            System.out.println("✓ '" + title + "' borrowed successfully!");
        } else {
            System.out.println("✗ '" + title + "' is currently not available.");
        }
    }
   
    // Return book - sets available = true
    public void returnBook() {
        if (!avail) {
            avail = true;
            System.out.println("✓ '" + title + "' returned successfully!");
        } else {
            System.out.println("✗ '" + title + "' was not borrowed.");
        }
    }
   
    // Display book information
    public void displayBookInfo() {
        System.out.println("=== BOOK DETAILS ===");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Status: " + (avail ? "Available" : "Borrowed"));
        System.out.println("====================");
        System.out.println();
    }
   
    public static void main(String[] args) {
        System.out.println("=== LIBRARY BOOK MANAGEMENT SYSTEM ===\n");
       
        // Create books using different constructors
       
        // Default constructor - empty book
        Book book1 = new Book();
        System.out.println("Book 1 created (Default):");
        book1.displayBookInfo();
       
        // Constructor with title and author
        Book book2 = new Book("Harry Potter", "J.K. Rowling");
        System.out.println("Book 2 created (Title + Author):");
        book2.displayBookInfo();
       
        // Constructor with all details
        Book book3 = new Book("The Alchemist", "Paulo Coelho", "978-0062315007", true);
        System.out.println("Book 3 created (All Details):");
        book3.displayBookInfo();
       
        // More books for testing
        Book book4 = new Book("1984", "George Orwell", "978-0451524935", false);
        System.out.println("Book 4 created (Already Borrowed):");
        book4.displayBookInfo();
       
        // Test borrowing and returning operations
        System.out.println("=== LIBRARY OPERATIONS ===\n");
       
        // Borrow available books
        book2.borrowBook();
        book3.borrowBook();
        System.out.println();
       
        // Try to borrow already borrowed book
        book2.borrowBook(); // Should show not available
        book4.borrowBook(); // Already borrowed
        System.out.println();
       
        // Return books
        book2.returnBook();
        book4.returnBook();
        System.out.println();
       
        // Try to return non-borrowed book
        book3.returnBook();
        book3.returnBook(); // Should show was not borrowed
        System.out.println();
       
        // Display final status of all books
        System.out.println("=== FINAL LIBRARY STATUS ===\n");
        book1.displayBookInfo();
        book2.displayBookInfo();
        book3.displayBookInfo();
        book4.displayBookInfo();
       
        System.out.println("Library management operations completed!");
    }
}
