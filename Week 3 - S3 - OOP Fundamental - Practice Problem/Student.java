public class Student {
    private String studentId;
    private String name;
    private double grade;
    private String course;
   
    public Student() {
        this.studentId = "";
        this.name = "";
        this.grade = 0.0;
        this.course = "";
    }
   
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }
   
    public String getStudentId() {
        return studentId;
    }
   
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
   
    public String getName() {
        return name;
    }
   
    public void setName(String name) {
        this.name = name;
    }
   
    public double getGrade() {
        return grade;
    }
   
    public void setGrade(double grade) {
        this.grade = grade;
    }
   
    public String getCourse() {
        return course;
    }
   
    public void setCourse(String course) {
        this.course = course;
    }
   
    public String calculateLetterGrade() {
        if (grade >= 90) {
            return "A";
        } else if (grade >= 80) {
            return "B";
        } else if (grade >= 70) {
            return "C";
        } else if (grade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
   
    public void displayStudent() {
        System.out.println("Student Information:");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Numerical Grade: " + grade);
        System.out.println("Letter Grade: " + calculateLetterGrade());
        System.out.println("-------------------");
    }
   
    public static void main(String[] args) {
        Student firstStudent = new Student();
        firstStudent.setStudentId("S2024001");
        firstStudent.setName("Emma Johnson");
        firstStudent.setGrade(87.5);
        firstStudent.setCourse("Computer Science");
       
        Student secondStudent = new Student("S2024002", "Marcus Chen", 92.3, "Mathematics");
       
        System.out.println("=== First Student (created with default constructor) ===");
        firstStudent.displayStudent();
       
        System.out.println("=== Second Student (created with parameterized constructor) ===");
        secondStudent.displayStudent();
       
        System.out.println("=== Testing Getter Methods ===");
        System.out.println("First student's name: " + firstStudent.getName());
        System.out.println("Second student's course: " + secondStudent.getCourse());
       
        System.out.println("\n=== Testing Setter Methods ===");
        firstStudent.setGrade(94.2);
        secondStudent.setName("Marcus Chen-Williams");
       
        System.out.println("After updating grades and names:");
        firstStudent.displayStudent();
        secondStudent.displayStudent();
       
        System.out.println("=== Grade Comparison ===");
        System.out.println(firstStudent.getName() + " has a " + firstStudent.calculateLetterGrade() +
                          " (" + firstStudent.getGrade() + ")");
        System.out.println(secondStudent.getName() + " has a " + secondStudent.calculateLetterGrade() +
                          " (" + secondStudent.getGrade() + ")");
    }
}


