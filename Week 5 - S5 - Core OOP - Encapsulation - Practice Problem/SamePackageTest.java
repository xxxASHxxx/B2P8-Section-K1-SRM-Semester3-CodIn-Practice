package com.company.security;

public class AccessModifierDemo {
// Four fields with different access modifiers
private int privateField; // Only accessible within this
class
String defaultField; // Accessible within same
package (package-private)
protected double protectedField; // Accessible in package +
subclasses
public boolean publicField; // Accessible everywhere

// Constructor that initializes all fields
public AccessModifierDemo() {
this.privateField = 10;
this.defaultField = &quot;Default Access&quot;;
this.protectedField = 3.14;
this.publicField = true;
}

// Four methods with matching access levels

private void privateMethod() {
System.out.println(&quot;Private method called&quot;);
}

void defaultMethod() { // Package-private (no modifier)
System.out.println(&quot;Default method called&quot;);
}

protected void protectedMethod() {
System.out.println(&quot;Protected method called&quot;);
}

public void publicMethod() {
System.out.println(&quot;Public method called&quot;);
}

// Public method to test internal access
public void testInternalAccess() {
System.out.println(&quot;=== Testing Internal Access ===&quot;);
// All fields accessible within same class
System.out.println(&quot;Private field: &quot; + privateField);
System.out.println(&quot;Default field: &quot; + defaultField);
System.out.println(&quot;Protected field: &quot; + protectedField);
System.out.println(&quot;Public field: &quot; + publicField);

// All methods accessible within same class
privateMethod();
defaultMethod();
protectedMethod();
publicMethod();

}

public static void main(String[] args) {
AccessModifierDemo obj = new AccessModifierDemo();

System.out.println(&quot;=== Testing Access from Main (Same Class)
===&quot;);
// All work - same class access
System.out.println(&quot;Private field: &quot; + obj.privateField);
// ✓ Works
System.out.println(&quot;Default field: &quot; + obj.defaultField);
// ✓ Works
System.out.println(&quot;Protected field: &quot; + obj.protectedField);
// ✓ Works
System.out.println(&quot;Public field: &quot; + obj.publicField);
// ✓ Works

// All methods work - same class access
obj.privateMethod(); // ✓ Works
obj.defaultMethod(); // ✓ Works
obj.protectedMethod(); // ✓ Works
obj.publicMethod(); // ✓ Works

// Test internal access method
obj.testInternalAccess();

// Test same package access
SamePackageTest.testAccess();
}
}

// Second class in the SAME package
class SamePackageTest {
public static void testAccess() {
System.out.println(&quot;=== Testing Access from Same Package ===&quot;);
AccessModifierDemo obj = new AccessModifierDemo();

// Field access from same package
// System.out.println(&quot;Private field: &quot; + obj.privateField); // ✗ ERROR - private not
accessible
System.out.println(&quot;Default field: &quot; + obj.defaultField);
// ✓ Works - package access
System.out.println(&quot;Protected field: &quot; + obj.protectedField);
// ✓ Works - package access
System.out.println(&quot;Public field: &quot; + obj.publicField);
// ✓ Works - public access

// Method access from same package
// obj.privateMethod(); // ✗ ERROR - private not accessible
obj.defaultMethod(); // ✓ Works - package access
obj.protectedMethod(); // ✓ Works - package access
obj.publicMethod(); // ✓ Works - public access
}
}