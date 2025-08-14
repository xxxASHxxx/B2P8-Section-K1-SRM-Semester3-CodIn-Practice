import java.util.Scanner;


public class BMICalculator {


    // Method to read height(cm) and weight(kg) for 10 people
    static double[][] readData(Scanner sc, int n) {
        double[][] hw = new double[n][2];
        for (int i = 0; i < n; i++) {
            System.out.println("Person " + (i + 1));
            System.out.print("Weight (kg): ");
            hw[i][0] = sc.nextDouble();
            System.out.print("Height (cm): ");
            hw[i][1] = sc.nextDouble();
        }
        return hw;
    }


    // Method to compute BMI and status
    static String[] getBmiStatus(double wt, double ht) {
        double m = ht / 100.0;
        double bmi = wt / (m * m);
        String status;
        if (bmi < 18.5) status = "Underweight";
        else if (bmi <= 24.9) status = "Normal";
        else if (bmi <= 34.9) status = "Overweight";
        else status = "Obesity";
        return new String[]{String.format("%.0f", ht), String.format("%.1f", wt),
                            String.format("%.1f", bmi), status};
    }


    // Method to create result table
    static String[][] buildTable(double[][] hw) {
        String[][] res = new String[hw.length][4];
        for (int i = 0; i < hw.length; i++)
            res[i] = getBmiStatus(hw[i][0], hw[i][1]);
        return res;
    }


    // Method to display table
    static void display(String[][] table) {
        System.out.println("Person\tHeight(cm)\tWeight(kg)\tBMI\tStatus");
        for (int i = 0; i < table.length; i++) {
            System.out.println((i+1) + "\t" + table[i][0] + "\t\t" +
                               table[i][1] + "\t\t" +
                               table[i][2] + "\t" + table[i][3]);
        }
    }


    public static void main(String[] args) {
        final int TEAM = 10;
        Scanner sc = new Scanner(System.in);
        double[][] hw = readData(sc, TEAM);
        String[][] result = buildTable(hw);
        display(result);
        sc.close();
    }
}
