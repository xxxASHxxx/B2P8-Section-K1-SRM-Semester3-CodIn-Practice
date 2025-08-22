package w2lab;


import java.util.Scanner;
class Program
{
   public static int[] findOccurrences(String str, String sub) {
        if (sub == null || sub.length() == 0) return new int[0];
        int[] temp = new int[str.length()];
        int count = 0;
        for (int i = 0; i <= str.length() - sub.length(); i++) {
            boolean found = true;
            for (int j = 0; j < sub.length(); j++) {
                if (str.charAt(i + j) != sub.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                temp[count++] = i;
            }
        }
        int[] result = new int[count];
        for (int k = 0; k < count; k++) {
            result[k] = temp[k];
        }
        return result;
    }
    public static String manualReplace(String str, String target, String replacement) {
        if (target == null || target.length() == 0) return str;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i <= str.length() - target.length()) {
            boolean match = true;
            for (int j = 0; j < target.length(); j++) {
                if (str.charAt(i + j) != target.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.append(replacement);
                i += target.length();
            } else {
                result.append(str.charAt(i));
                i++;
            }
        }


        while (i < str.length()) {
            result.append(str.charAt(i));
            i++;
        }
        return result.toString();
    }
    public static boolean compareWithBuiltInReplace(String str, String target, String replacement) {
        String manual = manualReplace(str, target, replacement);
        String builtin = str.replace(target, replacement);
        return manual.equals(builtin);
    }
    public static void main(String args[])
    {
        Scanner SC = new Scanner(System.in);
        System.out.println("Enter a string");
        String input = SC.nextLine();
        System.out.println("Enter a substring to find occurrences");
        String substring = SC.next();
        System.out.println("Enter a string to be replaced");
        String replaced = SC.nextLine();  
        int[] occurrences = findOccurrences(input, substring);
        System.out.println(substring + " occurs in " + input + " at positions " + java.util.Arrays.toString(occurrences) + " (" + occurrences.length + " times)");    
        System.out.println("Manual replace: " + manualReplace(input, substring, replaced));
        System.out.println("Built-in replace: " + input.replace(substring, replaced));
    }
}   
