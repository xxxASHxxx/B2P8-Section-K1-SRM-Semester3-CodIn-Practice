package w2lab;
import java.util.Scanner;
public class pg6 {
    public static String[] splitWords(String text) {
        int n = text.length();
        java.util.ArrayList<String> words = new java.util.ArrayList<>();
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < n) {
            words.add(text.substring(start));
        }
        return words.toArray(new String[0]);
    }


    public static String justifyTextSB(String[] words, int width) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int spaces = width - (lineLen - (j - i - 1));
            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();
            if (gaps == 0 || j == words.length) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) line.append(' ');
                }
                for (int k = line.length(); k < width; k++) line.append(' ');
            } else {
                int evenSpace = spaces / gaps;
                int extra = spaces % gaps;
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) {
                        for (int s = 0; s < evenSpace; s++) line.append(' ');
                        if (extra > 0) {
                            line.append(' ');
                            extra--;
                        }
                    }
                }
            }
            result.append(line).append('\n');
            i = j;
        }
        return result.toString();
    }


    public static String centerAlignText(String[] words, int width) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            StringBuilder line = new StringBuilder();
            for (int k = i; k < j; k++) {
                line.append(words[k]);
                if (k != j - 1) line.append(' ');
            }
            int pad = width - line.length();
            int leftPad = pad / 2;
            int rightPad = pad - leftPad;
            StringBuilder centered = new StringBuilder();
            for (int p = 0; p < leftPad; p++) centered.append(' ');
            centered.append(line);
            for (int p = 0; p < rightPad; p++) centered.append(' ');
            result.append(centered).append('\n');
            i = j;
        }
        return result.toString();
    }


    public static String justifyTextConcat(String[] words, int width) {
        String result = "";
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int spaces = width - (lineLen - (j - i - 1));
            int gaps = j - i - 1;
            String line = "";
            if (gaps == 0 || j == words.length) {
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k != j - 1) line += " ";
                }
                for (int k = line.length(); k < width; k++) line += " ";
            } else {
                int evenSpace = spaces / gaps;
                int extra = spaces % gaps;
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k != j - 1) {
                        for (int s = 0; s < evenSpace; s++) line += " ";
                        if (extra > 0) {
                            line += " ";
                            extra--;
                        }
                    }
                }
            }
            result += line + "\n";
            i = j;
        }
        return result;
    }


    public static void displayFormattedText(String formatted, String title) {
        System.out.println(title);
        String[] lines = formatted.split("\n");
        for (int i = 0; i < lines.length; i++) {
            System.out.printf("%2d: %-"+lines[i].length()+"s (%d chars)\n", i+1, lines[i], lines[i].length());
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to format:");
        String text = sc.nextLine();
        System.out.print("Enter desired line width: ");
        int width = sc.nextInt();
        sc.nextLine();


        String[] words = splitWords(text);


        long startSB = System.nanoTime();
        String justifiedSB = justifyTextSB(words, width);
        long endSB = System.nanoTime();


        long startConcat = System.nanoTime();
        String justifiedConcat = justifyTextConcat(words, width);
        long endConcat = System.nanoTime();


        String centered = centerAlignText(words, width);


        System.out.println("\nOriginal Text:\n" + text + "\n");
        displayFormattedText(justifiedSB, "Left-Justified (StringBuilder):");
        displayFormattedText(centered, "Center-Aligned (StringBuilder):");


        System.out.println("Performance Analysis:");
        System.out.println("StringBuilder Justify: " + (endSB - startSB) + " ns");
        System.out.println("String Concatenation Justify: " + (endConcat - startConcat) + " ns");
    }
   
}
