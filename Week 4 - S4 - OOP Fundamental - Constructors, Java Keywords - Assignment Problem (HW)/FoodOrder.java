public class FoodOrder {
    // Fields with short names
    private String cust;
    private String item;
    private int qty;
    private double price;
   
    // Fixed rate per item
    private static final double RATE = 150.0;
   
    // Full constructor - sets all details
    public FoodOrder(String cust, String item, int qty, double price) {
        this.cust = cust;
        this.item = item;
        this.qty = qty;
        this.price = price;
    }
   
    // Constructor with food item and quantity - calculates price
    public FoodOrder(String item, int qty) {
        this("Unknown Customer", item, qty, qty * RATE);
    }
   
    // Constructor with food item only - quantity=1, price=default
    public FoodOrder(String item) {
        this(item, 1);
    }
   
    // Default constructor - assigns "Unknown" order
    public FoodOrder() {
        this("Unknown Item");
    }
   
    // Print bill method - displays order details and total price
    public void printBill() {
        System.out.println("=== FOOD DELIVERY BILL ===");
        System.out.println("Customer: " + cust);
        System.out.println("Food Item: " + item);
        System.out.println("Quantity: " + qty);
        System.out.println("Rate per Item: Rs." + RATE);
        System.out.println("Total Price: Rs." + price);
        System.out.println("========================");
        System.out.println();
    }
   
    public static void main(String[] args) {
        System.out.println("=== FOOD DELIVERY SYSTEM ===\n");
       
        // Create multiple orders using different constructors
       
        // Default constructor - unknown order
        FoodOrder order1 = new FoodOrder();
        System.out.println("Order 1 (Default):");
        order1.printBill();
       
        // Constructor with food item only
        FoodOrder order2 = new FoodOrder("Chicken Burger");
        System.out.println("Order 2 (Item Only):");
        order2.printBill();
       
        // Constructor with food item and quantity
        FoodOrder order3 = new FoodOrder("Margherita Pizza", 3);
        System.out.println("Order 3 (Item + Quantity):");
        order3.printBill();
       
        // More examples with different items
        FoodOrder order4 = new FoodOrder("Pasta Alfredo", 2);
        System.out.println("Order 4 (Custom Quantity):");
        order4.printBill();
       
        // Custom order with specific customer and pricing
        FoodOrder order5 = new FoodOrder("Rajesh Singh", "Biryani Special", 1, 280.0);
        System.out.println("Order 5 (Premium Order):");
        order5.printBill();
       
        // Family order
        FoodOrder order6 = new FoodOrder("Paneer Tikka", 5);
        System.out.println("Order 6 (Family Pack):");
        order6.printBill();
       
        System.out.println("=== ORDER SUMMARY ===");
        System.out.println("Total orders processed: 6");
        System.out.println("Standard rate: Rs." + RATE + " per item");
        System.out.println("Thank you for choosing our food delivery service!");
    }
}
