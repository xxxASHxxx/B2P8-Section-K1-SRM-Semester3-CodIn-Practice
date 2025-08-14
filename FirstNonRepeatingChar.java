import java.util.Scanner;


public class FirstNonRepeatingChar {


    // Method to find the first non-repeating character using charAt()
    static char findFirstNonRepeating(String text) {
        int[] freq = new int[256]; // frequency array for ASCII chars


        // First pass: count frequency
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            freq[ch]++;
        }


        // Second pass: find first char with frequency 1
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (freq[ch] == 1) {
                return ch;
            }
        }


        // If no non-repeating character found
        return '\0'; // null character
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Take user input
        System.out.print("Enter a string: ");
        String text = sc.nextLine();


        // Call method
        char result = findFirstNonRepeating(text);


        // Display result
        if (result != '\0')
            System.out.println("First non-repeating character: " + result);
        else
            System.out.println("No non-repeating character found.");


        sc.close();
    }
}


