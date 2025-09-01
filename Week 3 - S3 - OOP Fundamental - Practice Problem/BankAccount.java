public class BankAccount {
    static String bankName;
    static int totalAccounts = 0;
    static double interestRate;
   
    private String accountNumber;
    private String accountHolder;
    private double balance;
   
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        totalAccounts++;
    }
   
    public static void setBankName(String name) {
        bankName = name;
    }
   
    public static void setInterestRate(double rate) {
        interestRate = rate;
    }
   
    public static int getTotalAccounts() {
        return totalAccounts;
    }
   
    public static void displayBankInfo() {
        System.out.println("Bank: " + bankName);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Current Interest Rate: " + (interestRate * 100) + "%");
        System.out.println("========================");
    }
   
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account " + accountNumber);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
   
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from account " + accountNumber);
            System.out.println("New balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal");
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }
   
    public double calculateInterest() {
        return balance * interestRate;
    }
   
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
        System.out.println("Interest Earned: $" + String.format("%.2f", calculateInterest()));
        System.out.println("-------------------");
    }
   
    public static void main(String[] args) {
        BankAccount.setBankName("First National Bank");
        BankAccount.setInterestRate(0.025);
       
        BankAccount account1 = new BankAccount("ACC001", "Sarah Williams", 1500.00);
        BankAccount account2 = new BankAccount("ACC002", "David Rodriguez", 2300.50);
        BankAccount account3 = new BankAccount("ACC003", "Lisa Chen", 850.75);
       
        System.out.println("=== Static Information Shared Across All Accounts ===");
        BankAccount.displayBankInfo();
       
        System.out.println("Account 1 sees total accounts: " + account1.getTotalAccounts());
        System.out.println("Account 2 sees total accounts: " + account2.getTotalAccounts());
        System.out.println("Account 3 sees total accounts: " + account3.getTotalAccounts());
       
        System.out.println("\n=== Instance Information Unique to Each Account ===");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        account3.displayAccountInfo();
       
        System.out.println("=== Testing Account Operations ===");
        account1.deposit(500.00);
        account2.withdraw(200.00);
        account3.deposit(1000.00);
       
        System.out.println("\n=== Updated Account Information ===");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        account3.displayAccountInfo();
       
        System.out.println("=== Static Method Access Demonstration ===");
        System.out.println("Calling via class name: " + BankAccount.getTotalAccounts() + " total accounts");
        System.out.println("Calling via object: " + account1.getTotalAccounts() + " total accounts");
       
        BankAccount.setInterestRate(0.035);
        System.out.println("After changing interest rate, all accounts affected:");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        account3.displayAccountInfo();
    }
}


