public class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
   
    private double hourlyRate;
    private int hoursWorked;
    private double contractAmount;
    private double bonus;
   
    public Employee(String empName, String department, double baseSalary) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        this.bonus = 0.0;
        totalEmployees++;
        System.out.println("Full-time employee created: " + empName);
    }
   
    public Employee(String empName, String department, double hourlyRate, int hoursWorked) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.empType = "Part-Time";
        totalEmployees++;
        System.out.println("Part-time employee created: " + empName);
    }
   
    public Employee(String empName, String department, double contractAmount, String contractType) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.contractAmount = contractAmount;
        this.empType = "Contract";
        totalEmployees++;
        System.out.println("Contract employee created: " + empName);
    }
   
    public double calculateSalary() {
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus;
        }
        return 0.0;
    }
   
    public double calculateSalary(double bonusAmount) {
        if (empType.equals("Full-Time")) {
            this.bonus = bonusAmount;
            return baseSalary + bonusAmount;
        }
        return 0.0;
    }
   
    public double calculateSalary(double hourlyRate, int hours) {
        if (empType.equals("Part-Time")) {
            return hourlyRate * hours;
        }
        return 0.0;
    }
   
    public double calculateSalary(String contractType) {
        if (empType.equals("Contract")) {
            return contractAmount;
        }
        return 0.0;
    }
   
    public double calculateTax() {
        double salary = 0.0;
        if (empType.equals("Full-Time")) {
            salary = calculateSalary();
            return salary * 0.20;
        } else if (empType.equals("Part-Time")) {
            salary = calculateSalary(hourlyRate, hoursWorked);
            return salary * 0.15;
        } else if (empType.equals("Contract")) {
            salary = calculateSalary("contract");
            return salary * 0.10;
        }
        return 0.0;
    }
   
    public double calculateTax(double customRate) {
        double salary = 0.0;
        if (empType.equals("Full-Time")) {
            salary = calculateSalary();
        } else if (empType.equals("Part-Time")) {
            salary = calculateSalary(hourlyRate, hoursWorked);
        } else if (empType.equals("Contract")) {
            salary = calculateSalary("contract");
        }
        return salary * customRate;
    }
   
    public double calculateTax(String empType, double rate) {
        double salary = 0.0;
        if (empType.equals("Full-Time")) {
            salary = calculateSalary();
        } else if (empType.equals("Part-Time")) {
            salary = calculateSalary(hourlyRate, hoursWorked);
        } else if (empType.equals("Contract")) {
            salary = calculateSalary("contract");
        }
        return salary * rate;
    }
   
    public void generatePaySlip() {
        double grossSalary = 0.0;
        double tax = calculateTax();
       
        if (empType.equals("Full-Time")) {
            grossSalary = calculateSalary();
        } else if (empType.equals("Part-Time")) {
            grossSalary = calculateSalary(hourlyRate, hoursWorked);
        } else if (empType.equals("Contract")) {
            grossSalary = calculateSalary("contract");
        }
       
        double netSalary = grossSalary - tax;
       
        System.out.println("=====================================");
        System.out.println("           PAY SLIP");
        System.out.println("=====================================");
        System.out.println("Employee ID: " + empId);
        System.out.println("Employee Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Employee Type: " + empType);
       
        if (empType.equals("Part-Time")) {
            System.out.println("Hourly Rate: $" + hourlyRate);
            System.out.println("Hours Worked: " + hoursWorked);
        } else if (empType.equals("Full-Time") && bonus > 0) {
            System.out.println("Base Salary: $" + baseSalary);
            System.out.println("Bonus: $" + bonus);
        }
       
        System.out.println("Gross Salary: $" + String.format("%.2f", grossSalary));
        System.out.println("Tax Deducted: $" + String.format("%.2f", tax));
        System.out.println("Net Salary: $" + String.format("%.2f", netSalary));
        System.out.println("=====================================");
    }
   
    public void displayEmployeeInfo() {
        System.out.println("Employee Information:");
        System.out.println("ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
       
        if (empType.equals("Full-Time")) {
            System.out.println("Base Salary: $" + baseSalary);
        } else if (empType.equals("Part-Time")) {
            System.out.println("Hourly Rate: $" + hourlyRate);
            System.out.println("Hours Worked: " + hoursWorked);
        } else if (empType.equals("Contract")) {
            System.out.println("Contract Amount: $" + contractAmount);
        }
        System.out.println("-------------------------");
    }
   
    private static String generateEmpId() {
        return "EMP" + String.format("%03d", totalEmployees + 1);
    }
   
    public static int getTotalEmployees() {
        return totalEmployees;
    }
   
    public static void generateCompanyPayrollReport(Employee[] employees) {
        System.out.println("\n========================================");
        System.out.println("      COMPANY PAYROLL REPORT");
        System.out.println("========================================");
        System.out.println("Total Employees: " + totalEmployees);
       
        double totalPayroll = 0.0;
        double totalTax = 0.0;
        int fullTimeCount = 0, partTimeCount = 0, contractCount = 0;
       
        for (Employee emp : employees) {
            if (emp != null) {
                double salary = 0.0;
                if (emp.empType.equals("Full-Time")) {
                    salary = emp.calculateSalary();
                    fullTimeCount++;
                } else if (emp.empType.equals("Part-Time")) {
                    salary = emp.calculateSalary(emp.hourlyRate, emp.hoursWorked);
                    partTimeCount++;
                } else if (emp.empType.equals("Contract")) {
                    salary = emp.calculateSalary("contract");
                    contractCount++;
                }
                totalPayroll += salary;
                totalTax += emp.calculateTax();
            }
        }
       
        System.out.println("Full-Time Employees: " + fullTimeCount);
        System.out.println("Part-Time Employees: " + partTimeCount);
        System.out.println("Contract Employees: " + contractCount);
        System.out.println("Total Gross Payroll: $" + String.format("%.2f", totalPayroll));
        System.out.println("Total Tax Deductions: $" + String.format("%.2f", totalTax));
        System.out.println("Total Net Payroll: $" + String.format("%.2f", (totalPayroll - totalTax)));
        System.out.println("========================================");
    }
   
    public static void main(String[] args) {
        Employee[] company = new Employee[6];
       
        System.out.println("=== Creating Different Employee Types ===");
        company[0] = new Employee("Alice Johnson", "Engineering", 75000.0);
        company[1] = new Employee("Bob Smith", "Marketing", 25.0, 40);
        company[2] = new Employee("Carol Davis", "Consulting", 50000.0, "contract");
        company[3] = new Employee("David Wilson", "Engineering", 80000.0);
        company[4] = new Employee("Eva Martinez", "Sales", 22.0, 35);
        company[5] = new Employee("Frank Miller", "IT Support", 45000.0, "contract");
       
        System.out.println("\n=== Demonstrating Method Overloading - Calculate Salary ===");
        System.out.println("Full-time without bonus: $" + company[0].calculateSalary());
        System.out.println("Full-time with bonus: $" + company[0].calculateSalary(5000.0));
        System.out.println("Part-time salary: $" + company[1].calculateSalary(25.0, 40));
        System.out.println("Contract salary: $" + company[2].calculateSalary("contract"));
       
        System.out.println("\n=== Demonstrating Method Overloading - Calculate Tax ===");
        System.out.println("Standard tax for Alice: $" + String.format("%.2f", company[0].calculateTax()));
        System.out.println("Custom tax rate (25%) for Alice: $" + String.format("%.2f", company[0].calculateTax(0.25)));
        System.out.println("Specific type tax for Bob: $" + String.format("%.2f", company[1].calculateTax("Part-Time", 0.12)));
       
        System.out.println("\n=== Employee Information Display ===");
        for (Employee emp : company) {
            emp.displayEmployeeInfo();
        }
       
        System.out.println("\n=== Generating Pay Slips ===");
        company[0].generatePaySlip();
        company[1].generatePaySlip();
        company[2].generatePaySlip();
       
        System.out.println("\n=== Adding Bonuses and Regenerating Pay Slips ===");
        company[0].calculateSalary(8000.0);
        company[3].calculateSalary(12000.0);
        company[0].generatePaySlip();
        company[3].generatePaySlip();
       
        Employee.generateCompanyPayrollReport(company);
       
        System.out.println("\n=== Method Overloading Summary ===");
        System.out.println("Same method names used with different parameters:");
        System.out.println("- calculateSalary() - no parameters for full-time base");
        System.out.println("- calculateSalary(bonus) - with bonus for full-time");
        System.out.println("- calculateSalary(rate, hours) - for part-time calculation");
        System.out.println("- calculateSalary(contractType) - for contract employees");
        System.out.println("- calculateTax() - standard rates by employee type");
        System.out.println("- calculateTax(customRate) - custom tax rate");
        System.out.println("- calculateTax(empType, rate) - specific type and rate");
    }
}
