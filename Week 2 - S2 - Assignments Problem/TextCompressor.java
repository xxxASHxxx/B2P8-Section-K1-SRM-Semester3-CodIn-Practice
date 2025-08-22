import java.util.Scanner;


public class TextCompressor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter text to compress: ");
        String text = sc.nextLine();


        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int size = countFrequency(text, chars, freq);


        String[][] map = buildCodes(chars, freq, size);


        String compressed = compress(text, map);
        String decompressed = decompress(compressed, map);


        showAnalysis(text, compressed, decompressed, chars, freq, size, map);


        sc.close();
    }


    public static int countFrequency(String text, char[] chars, int[] freq) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = -1;
            for (int j = 0; j < n; j++) {
                if (chars[j] == c) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) {
                chars[n] = c;
                freq[n] = 1;
                n++;
            } else {
                freq[idx]++;
            }
        }
        return n;
    }


    public static String[][] buildCodes(char[] chars, int[] freq, int size) {
        String[][] map = new String[size][2];
        for (int i = 0; i < size; i++) {
            map[i] = String.valueOf(chars[i]);
            if (freq[i] > 5) {
                map[i] = String.valueOf(i);  
            } else {
                map[i] = "#" + i;  
            }
        }
        return map;
    }


    public static String compress(String text, String[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String ch = String.valueOf(text.charAt(i));
            for (int j = 0; j < map.length; j++) {
                if (map[j].equals(ch)) {
                    sb.append(map[j]);
                    break;
                }
            }
        }
        return sb.toString();
    }


    public static String decompress(String comp, String[][] map) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < comp.length()) {
            boolean matched = false;
            for (int j = 0; j < map.length; j++) {
                String code = map[j];
                if (comp.startsWith(code, i)) {
                    sb.append(map[j]);
                    i += code.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                i++;
            }
        }
        return sb.toString();
    }


    public static void showAnalysis(String text, String comp, String decomp, char[] chars, int[] freq, int size, String[][] map) {
        System.out.println("\n=== FREQUENCY TABLE ===");
        System.out.printf("%-10s %-10s\n", "Char", "Freq");
        for (int i = 0; i < size; i++) {
            System.out.printf("%-10s %-10d\n", chars[i], freq[i]);
        }


        System.out.println("\n=== COMPRESSION MAP ===");
        System.out.printf("%-10s %-10s\n", "Char", "Code");
        for (int i = 0; i < map.length; i++) {
            System.out.printf("%-10s %-10s\n", map[i][0], map[i]);
        }


        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + comp);
        System.out.println("Decompressed Text: " + decomp);


        double ratio = (double) comp.length() / text.length();
        double percent = (1 - ratio) * 100;
        System.out.printf("\nCompression Efficiency: %.2f%%\n", percent);
    }
}
