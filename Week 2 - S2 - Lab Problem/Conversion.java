package w2lab;


import java.util.Scanner;


public class Conversion{
    public static char toUpperCase(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char)(ch - 32);
        }
        return ch;
    }


    public static char toLowerCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch + 32);
        }
        return ch;
    }


    public static String convertToUpper(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            sb.append(toUpperCase(ch));
        }
        return sb.toString();
    }


    public static String convertToLower(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            sb.append(toLowerCase(ch));
        }
        return sb.toString();
    }


    public static String convertToTitleCase(String str) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (char ch : str.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                sb.append(ch);
                newWord = true;
            } else if (newWord) {
                sb.append(toUpperCase(ch));
                newWord = false;
            } else {
                sb.append(toLowerCase(ch));
            }
        }
        return sb.toString();
    }


    public static void compareWithBuiltIn(String input) {
        System.out.printf("%-20s %-20s %-20s\n", "Conversion", "Manual", "Built-in");
        String upperManual = convertToUpper(input);
        String upperBuiltIn = input.toUpperCase();
        System.out.printf("%-20s %-20s %-20s\n", "Uppercase", upperManual, upperBuiltIn);


        String lowerManual = convertToLower(input);
        String lowerBuiltIn = input.toLowerCase();
        System.out.printf("%-20s %-20s %-20s\n", "Lowercase", lowerManual, lowerBuiltIn);


        String titleManual = convertToTitleCase(input);
        System.out.printf("%-20s %-20s %-20s\n", "Title Case", titleManual, titleManual);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();


        System.out.println("\nResults:");
        compareWithBuiltIn(input);
    }
}
