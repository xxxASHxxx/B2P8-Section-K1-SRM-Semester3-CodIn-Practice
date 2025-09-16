public class SecureBankAccount {
// Private fields that should NEVER be accessed directly
private final String accountNumber; // Read-only after creation
private double balance; // Only modified through
controlled methods
private int pin; // Write-only for security
private boolean isLocked; // Internal security state
private int failedAttempts; // Internal security counter

// Private constants

private static final int MAX_FAILED_ATTEMPTS = 3;
private static final double MIN_BALANCE = 0.0;

// Constructor that takes accountNumber and initial balance
public SecureBankAccount(String accountNumber, double
initialBalance) {
this.accountNumber = accountNumber;
this.balance = Math.max(initialBalance, MIN_BALANCE); // Ensure
non-negative
this.pin = 0; // Must be set separately for
security
this.isLocked = false;
this.failedAttempts = 0;
System.out.println(&quot;Account &quot; + accountNumber + &quot; created with
balance: $&quot; + this.balance);
}

// ================== PUBLIC METHODS FOR CONTROLLED ACCESS
==================

// Account Info Methods
public String getAccountNumber() {
return accountNumber;
}

public double getBalance() {
if (isLocked) {
System.out.println(&quot;Account is locked. Cannot access
balance.&quot;);
return -1; // Indicate error
}

return balance;
}

public boolean isAccountLocked() {
return isLocked;
}

// Security Methods
public boolean setPin(int oldPin, int newPin) {
if (isLocked) {
System.out.println(&quot;Account is locked. Cannot change
PIN.&quot;);
return false;
}

if (pin == 0 || pin == oldPin) { // First time or correct old
PIN
if (newPin &gt;= 1000 &amp;&amp; newPin &lt;= 9999) { // 4-digit PIN
validation
pin = newPin;
resetFailedAttempts();
System.out.println(&quot;PIN successfully updated for
account &quot; + accountNumber);
return true;
} else {
System.out.println(&quot;PIN must be 4 digits (1000-9999)&quot;);
return false;
}
} else {
System.out.println(&quot;Incorrect old PIN. PIN change
failed.&quot;);

incrementFailedAttempts();
return false;
}
}

public boolean validatePin(int enteredPin) {
if (isLocked) {
System.out.println(&quot;Account is locked due to too many
failed attempts.&quot;);
return false;
}

if (pin == 0) {
System.out.println(&quot;PIN not set. Please set PIN first.&quot;);
return false;
}

if (pin == enteredPin) {
resetFailedAttempts();
return true;
} else {
incrementFailedAttempts();
System.out.println(&quot;Incorrect PIN. Attempts remaining: &quot; +
(MAX_FAILED_ATTEMPTS - failedAttempts));
return false;
}
}

public boolean unlockAccount(int correctPin) {

if (!isLocked) {
System.out.println(&quot;Account is not locked.&quot;);
return true;
}

if (pin == correctPin) {
isLocked = false;
resetFailedAttempts();
System.out.println(&quot;Account &quot; + accountNumber + &quot;
successfully unlocked.&quot;);
return true;
} else {
System.out.println(&quot;Incorrect PIN. Account remains
locked.&quot;);
return false;
}
}

// Transaction Methods
public boolean deposit(double amount, int pin) {
if (!validatePin(pin)) {
return false;
}

if (amount &lt;= 0) {
System.out.println(&quot;Deposit amount must be positive.&quot;);
return false;
}

balance += amount;

System.out.println(&quot;Deposited $&quot; + amount + &quot;. New balance: $&quot;
+ balance);
return true;
}

public boolean withdraw(double amount, int pin) {
if (!validatePin(pin)) {
return false;
}

if (amount &lt;= 0) {
System.out.println(&quot;Withdrawal amount must be positive.&quot;);
return false;
}

if (balance - amount &lt; MIN_BALANCE) {
System.out.println(&quot;Insufficient funds. Current balance: $&quot;
+ balance);
return false;
}

balance -= amount;
System.out.println(&quot;Withdrew $&quot; + amount + &quot;. New balance: $&quot; +
balance);
return true;
}

public boolean transfer(SecureBankAccount target, double amount,
int pin) {
if (!validatePin(pin)) {

return false;
}

if (target == null) {
System.out.println(&quot;Invalid target account.&quot;);
return false;
}

if (target.isLocked) {
System.out.println(&quot;Target account is locked. Transfer
failed.&quot;);
return false;
}

if (amount &lt;= 0) {
System.out.println(&quot;Transfer amount must be positive.&quot;);
return false;
}

if (balance - amount &lt; MIN_BALANCE) {
System.out.println(&quot;Insufficient funds for transfer.
Current balance: $&quot; + balance);
return false;
}

// Perform transfer
balance -= amount;
target.balance += amount;
System.out.println(&quot;Transferred $&quot; + amount + &quot; from &quot; +
accountNumber +

&quot; to &quot; + target.accountNumber);
System.out.println(&quot;Your new balance: $&quot; + balance);
return true;
}

// ================== PRIVATE HELPER METHODS ==================

private void lockAccount() {
isLocked = true;
System.out.println(&quot;⚠️ SECURITY ALERT: Account &quot; +
accountNumber +
&quot; has been LOCKED due to multiple failed PIN
attempts!&quot;);
}

private void resetFailedAttempts() {
failedAttempts = 0;
}

private void incrementFailedAttempts() {
failedAttempts++;
if (failedAttempts &gt;= MAX_FAILED_ATTEMPTS) {
lockAccount();
}
}

// ================== MAIN METHOD FOR TESTING ==================

public static void main(String[] args) {
System.out.println(&quot;=== SECURE BANK ACCOUNT DEMO ===\n&quot;);

// Create two SecureBankAccount objects
SecureBankAccount account1 = new SecureBankAccount(&quot;ACC-001&quot;,
1000.0);
SecureBankAccount account2 = new SecureBankAccount(&quot;ACC-002&quot;,
500.0);

System.out.println(&quot;\n--- Attempting Direct Field Access
(Should Fail) ---&quot;);
// These would cause compile errors if uncommented:
// System.out.println(account1.balance); // ✗ COMPILE ERROR
// account1.pin = 1234; // ✗ COMPILE ERROR
// account1.isLocked = true; // ✗ COMPILE ERROR
System.out.println(&quot;✓ Private fields are properly encapsulated!&quot;);

System.out.println(&quot;\n--- Proper Usage Through Public Methods -
--&quot;);

// Set PINs for both accounts
System.out.println(&quot;\n1. Setting PINs:&quot;);
account1.setPin(0, 1234); // First time setup
account2.setPin(0, 5678); // First time setup

// Make deposits and withdrawals
System.out.println(&quot;\n2. Making transactions:&quot;);
account1.deposit(200.0, 1234);
account1.withdraw(150.0, 1234);
account2.deposit(100.0, 5678);

// Transfer money between accounts

System.out.println(&quot;\n3. Transfer between accounts:&quot;);
account1.transfer(account2, 300.0, 1234);

System.out.println(&quot;\n4. Checking balances:&quot;);
System.out.println(&quot;Account 1 balance: $&quot; +
account1.getBalance());
System.out.println(&quot;Account 2 balance: $&quot; +
account2.getBalance());

System.out.println(&quot;\n--- Security Features Demonstration ---
&quot;);

// Attempt security breaches
System.out.println(&quot;\n5. Testing wrong PIN multiple times:&quot;);
account1.withdraw(50.0, 9999); // Wrong PIN - attempt 1
account1.withdraw(50.0, 8888); // Wrong PIN - attempt 2
account1.withdraw(50.0, 7777); // Wrong PIN - attempt 3
(should lock)

System.out.println(&quot;\n6. Attempting operations on locked
account:&quot;);
account1.deposit(100.0, 1234); // Should fail - account locked
System.out.println(&quot;Account 1 locked status: &quot; +
account1.isAccountLocked());

System.out.println(&quot;\n7. Unlocking account:&quot;);
account1.unlockAccount(1234); // Correct PIN - should unlock
account1.deposit(100.0, 1234); // Should work now

System.out.println(&quot;\n8. Testing insufficient funds:&quot;);
account1.withdraw(10000.0, 1234); // More than balance

System.out.println(&quot;\n9. Testing invalid amounts:&quot;);
account1.deposit(-50.0, 1234); // Negative deposit
account1.withdraw(0.0, 1234); // Zero withdrawal

System.out.println(&quot;\n=== FINAL ACCOUNT STATUS ===&quot;);
System.out.println(&quot;Account &quot; + account1.getAccountNumber() +
&quot; - Balance: $&quot; + account1.getBalance() +
&quot; - Locked: &quot; + account1.isAccountLocked());
System.out.println(&quot;Account &quot; + account2.getAccountNumber() +
&quot; - Balance: $&quot; + account2.getBalance() +
&quot; - Locked: &quot; + account2.isAccountLocked());
}
}