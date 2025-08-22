

package w2lab;
import java.util.Scanner;


public class pg4 {
    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char e = (char) ('A' + (c - 'A' + shift + 26) % 26);
                encrypted.append(e);
            } else if (c >= 'a' && c <= 'z') {
                char e = (char) ('a' + (c - 'a' + shift + 26) % 26);
                encrypted.append(e);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }


    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }


    public static void displayAscii(String label, String text) {
        System.out.print(label + ": ");
        for (char c : text.toCharArray()) {
            System.out.print(c + "(" + (int)c + ") ");
        }
        System.out.println();
    }


    public static boolean validate(String original, String decrypted) {
        return original.equals(decrypted);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String input = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();


        displayAscii("Original", input);
        String encrypted = encrypt(input, shift);
        displayAscii("Encrypted", encrypted);
        String decrypted = decrypt(encrypted, shift);
        displayAscii("Decrypted", decrypted);


        if (validate(input, decrypted)) {
            System.out.println("Decryption successful: Original text restored.");
        } else {
            System.out.println("Decryption failed: Original text not restored.");
        }
        sc.close();
    }
}
