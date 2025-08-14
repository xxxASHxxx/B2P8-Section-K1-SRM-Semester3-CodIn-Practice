import java.util.Scanner;


public class ShortestAndLongestWordFinder {


    // Method to find length of String without using length()
    public static int findLengthWithoutLengthMethod(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // End of string reached
        }
        return count;
    }


    // Method to split text into words without using split()
    public static String[] customSplit(String text) {
        int length = findLengthWithoutLengthMethod(text);


        // Count words, handling multiple spaces
        int wordCount = 0;
        boolean inWord = false;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) != ' ' && !inWord) {
                inWord = true;
                wordCount++;
            } else if (text.charAt(i) == ' ') {
                inWord = false;
            }
        }


        // Extract words
        String[] words = new String[wordCount];
        int start = -1, index = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) != ' ' && start == -1) {
                start = i;
            }
            if ((text.charAt(i) == ' ' || i == length - 1) && start != -1) {
                int end = (i == length - 1 && text.charAt(i) != ' ') ? i + 1 : i;
                String word = "";
                for (int j = start; j < end; j++) {
                    word += text.charAt(j);
                }
                words[index++] = word;
                start = -1;
            }
        }
        return words;
    }


    // Method to create 2D array [word, length_as_String]
    public static String[][] getWordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            int len = findLengthWithoutLengthMethod(words[i]);
            result[i][0] = words[i];
            result[i][1] = String.valueOf(len);
        }
        return result;
    }


    // Method to find shortest and longest word lengths
    public static int[] findShortestAndLongest(String[][] wordsWithLengths) {
        int shortestLen = Integer.MAX_VALUE;
        int longestLen = Integer.MIN_VALUE;


        for (int i = 0; i < wordsWithLengths.length; i++) {
            int len = Integer.parseInt(wordsWithLengths[i][1]);
            if (len < shortestLen) {
                shortestLen = len;
            }
            if (len > longestLen) {
                longestLen = len;
            }
        }


        return new int[] {shortestLen, longestLen};
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Take input
        System.out.print("Enter text: ");
        String input = sc.nextLine();


        // Step 1: Get words
        String[] words = customSplit(input);


        // Step 2: Get words with lengths
        String[][] wordsWithLengths = getWordsWithLengths(words);


        // Step 3: Find shortest and longest
        int[] shortLong = findShortestAndLongest(wordsWithLengths);


        // Display table
        System.out.println("\nWord\t\tLength");
        System.out.println("-----------------------");
        for (String[] row : wordsWithLengths) {
            System.out.println(row[0] + "\t\t" + Integer.parseInt(row[1]));
        }


        // Display results
        System.out.println("\nShortest word length: " + shortLong[0]);
        System.out.println("Longest word length: " + shortLong[1]);


        sc.close();
    }
}


