import java.util.*;


public class PasswordAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("How many passwords do you want to check? ");
        int n = sc.nextInt();
        sc.nextLine();


        String[] pwds = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            pwds[i] = sc.nextLine();
        }


        System.out.println("\n=== PASSWORD ANALYSIS REPORT ===");
        System.out.printf("%-15s %-7s %-7s %-7s %-7s %-7s %-7s %-10s\n",
                "Password", "Len", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println("-------------------------------------------------------------------------");


        for (String p : pwds) {
            int[] stats = analyzePassword(p);
            int score = calcScore(p, stats);
            String strength = classify(score);
            System.out.printf("%-15s %-7d %-7d %-7d %-7d %-7d %-7d %-10s\n",
                    p, p.length(), stats[0], stats, stats, stats, score, strength);
        }


        System.out.print("\nDo you want to generate a strong password? (yes/no): ");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("yes")) {
            System.out.print("Enter desired length: ");
            int len = sc.nextInt();
            String strongPw = genPassword(len);
            System.out.println("Generated Strong Password: " + strongPw);
        }


        sc.close();
    }


    public static int[] analyzePassword(String p) {
        int up = 0, lo = 0, dig = 0, sp = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c >= 65 && c <= 90) up++;
            else if (c >= 97 && c <= 122) lo++;
            else if (c >= 48 && c <= 57) dig++;
            else if (c >= 33 && c <= 126) sp++;
        }
        return new int[]{up, lo, dig, sp};
    }


    public static int calcScore(String p, int[] stats) {
        int score = 0;
        if (p.length() > 8) score += (p.length() - 8) * 2;
        for (int i = 0; i < 4; i++) {
            if (stats[i] > 0) score += 10;
        }
        String low = p.toLowerCase();
        if (low.contains("123") || low.contains("abc") || low.contains("qwerty")) {
            score -= 10;
        }
        return score;
    }


    public static String classify(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }


    public static String genPassword(int len) {
        Random rnd = new Random();
        String up = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lo = "abcdefghijklmnopqrstuvwxyz";
        String dig = "0123456789";
        String sp = "!@#$%^&*()_+=-{}[]:;,.<>?";


        StringBuilder sb = new StringBuilder();
        sb.append(up.charAt(rnd.nextInt(up.length())));
        sb.append(lo.charAt(rnd.nextInt(lo.length())));
        sb.append(dig.charAt(rnd.nextInt(dig.length())));
        sb.append(sp.charAt(rnd.nextInt(sp.length())));


        String all = up + lo + dig + sp;
        for (int i = sb.length(); i < len; i++) {
            sb.append(all.charAt(rnd.nextInt(all.length())));
        }


        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) chars.add(sb.charAt(i));
        Collections.shuffle(chars);
        StringBuilder finalPw = new StringBuilder();
        for (char c : chars) finalPw.append(c);


        return finalPw.toString();
    }
}


