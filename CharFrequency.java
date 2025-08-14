import java.util.Scanner;


public class CharFrequency {


    // a) Method to find frequency of characters using charAt()
    static String[][] findCharFrequency(String text) {
        int[] freq = new int[256]; // ASCII frequency


        // i, ii) Count frequency
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            freq[ch]++;
        }


        // Count how many unique characters
        int uniqueCount = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) uniqueCount++;
        }


        // iii) Create 2D array to store character & frequency
        String[][] result = new String[uniqueCount][2];
        int index = 0;


        // iv) Loop through text to store char-frequency pairs without duplicates
        boolean[] visited = new boolean[256];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!visited[ch]) {
                visited[ch] = true;
                result[index][0] = String.valueOf(ch);
                result[index][1] = String.valueOf(freq[ch]);
                index++;
            }
        }


        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Input
        System.out.print("Enter a string: ");
        String text = sc.nextLine();


        // Call method
        String[][] freqTable = findCharFrequency(text);


        // Display
        System.out.println("Character\tFrequency");
        for (String[] row : freqTable) {
            System.out.println(row[0] + "\t\t" + row[1]);
        }


        sc.close();
    }
}
