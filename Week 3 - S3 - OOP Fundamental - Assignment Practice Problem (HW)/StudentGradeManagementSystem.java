import java.util.*;
import java.util.stream.*;


public class StudentGradeManagementSystem {


    // Subject class
    static class Subject {
        private String subjectCode;
        private String subjectName;
        private int credits;
        private String instructor;


        public Subject(String subjectCode, String subjectName, int credits, String instructor) {
            this.subjectCode = subjectCode;
            this.subjectName = subjectName;
            this.credits = credits;
            this.instructor = instructor;
        }


        public String getSubjectCode() { return subjectCode; }
        public String getSubjectName() { return subjectName; }
        public int getCredits() { return credits; }
        public String getInstructor() { return instructor; }
    }


    // Student class
    static class Student {
        private String studentId;
        private String studentName;
        private String className;
        private String[] subjects;
        private double[][] marks; // rows: subjects, columns: exams/tests
        private double gpa;
       
        // Static variables shared by all students
        private static int totalStudents = 0;
        private static String schoolName = "Generic High School";
        private static String[] gradingScale = {"A", "B", "C", "D", "F"};
        private static double passPercentage = 50.0;


        public Student(String studentId, String studentName, String className, String[] subjects, int examsPerSubject) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.className = className;
            this.subjects = subjects;
            this.marks = new double[subjects.length][examsPerSubject];
            totalStudents++;
        }


        public void addMarks(String subject, int examIndex, double mark) {
            for (int i = 0; i < subjects.length; i++) {
                if (subjects[i].equalsIgnoreCase(subject)) {
                    if (examIndex < marks[i].length) {
                        marks[i][examIndex] = mark;
                        System.out.println("Added mark " + mark + " for " + subject + " exam #" + (examIndex+1));
                    } else {
                        System.out.println("Invalid exam index for subject " + subject);
                    }
                    return;
                }
            }
            System.out.println("Subject not found: " + subject);
        }


        // Calculate average per subject
        public double[] calculateSubjectAverages() {
            double[] averages = new double[subjects.length];
            for (int i = 0; i < subjects.length; i++) {
                double sum = 0.0;
                int count = 0;
                for (double mark : marks[i]) {
                    if (mark >= 0) { // assume valid marks are >= 0
                        sum += mark;
                        count++;
                    }
                }
                averages[i] = count == 0 ? 0 : sum / count;
            }
            return averages;
        }


        // GPA calculation: weighted average based on credits of each subject (simplified: equal credits)
        public void calculateGPA() {
            double[] averages = calculateSubjectAverages();
            double total = 0.0;
            int count = 0;
            for (double avg : averages) {
                total += avg;
                count++;
            }
            gpa = count == 0 ? 0 : total / count;
        }


        // Grade for percentage
        private String getGrade(double percentage) {
            if (percentage >= 90) return "A";
            else if (percentage >= 80) return "B";
            else if (percentage >= 70) return "C";
            else if (percentage >= passPercentage) return "D";
            else return "F";
        }


        // Generate report card
        public void generateReportCard() {
            System.out.println("\nREPORT CARD for " + studentName + " (" + studentId + ")");
            System.out.println("Class: " + className);
            double[] averages = calculateSubjectAverages();
            for (int i = 0; i < subjects.length; i++) {
                String grade = getGrade(averages[i]);
                System.out.printf("%-15s : Average: %.2f %%, Grade: %s%n", subjects[i], averages[i], grade);
            }
            calculateGPA();
            System.out.printf("GPA: %.2f%n", gpa);
        }


        // Check promotion eligibility
        public boolean checkPromotionEligibility() {
            double[] averages = calculateSubjectAverages();
            for (double avg : averages) {
                if (avg < passPercentage) {
                    return false;
                }
            }
            return true;
        }


        public String getClassName() {
            return className;
        }


        public double getGPA() {
            return gpa;
        }


        public String getStudentName() {
            return studentName;
        }


        // Static setters and getters for shared properties
        public static void setGradingScale(String[] scale) {
            gradingScale = scale;
        }


        public static int getTotalStudents() {
            return totalStudents;
        }


        public static void setSchoolName(String name) {
            schoolName = name;
        }


        public static String getSchoolName() {
            return schoolName;
        }
    }


    // Static method to calculate class average GPA
    public static double calculateClassAverage(Student[] students, String className) {
        double totalGPA = 0;
        int count = 0;
        for (Student s : students) {
            if (s != null && s.getClassName().equalsIgnoreCase(className)) {
                totalGPA += s.getGPA();
                count++;
            }
        }
        return count == 0 ? 0 : totalGPA / count;
    }


    // Static method to get top performers in school
    public static List<Student> getTopPerformers(Student[] students, int count) {
        return Arrays.stream(students)
                .filter(Objects::nonNull)
                .sorted((s1, s2) -> Double.compare(s2.getGPA(), s1.getGPA()))
                .limit(count)
                .collect(Collectors.toList());
    }


    // Static method to generate school report for all students
    public static void generateSchoolReport(Student[] students) {
        System.out.println("\nSCHOOL REPORT for " + Student.getSchoolName());
        Map<String, List<Student>> classMap = new HashMap<>();
        for (Student s : students) {
            if (s != null) {
                classMap.computeIfAbsent(s.getClassName(), k -> new ArrayList<>()).add(s);
            }
        }
        classMap.forEach((className, studentList) -> {
            System.out.println("\nClass: " + className);
            double average = studentList.stream().mapToDouble(Student::getGPA).average().orElse(0);
            System.out.printf("Class Average GPA: %.2f%n", average);
            studentList.forEach(Student::generateReportCard);
        });
    }


    public static void main(String[] args) {
        // Create subject list for students (same subjects for this example)
        String[] subjects = {"Math", "English", "Science", "History"};


        // Create students
        Student s1 = new Student("S001", "Alice", "10A", subjects, 3);
        Student s2 = new Student("S002", "Bob", "10A", subjects, 3);
        Student s3 = new Student("S003", "Charlie", "10B", subjects, 3);


        // Add marks for students (subject, examIndex, marks)
        s1.addMarks("Math", 0, 85);
        s1.addMarks("English", 0, 78);
        s1.addMarks("Science", 0, 92);
        s1.addMarks("History", 0, 88);
        s1.addMarks("Math", 1, 89);
        s1.addMarks("English", 1, 82);
        s1.addMarks("Science", 1, 94);
        s1.addMarks("History", 1, 90);
        s1.addMarks("Math", 2, 87);


        s2.addMarks("Math", 0, 70);
        s2.addMarks("English", 0, 75);
        s2.addMarks("Science", 0, 80);
        s2.addMarks("History", 0, 73);
        s2.addMarks("Math", 1, 77);
        s2.addMarks("English", 1, 80);
        s2.addMarks("Science", 1, 85);
        s2.addMarks("History", 1, 79);
        s2.addMarks("Math", 2, 90);


        s3.addMarks("Math", 0, 90);
        s3.addMarks("English", 0, 88);
        s3.addMarks("Science", 0, 93);
        s3.addMarks("History", 0, 85);
        s3.addMarks("Math", 1, 91);
        s3.addMarks("English", 1, 87);
        s3.addMarks("Science", 1, 89);
        s3.addMarks("History", 1, 92);
        s3.addMarks("Math", 2, 88);


        // Calculate GPA for all students
        s1.calculateGPA();
        s2.calculateGPA();
        s3.calculateGPA();


        // Array of students
        Student[] allStudents = {s1, s2, s3};


        // Generate individual report cards
        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();


        // Check promotion eligibility
        System.out.println("\nPromotion Eligibility:");
        for (Student s : allStudents) {
            System.out.println(s.studentName + ": " +
                (s.checkPromotionEligibility() ? "Eligible" : "Not Eligible"));
        }


        // Calculate and display average GPA of class 10A
        double avg10A = calculateClassAverage(allStudents, "10A");
        System.out.printf("\nClass 10A Average GPA: %.2f\n", avg10A);


        // Get and display top 2 performers
        List<Student> topPerformers = getTopPerformers(allStudents, 2);
        System.out.println("\nTop Performers:");
        topPerformers.forEach(s ->
            System.out.println(s.studentName + " - GPA: " + String.format("%.2f", s.getGPA())));


        // Generate overall school report
        generateSchoolReport(allStudents);
    }
}
