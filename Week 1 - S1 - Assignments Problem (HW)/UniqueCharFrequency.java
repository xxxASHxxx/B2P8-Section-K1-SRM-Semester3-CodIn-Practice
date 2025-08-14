import java.util.Scanner;


public class UniqueCharFrequency {


    // a) Method to find unique characters using charAt() and nested loops
    static char[] uniqueCharacters(String text) {
        int len = text.length();
        char[] temp = new char[len];
        int count = 0;


        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == ch) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                temp[count++] = ch;
            }
        }


        // copy to exact-sized array
        char[] unique = new char[count];
        for (int i = 0; i < count; i++) {
            unique[i] = temp[i];
        }
        return unique;
    }


    // b) Method to find frequencies using ASCII table and unique chars
    static String[][] charFrequencies(String text) {
        int[] freq = new int[256]; // i) ASCII frequency array


        // ii) Count frequency for every char in text
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            freq[ch]++;
        }


        // iii) Get unique characters
        char[] uniqueChars = uniqueCharacters(text);


        // iv) Create 2D String array to store results
        String[][] result = new String[uniqueChars.length][2];


        // v) Fill 2D array with char and frequency
        for (int i = 0; i < uniqueChars.length; i++) {
            result[i][0] = String.valueOf(uniqueChars[i]);
            result[i][1] = String.valueOf(freq[uniqueChars[i]]);
        }


        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // c) Take user input
        System.out.print("Enter a string: ");
        String text = sc.nextLine();


        // Call method to get results
        String[][] freqTable = charFrequencies(text);


        // Display results
        System.out.println("\nCharacter\tFrequency");
        for (String[] row : freqTable) {
            System.out.println(row[0] + "\t\t" + row[1]);
        }


        sc.close();
    }
}
