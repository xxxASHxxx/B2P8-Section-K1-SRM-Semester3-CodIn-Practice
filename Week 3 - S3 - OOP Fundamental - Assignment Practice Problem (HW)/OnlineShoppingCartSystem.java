import java.util.Scanner;


public class OnlineShoppingCartSystem {
   
    static class Product {
        // Instance attributes
        private String productId;
        private String productName;
        private double price;
        private String category;
        private int stockQuantity;
       
        // Static variables
        private static int totalProducts = 0;
        private static String[] categories = {"Electronics", "Clothing", "Books", "Home", "Sports"};
       
        // Constructor
        public Product(String productId, String productName, double price, String category, int stockQuantity) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.category = category;
            this.stockQuantity = stockQuantity;
            totalProducts++;
        }
       
        // Static method to find product by ID
        public static Product findProductById(Product[] products, String productId) {
            for (Product product : products) {
                if (product != null && product.productId.equals(productId)) {
                    return product;
                }
            }
            return null;
        }
       
        // Static method to get products by category
        public static Product[] getProductsByCategory(Product[] products, String category) {
            Product[] categoryProducts = new Product[products.length];
            int count = 0;
           
            for (Product product : products) {
                if (product != null && product.category.equals(category)) {
                    categoryProducts[count] = product;
                    count++;
                }
            }
            return categoryProducts;
        }
       
        // Static method to get total products
        public static int getTotalProducts() {
            return totalProducts;
        }
       
        // Static method to get available categories
        public static String[] getCategories() {
            return categories;
        }
       
        // Method to reduce stock when product is purchased
        public boolean reduceStock(int quantity) {
            if (stockQuantity >= quantity) {
                stockQuantity -= quantity;
                return true;
            }
            return false;
        }
       
        // Method to increase stock (for restocking)
        public void increaseStock(int quantity) {
            stockQuantity += quantity;
        }
       
        // Getter methods
        public String getProductId() { return productId; }
        public String getProductName() { return productName; }
        public double getPrice() { return price; }
        public String getCategory() { return category; }
        public int getStockQuantity() { return stockQuantity; }
       
        // Method to display product information
        public void displayProduct() {
            System.out.println("ID: " + productId + " | " + productName +
                             " | $" + String.format("%.2f", price) +
                             " | Category: " + category +
                             " | Stock: " + stockQuantity);
        }
    }
   
    static class ShoppingCart {
        // Instance attributes
        private String cartId;
        private String customerName;
        private Product[] products;
        private int[] quantities;
        private double cartTotal;
        private int itemCount;
        private static final int MAX_ITEMS = 20;
       
        // Constructor
        public ShoppingCart(String cartId, String customerName) {
            this.cartId = cartId;
            this.customerName = customerName;
            this.products = new Product[MAX_ITEMS];
            this.quantities = new int[MAX_ITEMS];
            this.cartTotal = 0.0;
            this.itemCount = 0;
        }
       
        // Method to add product to cart
        public void addProduct(Product product, int quantity) {
            if (product == null) {
                System.out.println("ERROR: Product not found.");
                return;
            }
           
            if (quantity <= 0) {
                System.out.println("ERROR: Quantity must be positive.");
                return;
            }
           
            if (product.getStockQuantity() < quantity) {
                System.out.println("ERROR: Insufficient stock. Available: " + product.getStockQuantity());
                return;
            }
           
            // Check if product already exists in cart
            for (int i = 0; i < itemCount; i++) {
                if (products[i].getProductId().equals(product.getProductId())) {
                    quantities[i] += quantity;
                    calculateTotal();
                    System.out.println("SUCCESS: Updated quantity for " + product.getProductName() +
                                     ". New quantity: " + quantities[i]);
                    return;
                }
            }
           
            // Add new product to cart
            if (itemCount < MAX_ITEMS) {
                products[itemCount] = product;
                quantities[itemCount] = quantity;
                itemCount++;
                calculateTotal();
                System.out.println("SUCCESS: Added " + quantity + "x " + product.getProductName() +
                                 " to cart.");
            } else {
                System.out.println("ERROR: Cart is full. Maximum " + MAX_ITEMS + " different items allowed.");
            }
        }
       
        // Method to remove product from cart
        public void removeProduct(String productId) {
            for (int i = 0; i < itemCount; i++) {
                if (products[i].getProductId().equals(productId)) {
                    System.out.println("SUCCESS: Removed " + products[i].getProductName() + " from cart.");
                   
                    // Shift remaining items
                    for (int j = i; j < itemCount - 1; j++) {
                        products[j] = products[j + 1];
                        quantities[j] = quantities[j + 1];
                    }
                   
                    itemCount--;
                    products[itemCount] = null;
                    quantities[itemCount] = 0;
                    calculateTotal();
                    return;
                }
            }
            System.out.println("ERROR: Product not found in cart.");
        }
       
        // Method to calculate total cart value
        public void calculateTotal() {
            cartTotal = 0.0;
            for (int i = 0; i < itemCount; i++) {
                cartTotal += products[i].getPrice() * quantities[i];
            }
        }
       
        // Method to display cart contents
        public void displayCart() {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("                SHOPPING CART");
            System.out.println("=".repeat(60));
            System.out.println("Cart ID: " + cartId);
            System.out.println("Customer: " + customerName);
            System.out.println("=".repeat(60));
           
            if (itemCount == 0) {
                System.out.println("Your cart is empty.");
            } else {
                System.out.println("Item                          Qty    Price    Subtotal");
                System.out.println("-".repeat(60));
               
                for (int i = 0; i < itemCount; i++) {
                    double subtotal = products[i].getPrice() * quantities[i];
                    System.out.printf("%-25s     %2d    $%6.2f   $%7.2f%n",
                                    products[i].getProductName(),
                                    quantities[i],
                                    products[i].getPrice(),
                                    subtotal);
                }
               
                System.out.println("-".repeat(60));
                System.out.printf("Total Items: %d                        Total: $%.2f%n",
                                itemCount, cartTotal);
            }
            System.out.println("=".repeat(60));
        }
       
        // Method to checkout
        public void checkout() {
            if (itemCount == 0) {
                System.out.println("ERROR: Cannot checkout. Cart is empty.");
                return;
            }
           
            System.out.println("\nProcessing checkout...");
           
            // Check stock availability and reduce stock
            boolean allAvailable = true;
            for (int i = 0; i < itemCount; i++) {
                if (!products[i].reduceStock(quantities[i])) {
                    System.out.println("ERROR: Insufficient stock for " + products[i].getProductName());
                    allAvailable = false;
                }
            }
           
            if (allAvailable) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("              CHECKOUT SUCCESSFUL");
                System.out.println("=".repeat(50));
                System.out.println("Customer: " + customerName);
                System.out.println("Order Total: $" + String.format("%.2f", cartTotal));
                System.out.println("Thank you for your purchase!");
                System.out.println("=".repeat(50));
               
                // Clear cart after successful checkout
                clearCart();
            } else {
                System.out.println("ERROR: Checkout failed due to stock issues.");
            }
        }
       
        // Method to clear cart
        private void clearCart() {
            for (int i = 0; i < itemCount; i++) {
                products[i] = null;
                quantities[i] = 0;
            }
            itemCount = 0;
            cartTotal = 0.0;
        }
       
        // Getter methods
        public String getCartId() { return cartId; }
        public String getCustomerName() { return customerName; }
        public double getCartTotal() { return cartTotal; }
        public int getItemCount() { return itemCount; }
    }
   
    // Method to initialize sample products
    public static Product[] initializeProducts() {
        Product[] products = new Product[15];
       
        // Electronics
        products[0] = new Product("E001", "Smartphone", 599.99, "Electronics", 25);
        products[1] = new Product("E002", "Laptop", 899.99, "Electronics", 15);
        products[2] = new Product("E003", "Headphones", 149.99, "Electronics", 40);
       
        // Clothing
        products[3] = new Product("C001", "T-Shirt", 19.99, "Clothing", 100);
        products[4] = new Product("C002", "Jeans", 49.99, "Clothing", 75);
        products[5] = new Product("C003", "Sneakers", 79.99, "Clothing", 50);
       
        // Books
        products[6] = new Product("B001", "Java Programming", 39.99, "Books", 30);
        products[7] = new Product("B002", "Data Structures", 45.99, "Books", 25);
        products[8] = new Product("B003", "Web Development", 35.99, "Books", 20);
       
        // Home
        products[9] = new Product("H001", "Coffee Maker", 89.99, "Home", 20);
        products[10] = new Product("H002", "Desk Lamp", 29.99, "Home", 35);
       
        // Sports
        products[11] = new Product("S001", "Basketball", 24.99, "Sports", 40);
        products[12] = new Product("S002", "Tennis Racket", 119.99, "Sports", 15);
        products[13] = new Product("S003", "Yoga Mat", 34.99, "Sports", 30);
        products[14] = new Product("S004", "Water Bottle", 12.99, "Sports", 60);
       
        return products;
    }
   
    // Method to display all products
    public static void displayAllProducts(Product[] products) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                      AVAILABLE PRODUCTS");
        System.out.println("=".repeat(70));
       
        for (Product product : products) {
            if (product != null) {
                product.displayProduct();
            }
        }
        System.out.println("=".repeat(70));
    }
   
    // Method to display products by category
    public static void displayProductsByCategory(Product[] products, String category) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         PRODUCTS IN CATEGORY: " + category.toUpperCase());
        System.out.println("=".repeat(50));
       
        Product[] categoryProducts = Product.getProductsByCategory(products, category);
        boolean found = false;
       
        for (Product product : categoryProducts) {
            if (product != null) {
                product.displayProduct();
                found = true;
            }
        }
       
        if (!found) {
            System.out.println("No products found in this category.");
        }
        System.out.println("=".repeat(50));
    }
   
    // Main menu-driven system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product[] products = initializeProducts();
        ShoppingCart cart = null;
       
        System.out.println("WELCOME TO ONLINE SHOPPING CART SYSTEM");
        System.out.println("=".repeat(60));
       
        // Get customer information
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        String cartId = "CART" + System.currentTimeMillis();
        cart = new ShoppingCart(cartId, customerName);
       
        System.out.println("Hello " + customerName + "! Your cart ID is: " + cartId);
       
        while (true) {
            System.out.println("\nMAIN MENU:");
            System.out.println("1. Browse All Products");
            System.out.println("2. Browse by Category");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Checkout");
            System.out.println("8. System Statistics");
            System.out.println("9. Exit");
            System.out.print("Choose an option (1-9): ");
           
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
           
            switch (choice) {
                case 1:
                    displayAllProducts(products);
                    break;
                   
                case 2:
                    System.out.println("\nAvailable Categories:");
                    String[] categories = Product.getCategories();
                    for (int i = 0; i < categories.length; i++) {
                        System.out.println((i + 1) + ". " + categories[i]);
                    }
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();
                    displayProductsByCategory(products, category);
                    break;
                   
                case 3:
                    System.out.print("Enter Product ID: ");
                    String searchId = scanner.nextLine();
                    Product foundProduct = Product.findProductById(products, searchId);
                    if (foundProduct != null) {
                        System.out.println("\nProduct Found:");
                        foundProduct.displayProduct();
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                   
                case 4:
                    System.out.print("Enter Product ID to add: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                   
                    Product productToAdd = Product.findProductById(products, productId);
                    cart.addProduct(productToAdd, quantity);
                    break;
                   
                case 5:
                    System.out.print("Enter Product ID to remove: ");
                    String removeId = scanner.nextLine();
                    cart.removeProduct(removeId);
                    break;
                   
                case 6:
                    cart.displayCart();
                    break;
                   
                case 7:
                    cart.checkout();
                    break;
                   
                case 8:
                    System.out.println("\nSYSTEM STATISTICS:");
                    System.out.println("Total Products in System: " + Product.getTotalProducts());
                    System.out.println("Available Categories: " + String.join(", ", Product.getCategories()));
                    System.out.println("Items in Current Cart: " + cart.getItemCount());
                    System.out.println("Current Cart Total: $" + String.format("%.2f", cart.getCartTotal()));
                    break;
                   
                case 9:
                    System.out.println("Thank you for using Online Shopping Cart System!");
                    scanner.close();
                    return;
                   
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


