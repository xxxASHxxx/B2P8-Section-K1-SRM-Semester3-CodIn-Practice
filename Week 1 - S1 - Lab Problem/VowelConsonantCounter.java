import java.util.Scanner;


public class 
 {


    // Method to check if a character is vowel or consonant
    public static String checkCharacterType(char ch) {
        // Check if uppercase letter and convert to lowercase using ASCII values
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32); // Convert to lowercase
        }


        // Check if it's a letter
        if (ch >= 'a' && ch <= 'z') {
            // Check for vowels
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        } else {
            return "Not a Letter";
        }
    }


    // Method to count vowels and consonants in a string
    public static int[] countVowelsAndConsonants(String str) {
        int vowelCount = 0, consonantCount = 0;


        int length = 0;
        try {
            while (true) {
                str.charAt(length); // count length manually
                length++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // reached end
        }


        for (int i = 0; i < length; i++) {
            String type = checkCharacterType(str.charAt(i));
            if (type.equals("Vowel")) {
                vowelCount++;
            } else if (type.equals("Consonant")) {
                consonantCount++;
            }
        }


        return new int[]{vowelCount, consonantCount}; // index 0 = vowels, index 1 = consonants
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Take user input
        System.out.print("Enter a string: ");
        String input = sc.nextLine();


        // Get counts
        int[] counts = countVowelsAndConsonants(input);


        // Display results
        System.out.println("Number of vowels: " + counts[0]);
        System.out.println("Number of consonants: " + counts[1]);


        sc.close();
    }
}
