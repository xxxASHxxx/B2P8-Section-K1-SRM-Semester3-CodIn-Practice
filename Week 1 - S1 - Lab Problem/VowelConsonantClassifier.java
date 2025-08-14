import java.util.Scanner;


public class VowelConsonantClassifier {


    // a. Method to check if a character is vowel / consonant / not a letter
    public static String checkCharacterType(char ch) {
        // Convert uppercase to lowercase using ASCII values
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32); // Convert to lowercase
        }


        // Check if it's a letter
        if (ch >= 'a' && ch <= 'z') {
            // Check vowels
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        } else {
            return "Not a Letter";
        }
    }


    // b. Method to classify characters and return 2D array [char, type]
    public static String[][] classifyCharacters(String str) {
        // Find string length without length()
        int len = 0;
        try {
            while (true) {
                str.charAt(len);
                len++;
            }
        } catch (StringIndexOutOfBoundsException e) { }


        String[][] result = new String[len][2];
        for (int i = 0; i < len; i++) {
            char currentChar = str.charAt(i);
            result[i][0] = String.valueOf(currentChar);
            result[i][1] = checkCharacterType(currentChar);
        }
        return result;
    }


    // c. Method to display 2D array in tabular format
    public static void display2DArray(String[][] data) {
        System.out.println("\nCharacter\tType");
        System.out.println("---------------------------");
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i][0] + "\t\t" + data[i][1]);
        }
    }


    // d. Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter a string: ");
        String input = sc.nextLine();


        // Classify characters
        String[][] classifiedData = classifyCharacters(input);


        // Display results
        display2DArray(classifiedData);


        sc.close();
    }
}


