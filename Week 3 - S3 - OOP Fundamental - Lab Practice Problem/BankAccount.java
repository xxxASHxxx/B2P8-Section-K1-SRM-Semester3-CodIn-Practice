public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
   
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
       
        if (initialDeposit >= 0) {
            this.balance = initialDeposit;
            System.out.println("Account created successfully for " + accountHolderName);
        } else {
            this.balance = 0;
            System.out.println("Invalid initial deposit. Account created with $0 balance.");
        }
    }
   
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to account " + accountNumber);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }
   
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Current balance: $" + balance);
        } else {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from account " + accountNumber);
            System.out.println("Remaining balance: $" + balance);
        }
    }
   
    public double checkBalance() {
        System.out.println("Current balance for " + accountHolderName + ": $" + balance);
        return balance;
    }
   
    public static int getTotalAccounts() {
        return totalAccounts;
    }
   
    private static String generateAccountNumber() {
        return String.format("ACC%03d", totalAccounts + 1);
    }
   
    public void displayAccountInfo() {
        System.out.println("================================");
        System.out.println("Account Information");
        System.out.println("================================");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: $" + balance);
        System.out.println("================================");
    }
   
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[5];
       
        System.out.println("=== Creating Bank Accounts ===");
        accounts[0] = new BankAccount("Alice Johnson", 1500.00);
        accounts[1] = new BankAccount("Bob Smith", 2500.75);
        accounts[2] = new BankAccount("Carol Davis", 800.25);
        accounts[3] = new BankAccount("David Wilson", 0.00);
        accounts[4] = new BankAccount("Eva Martinez", -100.00);
       
        System.out.println("\nTotal accounts created: " + BankAccount.getTotalAccounts());
       
        System.out.println("\n=== Initial Account Information ===");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                accounts[i].displayAccountInfo();
            }
        }
       
        System.out.println("\n=== Testing Deposit Operations ===");
        accounts[0].deposit(500.00);
        accounts[1].deposit(-50.00);
        accounts[2].deposit(200.00);
       
        System.out.println("\n=== Testing Withdrawal Operations ===");
        accounts[0].withdraw(300.00);
        accounts[1].withdraw(3000.00);
        accounts[2].withdraw(-100.00);
        accounts[3].withdraw(50.00);
       
        System.out.println("\n=== Checking Balances ===");
        for (int i = 0; i < 3; i++) {
            accounts[i].checkBalance();
        }
       
        System.out.println("\n=== Final Account Status ===");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                accounts[i].displayAccountInfo();
            }
        }
       
        System.out.println("\n=== Static vs Instance Variable Demonstration ===");
        System.out.println("Total accounts (static): " + BankAccount.getTotalAccounts());
        System.out.println("This number is the same for all account objects:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Account " + (i+1) + " sees total accounts: " + BankAccount.getTotalAccounts());
        }
       
        BankAccount newAccount = new BankAccount("Frank Miller", 1000.00);
        System.out.println("After creating one more account:");
        System.out.println("Updated total accounts: " + BankAccount.getTotalAccounts());
    }
}


