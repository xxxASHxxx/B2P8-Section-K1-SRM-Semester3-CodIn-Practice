import java.util.Scanner;


public class StringLengthFinder {


    // Method to find length without using length()
    public static int findLengthWithoutLengthMethod(String str) {
        int count = 0;


        // Use infinite loop and catch exception when index is out of bounds
        try {
            while (true) {
                str.charAt(count); // Will throw exception when index exceeds size
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Loop ends when exception occurs
        }
       
        return count;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Taking user input
        System.out.print("Enter a string: ");
        String input = sc.next();


        // Calling custom method
        int customLength = findLengthWithoutLengthMethod(input);


        // Using built-in length() method
        int builtInLength = input.length();


        // Display results
        System.out.println("Length using custom method: " + customLength);
        System.out.println("Length using built-in method: " + builtInLength);


        sc.close();
    }
}
