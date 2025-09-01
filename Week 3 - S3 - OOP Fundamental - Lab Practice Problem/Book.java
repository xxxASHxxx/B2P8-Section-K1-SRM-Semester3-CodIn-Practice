public class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
   
    public Book(String title, String author) {
        this.bookId = generateBookId();
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }
   
    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            System.out.println("Book '" + title + "' has been issued.");
            return true;
        } else {
            System.out.println("Book '" + title + "' is currently not available.");
            return false;
        }
    }
   
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            System.out.println("Book '" + title + "' has been returned.");
        } else {
            System.out.println("Book '" + title + "' was not issued.");
        }
    }
   
    public void displayBookInfo() {
        String status = isAvailable ? "Available" : "Issued";
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + status);
        System.out.println("-------------------");
    }
   
    public String getBookId() {
        return bookId;
    }
   
    public String getTitle() {
        return title;
    }
   
    public boolean isAvailable() {
        return isAvailable;
    }
   
    private static String generateBookId() {
        return "BOOK" + String.format("%03d", totalBooks + 1);
    }
   
    public static int getTotalBooks() {
        return totalBooks;
    }
   
    public static int getAvailableBooks() {
        return availableBooks;
    }
}


class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int totalMembers = 0;
    private static final int MAX_BOOKS = 3;
   
    public Member(String memberName) {
        this.memberId = generateMemberId();
        this.memberName = memberName;
        this.booksIssued = new String[MAX_BOOKS];
        this.bookCount = 0;
        totalMembers++;
        System.out.println("New member registered: " + memberName + " (ID: " + memberId + ")");
    }
   
    public boolean borrowBook(Book book) {
        if (bookCount >= MAX_BOOKS) {
            System.out.println(memberName + " has reached the maximum limit of " + MAX_BOOKS + " books.");
            return false;
        }
       
        if (book.isAvailable() && book.issueBook()) {
            booksIssued[bookCount] = book.getBookId();
            bookCount++;
            System.out.println(memberName + " successfully borrowed '" + book.getTitle() + "'");
            return true;
        }
        return false;
    }
   
    public boolean returnBook(String bookId, Book[] books) {
        int bookIndex = -1;
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                bookIndex = i;
                break;
            }
        }
       
        if (bookIndex == -1) {
            System.out.println(memberName + " does not have book ID: " + bookId);
            return false;
        }
       
        for (Book book : books) {
            if (book != null && book.getBookId().equals(bookId)) {
                book.returnBook();
               
                for (int i = bookIndex; i < bookCount - 1; i++) {
                    booksIssued[i] = booksIssued[i + 1];
                }
                booksIssued[bookCount - 1] = null;
                bookCount--;
               
                System.out.println(memberName + " successfully returned '" + book.getTitle() + "'");
                return true;
            }
        }
        return false;
    }
   
    public void displayMemberInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Member Name: " + memberName);
        System.out.println("Books Issued: " + bookCount);
        if (bookCount > 0) {
            System.out.print("Book IDs: ");
            for (int i = 0; i < bookCount; i++) {
                System.out.print(booksIssued[i]);
                if (i < bookCount - 1) System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }
   
    public String getMemberId() {
        return memberId;
    }
   
    public String getMemberName() {
        return memberName;
    }
   
    private static String generateMemberId() {
        return "MEM" + String.format("%03d", totalMembers + 1);
    }
   
    public static int getTotalMembers() {
        return totalMembers;
    }
   
    public static void main(String[] args) {
        Book[] library = new Book[6];
        library[0] = new Book("To Kill a Mockingbird", "Harper Lee");
        library[1] = new Book("1984", "George Orwell");
        library[2] = new Book("Pride and Prejudice", "Jane Austen");
        library[3] = new Book("The Catcher in the Rye", "J.D. Salinger");
        library[4] = new Book("Lord of the Flies", "William Golding");
        library[5] = new Book("Animal Farm", "George Orwell");
       
        Member[] members = new Member[3];
        members[0] = new Member("Sarah Johnson");
        members[1] = new Member("Michael Chen");
        members[2] = new Member("Emma Rodriguez");
       
        System.out.println("\n=== Library Statistics ===");
        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
        System.out.println("Total Members: " + Member.getTotalMembers());
       
        System.out.println("\n=== Book Borrowing Demonstration ===");
        members[0].borrowBook(library[0]);
        members[0].borrowBook(library[1]);
        members[1].borrowBook(library[2]);
        members[1].borrowBook(library[0]);
        members[2].borrowBook(library[3]);
       
        System.out.println("\n=== Updated Library Statistics ===");
        System.out.println("Available Books: " + Book.getAvailableBooks());
       
        System.out.println("\n=== Member Information ===");
        for (Member member : members) {
            member.displayMemberInfo();
        }
       
        System.out.println("\n=== Book Return Demonstration ===");
        members[0].returnBook("BOOK001", library);
        members[1].returnBook("BOOK003", library);
        members[2].returnBook("BOOK999", library);
       
        System.out.println("\n=== Attempting to Exceed Book Limit ===");
        members[0].borrowBook(library[0]);
        members[0].borrowBook(library[4]);
        members[0].borrowBook(library[5]);
        members[0].borrowBook(library[3]);
       
        System.out.println("\n=== Final Library Status ===");
        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
       
        System.out.println("\n=== Final Book Status ===");
        for (Book book : library) {
            book.displayBookInfo();
        }
       
        System.out.println("\n=== Final Member Status ===");
        for (Member member : members) {
            member.displayMemberInfo();
        }
    }
}


