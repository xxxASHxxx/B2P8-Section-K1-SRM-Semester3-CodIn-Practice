import java.util.Scanner;


public class CSVAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter CSV data (end input with an empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            input.append(line).append("\n");
        }


        String raw = input.toString();
        String[][] data = parseCSV(raw);


        cleanData(data);
        analyzeData(data);
        formatTable(data);
        summaryReport(data);


        sc.close();
    }


    public static String[][] parseCSV(String text) {
        int rows = 1, cols = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') rows++;
            if (text.charAt(i) == ',' && rows == 1) cols++;
        }
        String[][] arr = new String[rows][cols];


        int r = 0, c = 0, start = 0;
        boolean inQuotes = false;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '\"') {
                inQuotes = !inQuotes;
            } else if (ch == ',' && !inQuotes) {
                arr[r][c++] = text.substring(start, i);
                start = i + 1;
            } else if (ch == '\n') {
                arr[r][c] = text.substring(start, i);
                r++;
                c = 0;
                start = i + 1;
            }
        }
        arr[r][c] = text.substring(start).replace("\n", "");
        return arr;
    }


    public static void cleanData(String[][] data) {
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[r].length; c++) {
                if (data[r][c] == null) data[r][c] = "";
                data[r][c] = data[r][c].trim().replaceAll("^\"|\"$", "");
            }
        }
    }


    public static void analyzeData(String[][] data) {
        System.out.println("\n=== DATA ANALYSIS ===");
        int numCols = data[0].length;
        for (int c = 0; c < numCols; c++) {
            boolean numeric = true;
            double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;
            int count = 0, missing = 0;
            for (int r = 1; r < data.length; r++) {
                String val = data[r][c];
                if (val.equals("")) {
                    missing++;
                    continue;
                }
                if (isNumeric(val)) {
                    double d = Double.parseDouble(val);
                    if (d < min) min = d;
                    if (d > max) max = d;
                    sum += d;
                    count++;
                } else {
                    numeric = false;
                }
            }
            System.out.print("Column " + data[0][c] + ": ");
            if (numeric && count > 0) {
                double avg = sum / count;
                System.out.printf("Min=%.2f Max=%.2f Avg=%.2f | Missing=%d\n", min, max, avg, missing);
            } else {
                System.out.println("Categorical column | Missing=" + missing);
            }
        }
    }


    public static void formatTable(String[][] data) {
        System.out.println("\n=== FORMATTED DATA TABLE ===");
        int cols = data[0].length;
        int[] widths = new int[cols];
        for (int c = 0; c < cols; c++) {
            int w = 0;
            for (int r = 0; r < data.length; r++) {
                if (data[r][c].length() > w) w = data[r][c].length();
            }
            widths[c] = w + 2;
        }


        for (int r = 0; r < data.length; r++) {
            StringBuilder sb = new StringBuilder("|");
            for (int c = 0; c < cols; c++) {
                sb.append(" ").append(String.format("%-" + (widths[c] - 1) + "s", data[r][c])).append("|");
            }
            System.out.println(sb.toString());
        }
    }


    public static void summaryReport(String[][] data) {
        System.out.println("\n=== SUMMARY REPORT ===");
        int total = data.length - 1;
        System.out.println("Records processed: " + total);
        int missingCount = 0;
        int totalFields = (data.length - 1) * data[0].length;
        for (int r = 1; r < data.length; r++) {
            for (int c = 0; c < data.length; c++) {
                if (data[r][c].equals("")) missingCount++;
            }
        }
        double completeness = 100.0 * (totalFields - missingCount) / totalFields;
        System.out.printf("Data completeness: %.2f%%\n", completeness);
        System.out.println("Missing fields: " + missingCount);
    }


    public static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!(ch >= '0' && ch <= '9') && ch != '.' && ch != '-') return false;
        }
        return true;
    }
}
