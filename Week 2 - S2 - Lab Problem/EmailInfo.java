package w2lab;
import java.util.*;
public class pg5 {
    static class EmailInfo {
        String email, username, domain, domainName, extension;
        boolean isValid;


        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean isValid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.isValid = isValid;
        }
    }


    public static boolean validateEmail(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at == -1 || at != lastAt) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }


    public static EmailInfo extractEmailInfo(String email) {
        boolean valid = validateEmail(email);
        String username = "", domain = "", domainName = "", extension = "";
        if (valid) {
            int at = email.indexOf('@');
            username = email.substring(0, at);
            domain = email.substring(at + 1);
            int dot = domain.lastIndexOf('.');
            if (dot != -1) {
                domainName = domain.substring(0, dot);
                extension = domain.substring(dot + 1);
            }
        }
        return new EmailInfo(email, username, domain, domainName, extension, valid);
    }


    public static void analyzeEmails(List<EmailInfo> infos) {
        int validCount = 0, invalidCount = 0, usernameLenSum = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        for (EmailInfo info : infos) {
            if (info.isValid) {
                validCount++;
                usernameLenSum += info.username.length();
                domainCount.put(info.domain, domainCount.getOrDefault(info.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mcd = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mcd = entry.getKey();
            }
        }
        double avgUsernameLen = validCount > 0 ? (double) usernameLenSum / validCount : 0;
        System.out.println("\nAnalysis:");
        System.out.println("Total valid emails: " + validCount);
        System.out.println("Total invalid emails: " + invalidCount);
        System.out.println("Most common domain: " + (mcd.isEmpty() ? "N/A" : mcd));
        System.out.printf("Average username length: %.2f\n", avgUsernameLen);
    }


    public static void displayTable(List<EmailInfo> infos) {
        System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid?");
        for (EmailInfo info : infos) {
            System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s\n",
                    info.email,
                    info.username,
                    info.domain,
                    info.domainName,
                    info.extension,
                    info.isValid ? "Valid" : "Invalid");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<EmailInfo> infos = new ArrayList<>();
        System.out.print("Enter number of emails: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email #" + (i + 1) + ": ");
            String email = sc.nextLine().trim();
            infos.add(extractEmailInfo(email));
        }
        displayTable(infos);
        analyzeEmails(infos);
    }
}
