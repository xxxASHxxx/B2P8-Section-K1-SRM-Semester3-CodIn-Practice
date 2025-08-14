import java.util.Scanner;


public class CustomStringSplitter {


    // Method to find length without using length()
    public static int findLengthWithoutLengthMethod(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count); // Accessing character at index
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Stop counting when out of bounds
        }
        return count;
    }


    // Method to split string without using built-in split()
    public static String[] customSplit(String text) {
        int length = findLengthWithoutLengthMethod(text);


        // Step 1: Count words (words = spaces + 1)
        int wordCount = 1;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                wordCount++;
            }
        }


        // Step 2: Store space indexes
        int[] spaceIndexes = new int[wordCount - 1];
        int indexCount = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[indexCount++] = i;
            }
        }


        // Step 3: Create words array
        String[] words = new String[wordCount];
        int start = 0;
        int spaceIndexIdx = 0;


        for (int w = 0; w < wordCount; w++) {
            int end;
            if (w < wordCount - 1) {
                end = spaceIndexes[spaceIndexIdx++];
            } else {
                end = length;
            }


            // Extract characters for the word
            String word = "";
            for (int i = start; i < end; i++) {
                word += text.charAt(i);
            }


            words[w] = word;
            start = end + 1; // Move past space
        }


        return words;
    }


    // Method to compare two string arrays
    public static boolean compareStringArrays(String[] arr1, String[] arr2) {
        if (arr1 == null || arr2 == null) return false;
        if (arr1.length != arr2.length) return false;


        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Take full line input
        System.out.print("Enter text: ");
        String input = sc.nextLine();


        // Custom split
        String[] customWords = customSplit(input);


        // Built-in split
        String[] builtInWords = input.split(" ");


        // Compare arrays
        boolean isEqual = compareStringArrays(customWords, builtInWords);


        // Display results
        System.out.println("Custom split result:");
        for (String word : customWords) {
            System.out.println(word);
        }


        System.out.println("\nBuilt-in split result:");
        for (String word : builtInWords) {
            System.out.println(word);
        }


        System.out.println("\nDo both methods produce the same result? " + isEqual);


        sc.close();
    }
}


