import java.util.Scanner;


public class CharFrequencyNestedLoop {


    // a) Method to find frequency using nested loops
    static String[] findFrequency(String text) {
        char[] chars = text.toCharArray(); // i) store characters
        int[] freq = new int[chars.length];
       
        // ii) find frequency using nested loops
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') continue; // already counted
            freq[i] = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; // mark as counted
                }
            }
        }
       
        // iii) store char-frequency pairs in 1D String array
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') count++;
        }
       
        String[] result = new String[count];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                result[index] = chars[i] + " - " + freq[i];
                index++;
            }
        }
       
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        // b) Input
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
       
        // Call method
        String[] frequencies = findFrequency(text);
       
        // Display
        System.out.println("\nCharacter\tFrequency");
        for (String pair : frequencies) {
            String[] parts = pair.split(" - ");
            System.out.println(parts[0] + "\t\t" + parts[1]);
        }
       
        sc.close();
    }
}


