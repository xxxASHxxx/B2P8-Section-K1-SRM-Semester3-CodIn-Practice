// Template Method Pattern: Food Preparation
abstract class Food {
    // Template method - defines the algorithm structure
    public final void prepare() {
        System.out.println("=== Preparing " + getClass().getSimpleName() + " ===");
        wash();
        cook();
        serve();
        System.out.println("Food preparation complete!\n");
    }
    
    // Abstract methods - must be implemented by subclasses
    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

class Pizza extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables and cleaning workspace");
    }
    
    @Override
    protected void cook() {
        System.out.println("Rolling dough, adding toppings, baking in oven at 450F");
    }
    
    @Override
    protected void serve() {
        System.out.println("Slicing pizza and serving on plates");
    }
}

class Soup extends Food {
    @Override
    protected void wash() {
        System.out.println("Washing vegetables and cleaning pot");
    }
    
    @Override
    protected void cook() {
        System.out.println("Chopping ingredients, boiling water, simmering for 30 minutes");
    }
    
    @Override
    protected void serve() {
        System.out.println("Ladling soup into bowls with crackers");
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("TEMPLATE METHOD PATTERN DEMO");
        
        Food pizza = new Pizza();
        pizza.prepare();
        
        Food soup = new Soup();
        soup.prepare();
        
        // Template method ensures same sequence for all food types
        System.out.println("Both foods follow same preparation steps: wash -> cook -> serve");
    }
}
