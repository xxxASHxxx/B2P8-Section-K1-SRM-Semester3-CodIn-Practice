public class PersonalFinanceManager {
   
    static class PersonalAccount {
        // Private instance attributes
        private String accountHolderName;
        private String accountNumber;
        private double currentBalance;
        private double totalIncome;
        private double totalExpenses;
       
        // Static variables - shared across all accounts
        private static int totalAccounts = 0;
        private static String bankName = "Default Bank";
       
        // Constructor
        public PersonalAccount(String accountHolderName) {
            this.accountHolderName = accountHolderName;
            this.accountNumber = generateAccountNumber();
            this.currentBalance = 0.0;
            this.totalIncome = 0.0;
            this.totalExpenses = 0.0;
            totalAccounts++;
        }
       
        // Instance method to add income
        public void addIncome(double amount, String description) {
            if (amount > 0) {
                this.totalIncome += amount;
                this.currentBalance += amount;
                System.out.println("SUCCESS: " + description + " - Added income of $" + amount +
                                 " to " + accountHolderName + "'s account");
            } else {
                System.out.println("ERROR: Invalid income amount. Must be positive.");
            }
        }
       
        // Instance method to add expense
        public void addExpense(double amount, String description) {
            if (amount > 0) {
                if (amount <= currentBalance) {
                    this.totalExpenses += amount;
                    this.currentBalance -= amount;
                    System.out.println("SUCCESS: " + description + " - Deducted expense of $" + amount +
                                     " from " + accountHolderName + "'s account");
                } else {
                    System.out.println("ERROR: Insufficient balance for " + accountHolderName +
                                     ". Available: $" + currentBalance + ", Requested: $" + amount);
                }
            } else {
                System.out.println("ERROR: Invalid expense amount. Must be positive.");
            }
        }
       
        // Instance method to calculate savings
        public double calculateSavings() {
            return totalIncome - totalExpenses;
        }
       
        // Instance method to display account summary
        public void displayAccountSummary() {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("         ACCOUNT SUMMARY");
            System.out.println("=".repeat(50));
            System.out.println("Bank Name: " + bankName);
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Current Balance: $" + String.format("%.2f", currentBalance));
            System.out.println("Total Income: $" + String.format("%.2f", totalIncome));
            System.out.println("Total Expenses: $" + String.format("%.2f", totalExpenses));
            System.out.println("Net Savings: $" + String.format("%.2f", calculateSavings()));
            System.out.println("=".repeat(50));
        }
       
        // Static method to set bank name (affects all accounts)
        public static void setBankName(String name) {
            bankName = name;
            System.out.println("Bank name updated to: " + bankName);
        }
       
        // Static method to get total accounts created
        public static int getTotalAccounts() {
            return totalAccounts;
        }
       
        // Static method to generate unique account numbers
        private static String generateAccountNumber() {
            return "PFM" + String.format("%04d", totalAccounts + 1);
        }
       
        // Getter methods
        public String getAccountHolderName() { return accountHolderName; }
        public double getCurrentBalance() { return currentBalance; }
        public static String getBankName() { return bankName; }
    }
   
    public static void main(String[] args) {
        System.out.println("PERSONAL FINANCE MANAGER SYSTEM");
        System.out.println("=".repeat(60));
       
        // Set bank name using static method
        PersonalAccount.setBankName("Prime Financial Bank");
       
        System.out.println("\nCreating Personal Accounts...");
       
        // Create 3 different personal accounts
        PersonalAccount account1 = new PersonalAccount("Alice Johnson");
        PersonalAccount account2 = new PersonalAccount("Bob Smith");
        PersonalAccount account3 = new PersonalAccount("Charlie Brown");
       
        System.out.println("\nPERFORMING TRANSACTIONS:");
        System.out.println("-".repeat(60));
       
        // Account 1 transactions
        System.out.println("\nAlice Johnson's Transactions:");
        account1.addIncome(5000.0, "Monthly Salary");
        account1.addIncome(500.0, "Freelance Work");
        account1.addExpense(1200.0, "Rent Payment");
        account1.addExpense(300.0, "Groceries");
        account1.addExpense(150.0, "Utilities");
       
        // Account 2 transactions
        System.out.println("\nBob Smith's Transactions:");
        account2.addIncome(6500.0, "Software Engineer Salary");
        account2.addIncome(200.0, "Side Project");
        account2.addExpense(1500.0, "Mortgage Payment");
        account2.addExpense(400.0, "Car Payment");
        account2.addExpense(250.0, "Insurance");
        account2.addExpense(100.0, "Gym Membership");
       
        // Account 3 transactions
        System.out.println("\nCharlie Brown's Transactions:");
        account3.addIncome(4200.0, "Teacher Salary");
        account3.addIncome(300.0, "Tutoring");
        account3.addExpense(1000.0, "Rent");
        account3.addExpense(200.0, "Food");
        account3.addExpense(80.0, "Phone Bill");
        account3.addExpense(5000.0, "Expensive Car"); // This will fail due to insufficient funds
       
        // Display all account summaries
        System.out.println("\nACCOUNT SUMMARIES:");
        account1.displayAccountSummary();
        account2.displayAccountSummary();
        account3.displayAccountSummary();
       
        // Demonstrate static vs instance variables
        System.out.println("\nSTATIC VS INSTANCE VARIABLE DEMONSTRATION:");
        System.out.println("=".repeat(60));
       
        System.out.println("\nStatic Variables (Shared across all accounts):");
        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());
        System.out.println("Bank Name: " + PersonalAccount.getBankName());
       
        System.out.println("\nInstance Variables (Unique to each account):");
        System.out.println("Alice's Balance: $" + String.format("%.2f", account1.getCurrentBalance()));
        System.out.println("Bob's Balance: $" + String.format("%.2f", account2.getCurrentBalance()));
        System.out.println("Charlie's Balance: $" + String.format("%.2f", account3.getCurrentBalance()));
       
        // Change bank name to show it affects all accounts
        System.out.println("\nChanging Bank Name (affects all accounts):");
        PersonalAccount.setBankName("Global Finance Corporation");
       
        // Show that bank name change affects all accounts
        System.out.println("\nVerifying Bank Name Change:");
        System.out.println("Account 1 Bank Name: " + PersonalAccount.getBankName());
        System.out.println("Account 2 Bank Name: " + PersonalAccount.getBankName());
        System.out.println("Account 3 Bank Name: " + PersonalAccount.getBankName());
       
        // Final system statistics
        System.out.println("\nFINAL SYSTEM STATISTICS:");
        System.out.println("=".repeat(60));
        System.out.println("Total Accounts Managed: " + PersonalAccount.getTotalAccounts());
        System.out.println("Current Bank Name: " + PersonalAccount.getBankName());
       
        double totalSystemBalance = account1.getCurrentBalance() +
                                   account2.getCurrentBalance() +
                                   account3.getCurrentBalance();
        System.out.println("Total System Balance: $" + String.format("%.2f", totalSystemBalance));
       
        // Test edge cases
        System.out.println("\nTesting Edge Cases:");
        System.out.println("-".repeat(40));
        account1.addIncome(-100.0, "Invalid Negative Income");
        account1.addExpense(-50.0, "Invalid Negative Expense");
        account1.addExpense(10000.0, "Exceeds Balance");
       
        System.out.println("\nPersonal Finance Manager Demo Completed Successfully!");
    }
}


