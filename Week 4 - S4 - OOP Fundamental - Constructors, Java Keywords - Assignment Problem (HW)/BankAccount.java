import java.util.Random;


public class BankAccount {
    // Fields with short names
    private String holder;
    private int accNum;
    private double bal;
   
    // Static random generator for account numbers
    private static Random rand = new Random();
   
    // Constructor with name and initial balance - assigns both
    public BankAccount(String holder, double bal) {
        this.holder = holder;
        this.accNum = genAccNum();
        this.bal = bal;
    }
   
    // Constructor with name - assigns random account number, balance = 0
    public BankAccount(String holder) {
        this(holder, 0.0);
    }
   
    // Default constructor - balance = 0
    public BankAccount() {
        this("Unknown Customer", 0.0);
    }
   
    // Generate random 6-digit account number
    private int genAccNum() {
        return rand.nextInt(900000) + 100000; // Range: 100000-999999
    }
   
    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            bal += amount;
            System.out.println(holder + ": Rs." + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
   
    // Withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
        } else if (amount > bal) {
            System.out.println(holder + ": Insufficient balance! Available: Rs." + bal);
        } else {
            bal -= amount;
            System.out.println(holder + ": Rs." + amount + " withdrawn successfully.");
        }
    }
   
    // Display account details
    public void displayAccount() {
        System.out.println("=== ACCOUNT DETAILS ===");
        System.out.println("Holder: " + holder);
        System.out.println("Account No: " + accNum);
        System.out.println("Balance: Rs." + bal);
        System.out.println("=======================");
        System.out.println();
    }
   
    public static void main(String[] args) {
        System.out.println("=== BANK ACCOUNT MANAGEMENT SYSTEM ===\n");
       
        // Create accounts using different constructors
       
        // Default constructor
        BankAccount acc1 = new BankAccount();
        System.out.println("Account 1 created (Default):");
        acc1.displayAccount();
       
        // Constructor with name only
        BankAccount acc2 = new BankAccount("Rajesh Kumar");
        System.out.println("Account 2 created (Name Only):");
        acc2.displayAccount();
       
        // Constructor with name and initial balance
        BankAccount acc3 = new BankAccount("Priya Sharma", 5000.0);
        System.out.println("Account 3 created (Name + Balance):");
        acc3.displayAccount();
       
        // Perform banking operations
        System.out.println("=== BANKING OPERATIONS ===\n");
       
        // Deposits
        acc1.deposit(1000.0);
        acc2.deposit(2500.0);
        acc3.deposit(1500.0);
        System.out.println();
       
        // Withdrawals
        acc1.withdraw(300.0);
        acc2.withdraw(1000.0);
        acc3.withdraw(2000.0);
        acc3.withdraw(6000.0); // Should show insufficient balance
        System.out.println();
       
        // Invalid operations
        acc1.deposit(-500.0); // Invalid deposit
        acc2.withdraw(-200.0); // Invalid withdrawal
        System.out.println();
       
        // Display final balances
        System.out.println("=== FINAL ACCOUNT STATUS ===\n");
        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
       
        System.out.println("Thank you for using our banking system!");
    }
}
