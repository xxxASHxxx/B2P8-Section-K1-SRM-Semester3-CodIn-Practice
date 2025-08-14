import java.util.Scanner;


public class UniqueChars {


    // a) Find length without using length()
    static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count); // will throw exception at end
                count++;
            }
        } catch (Exception e) {
            // do nothing, count is now length
        }
        return count;
    }


    // b) Find unique characters using charAt()
    static char[] findUniqueChars(String text) {
        int len = getLength(text);
        char[] temp = new char[len]; // store unique chars
        int uniqueCount = 0;


        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            boolean isUnique = true;
            // check in already found unique chars
            for (int j = 0; j < uniqueCount; j++) {
                if (temp[j] == ch) {
                    isUnique = false;
                    break;
                }
            }
            // if unique, add to temp
            if (isUnique) {
                temp[uniqueCount] = ch;
                uniqueCount++;
            }
        }


        // create new array with exact size
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = temp[i];
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // c) Take input
        System.out.print("Enter a string: ");
        String text = sc.nextLine();


        // Get unique characters
        char[] unique = findUniqueChars(text);


        // Display result
        System.out.print("Unique characters: ");
        for (char c : unique) {
            System.out.print(c + " ");
        }


        sc.close();
    }
}


