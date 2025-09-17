// Method Overloading with Inheritance
class BasicMath {
    // Overloaded calculate() methods - same name, different parameters
    
    // Single parameter - square
    public int calculate(int a) {
        System.out.println("BasicMath: Squaring " + a);
        return a * a;
    }
    
    // Two parameters - addition
    public int calculate(int a, int b) {
        System.out.println("BasicMath: Adding " + a + " + " + b);
        return a + b;
    }
    
    // Three parameters - sum
    public int calculate(int a, int b, int c) {
        System.out.println("BasicMath: Sum " + a + " + " + b + " + " + c);
        return a + b + c;
    }
    
    // Different type - double addition
    public double calculate(double a, double b) {
        System.out.println("BasicMath: Double addition " + a + " + " + b);
        return a + b;
    }
}

class AdvancedMath extends BasicMath {
    // Inherits all BasicMath calculate() methods
    
    // New overloaded method - array sum
    public int calculate(int[] numbers) {
        System.out.print("AdvancedMath: Array sum [");
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            System.out.print(numbers[i] + (i < numbers.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        return sum;
    }
    
    // New overloaded method - power calculation
    public int calculate(int base, int exponent, String operation) {
        if (operation.equals("power")) {
            System.out.println("AdvancedMath: Power " + base + "^" + exponent);
            return (int) Math.pow(base, exponent);
        }
        return 0;
    }
    
    // New overloaded method - percentage calculation
    public double calculate(double value, double percentage, boolean isPercentage) {
        if (isPercentage) {
            System.out.println("AdvancedMath: " + percentage + "% of " + value);
            return (value * percentage) / 100;
        }
        return value;
    }
}

public class MethodOverloadingDemo {
    public static void main(String[] args) {
        System.out.println("=== METHOD OVERLOADING WITH INHERITANCE ===");
        
        BasicMath basic = new BasicMath();
        AdvancedMath advanced = new AdvancedMath();
        
        System.out.println("\n--- BasicMath Operations ---");
        System.out.println("Result: " + basic.calculate(5));           // Square
        System.out.println("Result: " + basic.calculate(10, 20));      // Addition
        System.out.println("Result: " + basic.calculate(1, 2, 3));     // Sum of 3
        System.out.println("Result: " + basic.calculate(2.5, 3.7));    // Double addition
        
        System.out.println("\n--- AdvancedMath: Inherited Methods ---");
        System.out.println("Result: " + advanced.calculate(4));         // Inherited - Square
        System.out.println("Result: " + advanced.calculate(15, 25));    // Inherited - Addition
        System.out.println("Result: " + advanced.calculate(5, 10, 15)); // Inherited - Sum of 3
        System.out.println("Result: " + advanced.calculate(1.2, 2.8));  // Inherited - Double
        
        System.out.println("\n--- AdvancedMath: New Overloaded Methods ---");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Result: " + advanced.calculate(arr));                    // Array sum
        System.out.println("Result: " + advanced.calculate(2, 3, "power"));         // Power
        System.out.println("Result: " + advanced.calculate(100.0, 15.0, true));     // Percentage
        
        System.out.println("\n--- Method Overloading Summary ---");
        System.out.println("BasicMath has 4 overloaded calculate() methods");
        System.out.println("AdvancedMath inherits all 4 + adds 3 new overloaded methods");
        System.out.println("Total calculate() methods available in AdvancedMath: 7");
    }
}
