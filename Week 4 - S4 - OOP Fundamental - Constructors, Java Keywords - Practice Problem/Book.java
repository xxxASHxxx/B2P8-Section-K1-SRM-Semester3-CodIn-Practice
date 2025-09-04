public class Book {
    String title;
    String author;
    double price;
   
    // Default constructor - sets generic values
    Book() {
        title = "Unknown Title";
        author = "Unknown Author";
        price = 0.0;
    }
   
    // Parameterized constructor - sets custom values
    Book(String t, String a, double p) {
        title = t;
        author = a;
        price = p;
    }
   
    // Display method to show book details
    void show() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: Rs." + price);
        System.out.println("-------------------");
    }
   
    public static void main(String[] args) {
        // Create book1 using default constructor
        Book book1 = new Book();
       
        // Create book2 using parameterized constructor  
        Book book2 = new Book("Harry Potter", "J.K. Rowling", 350.0);
       
        // Display both books
        System.out.println("Book 1 Details:");
        book1.show();
       
        System.out.println("Book 2 Details:");
        book2.show();
    }
}

