import java.util.Scanner;


public class SpellChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String[] dict = {"hello", "world", "java", "programming", "fun", "challenging", "good", "morning", "night", "day"};


        System.out.println("Enter a sentence: ");
        String sentence = sc.nextLine().toLowerCase();


        String[] words = splitWords(sentence);


        System.out.println("=== SPELL CHECK REPORT ===");
        System.out.printf("%-15s %-15s %-10s %-12s\n", "Word", "Suggestion", "Distance", "Status");
        System.out.println("------------------------------------------------------");


        for (String w : words) {
            if (w.length() == 0) continue;
            String suggestion = findClosest(w, dict);
            int dist = distance(w, suggestion);


            if (w.equals(suggestion)) {
                System.out.printf("%-15s %-15s %-10d %-12s\n", w, "-", 0, "Correct");
            } else if (dist <= 2) {
                System.out.printf("%-15s %-15s %-10d %-12s\n", w, suggestion, dist, "Misspelled");
            } else {
                System.out.printf("%-15s %-15s %-10s %-12s\n", w, "None", "-", "Unknown");
            }
        }


        sc.close();
    }


    public static String[] splitWords(String text) {
        String[] arr = new String[text.length()];
        int count = 0;
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?') {
                if (i > start) {
                    arr[count++] = text.substring(start, i);
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            arr[count++] = text.substring(start);
        }
        String[] words = new String[count];
        for (int i = 0; i < count; i++) {
            words[i] = arr[i];
        }
        return words;
    }


    public static int distance(String a, String b) {
        if (a.equals(b)) return 0;
        int la = a.length();
        int lb = b.length();
        int diff = Math.abs(la - lb);
        int min = Math.min(la, lb);
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff;
    }


    public static String findClosest(String w, String[] dict) {
        String best = w;
        int minDist = Integer.MAX_VALUE;
        for (String d : dict) {
            int dist = distance(w, d);
            if (dist < minDist) {
                minDist = dist;
                best = d;
            }
        }
        return best;
    }
}
