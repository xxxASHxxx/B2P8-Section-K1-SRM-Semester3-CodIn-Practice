import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeBean implements Serializable {
private static final long serialVersionUID = 1L;

// Private fields following JavaBean conventions
private String employeeId;
private String firstName;
private String lastName;
private double salary;
private String department;
private Date hireDate;
private boolean isActive;

// Default no-argument constructor (JavaBean requirement)
public EmployeeBean() {
// Initialize with default values
this.isActive = true;
this.hireDate = new Date();
}

// Parameterized constructor for convenience
public EmployeeBean(String employeeId, String firstName, String
lastName,
double salary, String department, Date hireDate,
boolean isActive) {
this.employeeId = employeeId;
this.firstName = firstName;
this.lastName = lastName;
this.salary = salary;
this.department = department;
this.hireDate = hireDate != null ? new Date(hireDate.getTime())
: new Date();
this.isActive = isActive;
}

// ==================== STANDARD JAVABEAN GETTERS
====================

public String getEmployeeId() {
return employeeId;
}

public String getFirstName() {
return firstName;
}

public String getLastName() {
return lastName;
}

public double getSalary() {
return salary;
}

public String getDepartment() {
return department;
}

public Date getHireDate() {
return hireDate != null ? new Date(hireDate.getTime()) : null;
}

// For boolean: isActive() instead of getIsActive() per JavaBean
convention

public boolean isActive() {
return isActive;
}

// ==================== STANDARD JAVABEAN SETTERS
====================

public void setEmployeeId(String employeeId) {
this.employeeId = employeeId;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

// Setter with validation
public void setSalary(double salary) {
if (salary &lt; 0) {
throw new IllegalArgumentException(&quot;Salary cannot be
negative&quot;);
}
this.salary = salary;
}

public void setDepartment(String department) {
this.department = department;

}

public void setHireDate(Date hireDate) {
this.hireDate = hireDate != null ? new Date(hireDate.getTime())
: null;
}

public void setActive(boolean active) {
this.isActive = active;
}

// ==================== COMPUTED PROPERTIES ====================

// Computed property: getters without corresponding fields
public String getFullName() {
if (firstName == null &amp;&amp; lastName == null) return &quot;&quot;;
if (firstName == null) return lastName;
if (lastName == null) return firstName;
return firstName + &quot; &quot; + lastName;
}

public long getYearsOfService() {
if (hireDate == null) return 0;
LocalDate hire =
hireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
LocalDate now = LocalDate.now();
return ChronoUnit.YEARS.between(hire, now);
}

public String getFormattedSalary() {

DecimalFormat formatter = new DecimalFormat(&quot;$#,##0.00&quot;);
return formatter.format(salary);
}

// ==================== DERIVED PROPERTIES WITH VALIDATION
====================

// Derived property: splits fullName into firstName/lastName
public void setFullName(String fullName) {
if (fullName == null || fullName.trim().isEmpty()) {
this.firstName = &quot;&quot;;
this.lastName = &quot;&quot;;
return;
}

String[] parts = fullName.trim().split(&quot;\\s+&quot;, 2);
this.firstName = parts[0];
this.lastName = parts.length &gt; 1 ? parts[1] : &quot;&quot;;
}

// ==================== OBJECT METHODS ====================

@Override
public String toString() {
SimpleDateFormat dateFormat = new SimpleDateFormat(&quot;yyyy-MM-
dd&quot;);
return &quot;EmployeeBean{&quot; +
&quot;employeeId=&#39;&quot; + employeeId + &#39;\&#39;&#39; +
&quot;, fullName=&#39;&quot; + getFullName() + &#39;\&#39;&#39; +
&quot;, salary=&quot; + getFormattedSalary() +

&quot;, department=&#39;&quot; + department + &#39;\&#39;&#39; +
&quot;, hireDate=&quot; + (hireDate != null ?
dateFormat.format(hireDate) : &quot;null&quot;) +
&quot;, yearsOfService=&quot; + getYearsOfService() +
&quot;, isActive=&quot; + isActive +
&#39;}&#39;;
}

@Override
public boolean equals(Object obj) {
if (this == obj) return true;
if (obj == null || getClass() != obj.getClass()) return false;
EmployeeBean that = (EmployeeBean) obj;
return Objects.equals(employeeId, that.employeeId);
}

@Override
public int hashCode() {
return Objects.hash(employeeId);
}

// ==================== MAIN METHOD FOR TESTING
====================

public static void main(String[] args) {
System.out.println(&quot;=== JAVABEAN STANDARDS IMPLEMENTATION DEMO
===\n&quot;);

// Create EmployeeBean using default constructor + setters

System.out.println(&quot;1. Creating employee using default
constructor + setters:&quot;);
EmployeeBean emp1 = new EmployeeBean();
emp1.setEmployeeId(&quot;EMP001&quot;);
emp1.setFirstName(&quot;John&quot;);
emp1.setLastName(&quot;Doe&quot;);
emp1.setSalary(75000.0);
emp1.setDepartment(&quot;Engineering&quot;);
emp1.setHireDate(new Date(System.currentTimeMillis() - (365L *
24 * 60 * 60 * 1000 * 2))); // 2 years ago
emp1.setActive(true);

// Create EmployeeBean using parameterized constructor
System.out.println(&quot;2. Creating employee using parameterized
constructor:&quot;);
EmployeeBean emp2 = new EmployeeBean(
&quot;EMP002&quot;, &quot;Jane&quot;, &quot;Smith&quot;, 85000.0, &quot;Marketing&quot;,
new Date(System.currentTimeMillis() - (365L * 24 * 60 * 60
* 1000 * 3)), // 3 years ago
true
);

// Demonstrate all getter methods
System.out.println(&quot;\n3. Demonstrating all getter methods:&quot;);
System.out.println(&quot;Employee 1 ID: &quot; + emp1.getEmployeeId());
System.out.println(&quot;Employee 1 First Name: &quot; +
emp1.getFirstName());
System.out.println(&quot;Employee 1 Last Name: &quot; +
emp1.getLastName());
System.out.println(&quot;Employee 1 Salary: &quot; + emp1.getSalary());

System.out.println(&quot;Employee 1 Department: &quot; +
emp1.getDepartment());
System.out.println(&quot;Employee 1 Hire Date: &quot; +
emp1.getHireDate());
System.out.println(&quot;Employee 1 Is Active: &quot; + emp1.isActive());

// Test computed properties
System.out.println(&quot;\n4. Testing computed properties:&quot;);
System.out.println(&quot;Employee 1 Full Name: &quot; +
emp1.getFullName());
System.out.println(&quot;Employee 1 Years of Service: &quot; +
emp1.getYearsOfService());
System.out.println(&quot;Employee 1 Formatted Salary: &quot; +
emp1.getFormattedSalary());

// Test validation in setter methods
System.out.println(&quot;\n5. Testing validation in setter
methods:&quot;);
try {
emp1.setSalary(-1000); // Should throw exception
} catch (IllegalArgumentException e) {
System.out.println(&quot;✓ Salary validation working: &quot; +
e.getMessage());
}

// Test derived property setFullName
System.out.println(&quot;\n6. Testing derived property
setFullName:&quot;);
emp1.setFullName(&quot;Michael Johnson&quot;);
System.out.println(&quot;After setFullName(&#39;Michael Johnson&#39;):&quot;);
System.out.println(&quot;First Name: &quot; + emp1.getFirstName());

System.out.println(&quot;Last Name: &quot; + emp1.getLastName());
System.out.println(&quot;Full Name: &quot; + emp1.getFullName());

// Create array of EmployeeBeans for bulk operations
System.out.println(&quot;\n7. JavaBean in action with
collections:&quot;);
EmployeeBean[] employees = {
new EmployeeBean(&quot;EMP003&quot;, &quot;Alice&quot;, &quot;Brown&quot;, 65000, &quot;HR&quot;,
new Date(System.currentTimeMillis() - (365L
* 24 * 60 * 60 * 1000)), true),
new EmployeeBean(&quot;EMP004&quot;, &quot;Bob&quot;, &quot;Wilson&quot;, 95000,
&quot;Engineering&quot;,
new Date(System.currentTimeMillis() - (365L
* 24 * 60 * 60 * 1000 * 5)), true),
new EmployeeBean(&quot;EMP005&quot;, &quot;Carol&quot;, &quot;Davis&quot;, 45000,
&quot;Sales&quot;,
new Date(System.currentTimeMillis() - (365L
* 24 * 60 * 60 * 1000 / 2)), false),
emp1, emp2
};

// Convert to List for easier manipulation
List&lt;EmployeeBean&gt; empList = new
ArrayList&lt;&gt;(Arrays.asList(employees));

// Sorting by salary using computed properties
System.out.println(&quot;\n a) Sorting by salary (descending):&quot;);
Collections.sort(empList, new Comparator&lt;EmployeeBean&gt;() {
public int compare(EmployeeBean e1, EmployeeBean e2) {
return Double.compare(e2.getSalary(), e1.getSalary());
}

});

for (EmployeeBean emp : empList) {
System.out.println(&quot; &quot; + emp.getFullName() + &quot; - &quot; +
emp.getFormattedSalary());
}

// Filtering active employees
System.out.println(&quot;\n b) Filtering active employees:&quot;);
List&lt;EmployeeBean&gt; activeEmployees = new ArrayList&lt;&gt;();
for (EmployeeBean emp : empList) {
if (emp.isActive()) {
activeEmployees.add(emp);
}
}

System.out.println(&quot; Active employees count: &quot; +
activeEmployees.size());
for (EmployeeBean emp : activeEmployees) {
System.out.println(&quot; &quot; + emp.getFullName() + &quot; (&quot; +
emp.getDepartment() + &quot;)&quot;);
}

// Bulk operations using JavaBean conventions
System.out.println(&quot;\n c) Bulk salary increase for
Engineering:&quot;);
for (EmployeeBean emp : empList) {
if (&quot;Engineering&quot;.equals(emp.getDepartment()) &amp;&amp;
emp.isActive()) {
double newSalary = emp.getSalary() * 1.10; // 10%
increase

emp.setSalary(newSalary);
System.out.println(&quot; &quot; + emp.getFullName() + &quot; salary
increased to &quot; +
emp.getFormattedSalary());
}
}

// Demonstrate JavaBean utility class
System.out.println(&quot;\n8. JavaBean utility class
demonstration:&quot;);
JavaBeanProcessor.printAllProperties(emp1);

System.out.println(&quot;\n9. Property copying demonstration:&quot;);
EmployeeBean empCopy = new EmployeeBean();
JavaBeanProcessor.copyProperties(emp1, empCopy);
System.out.println(&quot;Original: &quot; + emp1);
System.out.println(&quot;Copy: &quot; + empCopy);
}
}

// ==================== JAVABEAN UTILITY CLASS ====================

class JavaBeanProcessor {

// Static method to print all properties using reflection
public static void printAllProperties(EmployeeBean emp) {
System.out.println(&quot;=== All Properties via Reflection ===&quot;);

Class&lt;?&gt; clazz = emp.getClass();
Method[] methods = clazz.getMethods();

for (Method method : methods) {
String methodName = method.getName();

// Check if it&#39;s a getter method (starts with &#39;get&#39; or
&#39;is&#39;, no parameters, returns value)
if (isGetterMethod(method)) {
try {
Object value = method.invoke(emp);
String propertyName = getPropertyName(methodName);
System.out.println(propertyName + &quot;: &quot; + value);
} catch (IllegalAccessException |
InvocationTargetException e) {
System.out.println(&quot;Error accessing &quot; + methodName
+ &quot;: &quot; + e.getMessage());
}
}
}
}

// Copy properties from source to target using reflection
public static void copyProperties(EmployeeBean source, EmployeeBean
target) {
Class&lt;?&gt; clazz = EmployeeBean.class;
Method[] methods = clazz.getMethods();

// Create maps of getters and setters
for (Method method : methods) {
if (isGetterMethod(method)) {
try {

// Get the property value from source
Object value = method.invoke(source);

// Find corresponding setter
String propertyName =
getPropertyName(method.getName());
String setterName = &quot;set&quot; +
capitalize(propertyName);

// Handle special case for boolean property
if (method.getName().startsWith(&quot;is&quot;)) {
setterName = &quot;set&quot; +
capitalize(method.getName().substring(2));
}

try {
Method setter = clazz.getMethod(setterName,
method.getReturnType());
setter.invoke(target, value);
} catch (NoSuchMethodException e) {
// Skip if no corresponding setter found
System.out.println(&quot;No setter found for
property: &quot; + propertyName);
}

} catch (IllegalAccessException |
InvocationTargetException e) {
System.out.println(&quot;Error copying property: &quot; +
e.getMessage());
}
}

}

System.out.println(&quot;✓ Properties copied successfully using reflection&quot;);
}

// Helper method to check if a method is a getter
private static boolean isGetterMethod(Method method) {
String name = method.getName();
Class&lt;?&gt; returnType = method.getReturnType();
Class&lt;?&gt;[] paramTypes = method.getParameterTypes();

// Must have no parameters and return a value
if (paramTypes.length != 0 || returnType == void.class) {
return false;
}

// Must start with &#39;get&#39; or &#39;is&#39; (for boolean)
if (name.startsWith(&quot;get&quot;) &amp;&amp; name.length() &gt; 3) {
return true;
}

if (name.startsWith(&quot;is&quot;) &amp;&amp; name.length() &gt; 2 &amp;&amp;
(returnType == boolean.class || returnType ==
Boolean.class)) {
return true;
}

return false;
}

// Helper method to extract property name from getter method name
private static String getPropertyName(String methodName) {
if (methodName.startsWith(&quot;get&quot;)) {
return decapitalize(methodName.substring(3));
} else if (methodName.startsWith(&quot;is&quot;)) {
return decapitalize(methodName.substring(2));
}
return methodName;
}

// Helper method to capitalize first letter
private static String capitalize(String str) {
if (str == null || str.isEmpty()) return str;
return str.substring(0, 1).toUpperCase() + str.substring(1);
}

// Helper method to decapitalize first letter
private static String decapitalize(String str) {
if (str == null || str.isEmpty()) return str;
return str.substring(0, 1).toLowerCase() + str.substring(1);
}
}
