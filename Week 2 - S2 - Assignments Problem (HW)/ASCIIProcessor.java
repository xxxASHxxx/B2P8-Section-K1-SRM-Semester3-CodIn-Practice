import java.util.Scanner;


public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter a string: ");
        String txt = sc.nextLine();


        for (int i = 0; i < txt.length(); i++) {
            char ch = txt.charAt(i);
            int code = (int) ch;
            System.out.println("Char: " + ch + " | ASCII: " + code);


            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);


            if (Character.isLetter(ch)) {
                char otherCase = toggleCase(ch);
                System.out.println("Toggle case: " + otherCase + " | ASCII: " + (int) otherCase);
                int diff = Math.abs(((int) Character.toUpperCase(ch)) - ((int) Character.toLowerCase(ch)));
                System.out.println("Diff between cases: " + diff);
            }
            System.out.println();
        }


        System.out.println("ASCII Table (65 to 75):");
        displayASCIITable(65, 75);


        System.out.print("Enter shift for Caesar Cipher: ");
        int shift = sc.nextInt();
        sc.nextLine();
        String ciphered = caesarCipher(txt, shift);
        System.out.println("Ciphered: " + ciphered);


        int[] asciiArr = stringToASCII(txt);
        System.out.print("ASCII array: ");
        for (int n : asciiArr) {
            System.out.print(n + " ");
        }
        System.out.println();


        String strBack = asciiToString(asciiArr);
        System.out.println("Back from ASCII array: " + strBack);


        sc.close();
    }


    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return "Uppercase Letter";
        } else if (ch >= 'a' && ch <= 'z') {
            return "Lowercase Letter";
        } else if (ch >= '0' && ch <= '9') {
            return "Digit";
        } else {
            return "Special Character";
        }
    }


    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        } else if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);
        }
        return ch;
    }


    public static String caesarCipher(String text, int shift) {
        String res = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ((ch - 'A' + shift) % 26 + 'A');
            } else if (ch >= 'a' && ch <= 'z') {
                ch = (char) ((ch - 'a' + shift) % 26 + 'a');
            }
            res += ch;
        }
        return res;
    }


    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }


    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = (int) text.charAt(i);
        }
        return arr;
    }


    public static String asciiToString(int[] asciiValues) {
        String res = "";
        for (int val : asciiValues) {
            res += (char) val;
        }
        return res;
    }
}


