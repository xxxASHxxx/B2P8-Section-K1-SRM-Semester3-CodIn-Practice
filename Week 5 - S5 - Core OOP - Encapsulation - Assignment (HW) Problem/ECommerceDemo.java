import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// a. Immutable Product class
public final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;
    
    private Product(String productId, String name, String category, String manufacturer,
                   double basePrice, double weight, String[] features, 
                   Map<String, String> specifications) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features.clone();
        this.specifications = new HashMap<>(specifications);
    }
    
    public static Product createElectronics(String productId, String name, String manufacturer,
                                          double basePrice, double weight, String[] features,
                                          Map<String, String> specifications) {
        return new Product(productId, name, "Electronics", manufacturer, 
                          basePrice, weight, features, specifications);
    }
    
    public static Product createClothing(String productId, String name, String manufacturer,
                                       double basePrice, double weight, String[] features,
                                       Map<String, String> specifications) {
        return new Product(productId, name, "Clothing", manufacturer, 
                          basePrice, weight, features, specifications);
    }
    
    public static Product createBooks(String productId, String name, String manufacturer,
                                    double basePrice, double weight, String[] features,
                                    Map<String, String> specifications) {
        return new Product(productId, name, "Books", manufacturer, 
                          basePrice, weight, features, specifications);
    }
    
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }
    
    public final double calculateTax(String region) {
        switch (region.toLowerCase()) {
            case "domestic":
                return basePrice * 0.08;
            case "international":
                return basePrice * 0.15;
            case "exempt":
                return 0.0;
            default:
                return basePrice * 0.10;
        }
    }
}

// b. Customer class with privacy tiers
class Customer {
    private final String customerId;
    private final String email;
    private final String accountCreationDate;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private int creditRating;
    
    public Customer(String customerId, String email, String name, 
                   String phoneNumber, String preferredLanguage) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.accountCreationDate = LocalDateTime.now().toString();
        this.creditRating = 750;
    }
    
    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getAccountCreationDate() { return accountCreationDate; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    
    int getCreditRating() { return creditRating; }
    void updateCreditRating(int newRating) { this.creditRating = newRating; }
    
    public Map<String, String> getPublicProfile() {
        Map<String, String> publicInfo = new HashMap<>();
        publicInfo.put("name", name);
        publicInfo.put("memberSince", accountCreationDate.substring(0, 10));
        publicInfo.put("preferredLanguage", preferredLanguage);
        return publicInfo;
    }
}

// c. ShoppingCart class
class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private List<Object> items;
    private List<Integer> quantities;
    private double totalAmount;
    private int itemCount;
    
    public ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.totalAmount = 0.0;
        this.itemCount = 0;
    }
    
    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) {
            System.out.println("Invalid product type");
            return false;
        }
        if (quantity <= 0) {
            System.out.println("Quantity must be positive");
            return false;
        }
        Product validProduct = (Product) product;
        items.add(validProduct);
        quantities.add(quantity);
        totalAmount += validProduct.getBasePrice() * quantity;
        itemCount += quantity;
        return true;
    }
    
    private double calculateDiscount() {
        if (totalAmount > 1000) {
            return totalAmount * 0.10;
        } else if (totalAmount > 500) {
            return totalAmount * 0.05;
        }
        return 0.0;
    }
    
    Map<String, Object> getCartSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("cartId", cartId);
        summary.put("customerId", customerId);
        summary.put("itemCount", itemCount);
        summary.put("subtotal", totalAmount);
        summary.put("discount", calculateDiscount());
        summary.put("finalAmount", totalAmount - calculateDiscount());
        return summary;
    }
    
    public String getCartId() { return cartId; }
    public String getCustomerId() { return customerId; }
    public int getItemCount() { return itemCount; }
    public double getTotalAmount() { return totalAmount - calculateDiscount(); }
}

// d. Constructor chaining for different order types
class OrderBuilder {
    private String orderId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerType;
    private String companyName;
    private double specialDiscount;
    private boolean hasPriorityShipping;
    
    public OrderBuilder(String orderId, String customerEmail, String customerName) {
        this(orderId, null, customerName, customerEmail, "GUEST", null, 0.0, false);
    }
    
    public OrderBuilder(String orderId, String customerId, String customerName, String customerEmail) {
        this(orderId, customerId, customerName, customerEmail, "REGISTERED", null, 0.0, false);
    }
    
    public OrderBuilder(String orderId, String customerId, String customerName, 
                       String customerEmail, double specialDiscount) {
        this(orderId, customerId, customerName, customerEmail, "PREMIUM", null, specialDiscount, true);
    }
    
    public OrderBuilder(String orderId, String customerId, String customerName, 
                       String customerEmail, String customerType, String companyName,
                       double specialDiscount, boolean hasPriorityShipping) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerType = customerType;
        this.companyName = companyName;
        this.specialDiscount = specialDiscount;
        this.hasPriorityShipping = hasPriorityShipping;
        
        validateCustomerType();
    }
    
    private void validateCustomerType() {
        if ("CORPORATE".equals(customerType) && (companyName == null || companyName.isEmpty())) {
            throw new IllegalArgumentException("Corporate accounts must have company name");
        }
    }
    
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getCustomerType() { return customerType; }
    public String getCompanyName() { return companyName; }
    public double getSpecialDiscount() { return specialDiscount; }
    public boolean hasPriorityShipping() { return hasPriorityShipping; }
}

// e. Order class
class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final String customerId;
    private final List<Product> products;
    private final Map<String, Integer> quantities;
    private final double totalAmount;
    
    public Order(String orderId, String customerId, List<Product> products, 
                Map<String, Integer> quantities, double totalAmount) {
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
        this.customerId = customerId;
        this.products = new ArrayList<>(products);
        this.quantities = new HashMap<>(quantities);
        this.totalAmount = totalAmount;
    }
    
    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public String getCustomerId() { return customerId; }
    public double getTotalAmount() { return totalAmount; }
    public List<Product> getProducts() { return new ArrayList<>(products); }
    public Map<String, Integer> getQuantities() { return new HashMap<>(quantities); }
}

// PaymentProcessor class
class PaymentProcessor {
    private final String processorId;
    private final String securityKey;
    private final Map<String, String> supportedMethods;
    
    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
        this.supportedMethods = new HashMap<>();
        initializeSupportedMethods();
    }
    
    private void initializeSupportedMethods() {
        supportedMethods.put("CREDIT_CARD", "Visa, MasterCard, Amex");
        supportedMethods.put("DIGITAL_WALLET", "PayPal, Apple Pay, Google Pay");
        supportedMethods.put("BANK_TRANSFER", "ACH, Wire Transfer");
    }
    
    public boolean processPayment(String paymentMethod, double amount, String customerDetails) {
        if (!supportedMethods.containsKey(paymentMethod)) {
            return false;
        }
        if (amount > 0 && customerDetails != null && !customerDetails.isEmpty()) {
            System.out.println("Processing payment of $" + amount + " via " + paymentMethod);
            return true;
        }
        return false;
    }
    
    public String getProcessorId() { return processorId; }
}

// ShippingCalculator class
class ShippingCalculator {
    private final Map<String, Double> shippingRates;
    private final String calculatorId;
    
    public ShippingCalculator(String calculatorId) {
        this.calculatorId = calculatorId;
        this.shippingRates = new HashMap<>();
        initializeShippingRates();
    }
    
    private void initializeShippingRates() {
        shippingRates.put("STANDARD", 5.99);
        shippingRates.put("EXPRESS", 12.99);
        shippingRates.put("OVERNIGHT", 24.99);
        shippingRates.put("FREE", 0.0);
    }
    
    public double calculateShipping(String shippingMethod, double orderWeight, double orderValue) {
        if (!shippingRates.containsKey(shippingMethod)) {
            return shippingRates.get("STANDARD");
        }
        double baseRate = shippingRates.get(shippingMethod);
        if (orderValue > 75.0 && "STANDARD".equals(shippingMethod)) {
            return 0.0;
        }
        if (orderWeight > 5.0) {
            baseRate += (orderWeight - 5.0) * 1.5;
        }
        return baseRate;
    }
    
    public Set<String> getAvailableShippingMethods() {
        return new HashSet<>(shippingRates.keySet());
    }
}

// f. ECommerceSystem final class
final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new ConcurrentHashMap<>();
    private static final PaymentProcessor paymentProcessor = new PaymentProcessor("SYS001", "SECURE_KEY_123");
    private static final ShippingCalculator shippingCalculator = new ShippingCalculator("CALC001");
    
    static {
        initializeProductCatalog();
    }
    
    private static void initializeProductCatalog() {
        Map<String, String> laptopSpecs = new HashMap<>();
        laptopSpecs.put("RAM", "16GB");
        laptopSpecs.put("Storage", "512GB SSD");
        laptopSpecs.put("Processor", "Intel i7");
        Product laptop = Product.createElectronics("ELEC001", "Gaming Laptop", "TechCorp",
            1299.99, 2.5, new String[]{"Gaming", "Portable", "High Performance"}, laptopSpecs);
        productCatalog.put("ELEC001", laptop);
        
        Map<String, String> shirtSpecs = new HashMap<>();
        shirtSpecs.put("Material", "100% Cotton");
        shirtSpecs.put("Size", "M");
        shirtSpecs.put("Color", "Blue");
        Product shirt = Product.createClothing("CLOTH001", "Casual Shirt", "FashionBrand",
            29.99, 0.3, new String[]{"Casual", "Comfortable", "Breathable"}, shirtSpecs);
        productCatalog.put("CLOTH001", shirt);
        
        Map<String, String> bookSpecs = new HashMap<>();
        bookSpecs.put("Pages", "320");
        bookSpecs.put("Language", "English");
        bookSpecs.put("Edition", "2nd");
        Product book = Product.createBooks("BOOK001", "Java Programming Guide", "TechPublisher",
            49.99, 0.8, new String[]{"Educational", "Programming", "Reference"}, bookSpecs);
        productCatalog.put("BOOK001", book);
    }
    
    public static boolean processOrder(Object order, Object customer) {
        if (!(order instanceof Order) || !(customer instanceof Customer)) {
            System.out.println("Invalid order or customer type");
            return false;
        }
        Order validOrder = (Order) order;
        Customer validCustomer = (Customer) customer;
        if (!validateCustomer(validCustomer)) {
            System.out.println("Customer validation failed");
            return false;
        }
        boolean paymentSuccess = paymentProcessor.processPayment("CREDIT_CARD", 
            validOrder.getTotalAmount(), validCustomer.getEmail());
        if (!paymentSuccess) {
            System.out.println("Payment processing failed");
            return false;
        }
        double totalWeight = calculateOrderWeight(validOrder);
        double shippingCost = shippingCalculator.calculateShipping("STANDARD", 
            totalWeight, validOrder.getTotalAmount());
        System.out.println("Order processed successfully!");
        System.out.println("Order ID: " + validOrder.getOrderId());
        System.out.println("Customer: " + validCustomer.getName());
        System.out.println("Total Amount: $" + validOrder.getTotalAmount());
        System.out.println("Shipping Cost: $" + shippingCost);
        return true;
    }
    
    public static boolean addProduct(String productId, Object product) {
        if (!(product instanceof Product)) {
            return false;
        }
        productCatalog.put(productId, product);
        return true;
    }
    
    public static Object getProduct(String productId) {
        return productCatalog.get(productId);
    }
    
    public static Set<String> getAvailableProducts() {
        return new HashSet<>(productCatalog.keySet());
    }
    
    private static boolean validateCustomer(Customer customer) {
        return customer.getCustomerId() != null && 
               customer.getEmail() != null && 
               !customer.getEmail().isEmpty();
    }
    
    private static double calculateOrderWeight(Order order) {
        double totalWeight = 0.0;
        for (Product product : order.getProducts()) {
            String productId = product.getProductId();
            Integer quantity = order.getQuantities().get(productId);
            if (quantity != null) {
                totalWeight += product.getWeight() * quantity;
            }
        }
        return totalWeight;
    }
    
    public static Map<String, Object> fulfillOrder(String orderId) {
        Map<String, Object> fulfillmentStatus = new HashMap<>();
        fulfillmentStatus.put("orderId", orderId);
        fulfillmentStatus.put("status", "PROCESSING");
        fulfillmentStatus.put("estimatedDelivery", "3-5 business days");
        fulfillmentStatus.put("trackingNumber", "TRK" + System.currentTimeMillis());
        System.out.println("Order " + orderId + " is being fulfilled");
        return fulfillmentStatus;
    }
}

// Demo class to test functionality
class ECommerceDemo {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce System Demo ===\n");
        
        // Create customer
        Customer customer = new Customer("CUST001", "john.doe@email.com", 
            "John Doe", "555-1234", "English");
        
        // Create shopping cart
        ShoppingCart cart = new ShoppingCart("CART001", "CUST001");
        
        // Get products from catalog
        Product laptop = (Product) ECommerceSystem.getProduct("ELEC001");
        Product shirt = (Product) ECommerceSystem.getProduct("CLOTH001");
        
        // Add items to cart
        cart.addItem(laptop, 1);
        cart.addItem(shirt, 2);
        
        // Display cart summary
        Map<String, Object> cartSummary = cart.getCartSummary();
        System.out.println("Cart Summary: " + cartSummary);
        
        // Create order
        List<Product> products = Arrays.asList(laptop, shirt);
        Map<String, Integer> quantities = new HashMap<>();
        quantities.put("ELEC001", 1);
        quantities.put("CLOTH001", 2);
        
        Order order = new Order("ORD001", "CUST001", products, quantities, cart.getTotalAmount());
        
        // Process order
        boolean success = ECommerceSystem.processOrder(order, customer);
        
        if (success) {
            // Fulfill order
            Map<String, Object> fulfillment = ECommerceSystem.fulfillOrder("ORD001");
            System.out.println("Fulfillment Status: " + fulfillment);
        }
        
        // Test different order types
        System.out.println("\n=== Testing Different Order Types ===");
        
        // Guest checkout
        OrderBuilder guestOrder = new OrderBuilder("ORD002", "guest@email.com", "Guest User");
        System.out.println("Guest Order Type: " + guestOrder.getCustomerType());
        
        // Premium member
        OrderBuilder premiumOrder = new OrderBuilder("ORD003", "CUST002", "Premium User", 
            "premium@email.com", 15.0);
        System.out.println("Premium Order Type: " + premiumOrder.getCustomerType() + 
            " with discount: " + premiumOrder.getSpecialDiscount() + "%");
        
        System.out.println("\n=== Demo Complete ===");
    }
}
