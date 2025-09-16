package com.company.main;

// Import the AccessModifierDemo class from com.company.security

import com.company.security.AccessModifierDemo;

public class PackageTestMain {
public static void main(String[] args) {
System.out.println(&quot;=== Testing Cross-Package Access ===&quot;);

// Create AccessModifierDemo object
AccessModifierDemo obj = new AccessModifierDemo();

// Attempt to access each field and method
// System.out.println(&quot;Private field: &quot; + obj.privateField); // ✗ COMPILE ERROR
// System.out.println(&quot;Default field: &quot; + obj.defaultField); // ✗ COMPILE ERROR
// System.out.println(&quot;Protected field: &quot; + obj.protectedField); // ✗ COMPILE ERROR
System.out.println(&quot;Public field: &quot; + obj.publicField);
// ✓ Works - public access

// Method access attempts
// obj.privateMethod(); // ✗ COMPILE ERROR - private not accessible
// obj.defaultMethod(); // ✗ COMPILE ERROR - package-private
not accessible
// obj.protectedMethod(); // ✗ COMPILE ERROR - protected not accessible
(not a subclass)
obj.publicMethod(); // ✓ Works - public access

// This works because it&#39;s a public method
obj.testInternalAccess();

/*
* EXPLANATION OF ACCESS FAILURES:

* - private: Only accessible within the same class
* - default (package-private): Only accessible within the same
package
* - protected: Accessible in same package OR subclasses in
different packages
* - public: Accessible everywhere
*
* Since PackageTestMain is in a different package
(com.company.main)
* and NOT a subclass, only PUBLIC members are accessible.
*/
}
}
package com.company.extended;

// Import AccessModifierDemo
import com.company.security.AccessModifierDemo;

// Create class ExtendedDemo that extends AccessModifierDemo
public class ExtendedDemo extends AccessModifierDemo {

// Constructor that calls super constructor
public ExtendedDemo() {
super(); // Call parent constructor
System.out.println(&quot;ExtendedDemo constructor called&quot;);
}

public void testInheritedAccess() {
System.out.println(&quot;=== Testing Inherited Access in Subclass
(Different Package) ===&quot;);

// Try accessing inherited fields with different modifiers
// System.out.println(&quot;Private field: &quot; + privateField); // ✗ ERROR -
private NOT inherited
// System.out.println(&quot;Default field: &quot; + defaultField); // ✗ ERROR -
package-private not accessible across packages
System.out.println(&quot;Protected field: &quot; + protectedField); //
✓ Works - protected accessible in subclass
System.out.println(&quot;Public field: &quot; + publicField); //
✓ Works - public accessible everywhere

// Try calling inherited methods with different modifiers
// privateMethod(); // ✗ ERROR - private NOT inherited
// defaultMethod(); // ✗ ERROR - package-private not accessible
across packages
protectedMethod(); // ✓ Works - protected accessible in subclass
publicMethod(); // ✓ Works - public accessible everywhere

/*
* KEY INHERITANCE RULES:
* 1. private members are NOT inherited at all
* 2. default (package-private) members are inherited only
within same package
* 3. protected members are inherited and accessible in
subclasses (even different packages)
* 4. public members are inherited and accessible everywhere
*
* IMPORTANT: Protected access in subclass works because we&#39;re
accessing

* through &#39;this&#39; reference (inherited members), not through
parent object reference
*/
}

// Override protected method from parent class
@Override
protected void protectedMethod() {
System.out.println(&quot;Overridden protected method in
ExtendedDemo&quot;);
super.protectedMethod(); // Call parent version
}

// Demonstrate that we CAN access protected members of our own type
public void testProtectedAccess() {
ExtendedDemo another = new ExtendedDemo();

// This works - accessing protected member of same subclass
type
System.out.println(&quot;Protected field of another ExtendedDemo: &quot;
+ another.protectedField);
another.protectedMethod();

// But this would NOT work - can&#39;t access protected members of
parent type
// AccessModifierDemo parent = new AccessModifierDemo();
// System.out.println(parent.protectedField); // ✗ COMPILE ERROR
// parent.protectedMethod(); // ✗ COMPILE ERROR
}

public static void main(String[] args) {
System.out.println(&quot;=== Testing Inheritance Access Rules ===&quot;);

// Create both parent and child objects
AccessModifierDemo parent = new AccessModifierDemo();
ExtendedDemo child = new ExtendedDemo();

System.out.println(&quot;\n--- Parent object access from subclass --
-&quot;);
// Even in a subclass, we cannot access protected members of
parent object
// System.out.println(&quot;Parent protected field: &quot; + parent.protectedField); // ✗ ERROR
System.out.println(&quot;Parent public field: &quot; +
parent.publicField); // ✓ Works

System.out.println(&quot;\n--- Child object access ---&quot;);
child.testInheritedAccess();
child.testProtectedAccess();

/*
* CRITICAL UNDERSTANDING:
* Protected access in different packages works ONLY when:
* 1. Accessing through subclass reference
(this.protectedMember)
* 2. Accessing inherited members directly
*
* Protected access does NOT work when:
* 1. Accessing through parent class reference from subclass
* 2. Accessing from non-subclass in different package
*/
}
}