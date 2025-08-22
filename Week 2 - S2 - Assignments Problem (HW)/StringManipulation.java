import java.util.Scanner;


public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();


        String trimmed = input.trim();
        System.out.println("Trimmed: " + trimmed);


        String replaced = trimmed.replace(' ', '_');
        System.out.println("With underscores: " + replaced);


        String noDigits = "";
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                noDigits += c;
            }
        }
        System.out.println("Without digits: " + noDigits);


        String[] words = trimmed.split(" ");
        System.out.println("Words in sentence:");
        for (String word : words) {
            if (!word.equals("")) {
                System.out.println(word);
            }
        }


        String joined = "";
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                joined += words[i];
                if (i < words.length - 1) {
                    joined += " | ";
                }
            }
        }
        System.out.println("Joined with | : " + joined);


        String noPunct = removePunctuation(trimmed);
        System.out.println("Without punctuation: " + noPunct);


        String capitalized = capitalizeWords(noPunct);
        System.out.println("Capitalized: " + capitalized);


        String reversedOrder = reverseWordOrder(noPunct);
        System.out.println("Reversed word order: " + reversedOrder);


        System.out.println("Word Frequency:");
        countWordFrequency(noPunct);


        scanner.close();
    }


    public static String removePunctuation(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ') {
                result += c;
            }
        }
        return result;
    }


    public static String capitalizeWords(String text) {
        String[] words = text.split(" ");
        String result = "";
        for (String word : words) {
            if (!word.equals("")) {
                result += Character.toUpperCase(word.charAt(0));
                if (word.length() > 1) {
                    result += word.substring(1).toLowerCase();
                }
                result += " ";
            }
        }
        return result.trim();
    }


    public static String reverseWordOrder(String text) {
        String[] words = text.split(" ");
        String result = "";
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].equals("")) {
                result += words[i] + " ";
            }
        }
        return result.trim();
    }


    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split(" ");
        boolean[] visited = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("") && !visited[i]) {
                int count = 1;
                for (int j = i + 1; j < words.length; j++) {
                    if (words[i].equals(words[j])) {
                        count++;
                        visited[j] = true;
                    }
                }
                System.out.println(words[i] + " : " + count);
            }
        }
    }
}


