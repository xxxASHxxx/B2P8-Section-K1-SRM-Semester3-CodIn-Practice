import java.util.*;


public class FileOrganizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter number of files: ");
        int n = Integer.parseInt(sc.nextLine());


        String[] files = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter file name " + (i + 1) + ": ");
            files[i] = sc.nextLine();
        }


        String[][] info = new String[n][4]; // [orig, name, ext, category]


        for (int i = 0; i < n; i++) {
            String[] parts = extract(files[i]);
            if (parts == null) {
                info[i] = files[i];
                info[i] = "Invalid";
                info[i] = "";
                info[i] = "Invalid";
            } else {
                info[i] = files[i];
                info[i] = parts;
                info[i] = parts;
                info[i] = categorize(parts);
            }
        }


        String[] renamed = new String[n];
        for (int i = 0; i < n; i++) {
            renamed[i] = newName(info[i], info[i], info[i], i);
        }


        showReport(info, renamed);


        sc.close();
    }


    public static String[] extract(String f) {
        int dot = f.lastIndexOf('.');
        if (dot == -1 || dot == f.length() - 1) return null;
        String name = f.substring(0, dot);
        String ext = f.substring(dot + 1).toLowerCase();
        if (name.length() == 0) return null;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (!(ch >= 'A' && ch <= 'Z') && !(ch >= 'a' && ch <= 'z') && !(ch >= '0' && ch <= '9') && ch != '_' && ch != '-') {
                return null;
            }
        }
        return new String[]{name, ext};
    }


    public static String categorize(String ext) {
        if (ext.equals("txt") || ext.equals("doc")) return "Document";
        if (ext.equals("jpg") || ext.equals("png")) return "Image";
        if (ext.equals("mp3") || ext.equals("wav")) return "Audio";
        if (ext.equals("mp4") || ext.equals("avi")) return "Video";
        if (ext.equals("java") || ext.equals("py")) return "Code";
        return "Unknown";
    }


    public static String newName(String name, String ext, String cat, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(cat.substring(0, Math.min(3, cat.length())).toUpperCase());
        sb.append("_20250822_"); // pretend date
        sb.append(id + 1);
        sb.append(".").append(ext);
        return sb.toString();
    }


    public static void showReport(String[][] info, String[] renamed) {
        System.out.println("\n=== FILE ORGANIZATION REPORT ===");
        System.out.printf("%-20s %-12s %-12s %-12s %-20s\n", "Original", "Name", "Ext", "Category", "New Name");
        System.out.println("-----------------------------------------------------------------------");


        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i < info.length; i++) {
            System.out.printf("%-20s %-12s %-12s %-12s %-20s\n",
                    info[i][0], info[i], info[i], info[i], renamed[i]);


            counts.put(info[i], counts.getOrDefault(info[i], 0) + 1);
        }


        System.out.println("\n=== CATEGORY COUNTS ===");
        for (String cat : counts.keySet()) {
            System.out.println(cat + ": " + counts.get(cat));
        }


        System.out.println("\n=== RENAME COMMANDS (SIMULATED) ===");
        for (int i = 0; i < info.length; i++) {
            if (!info[i].equals("Invalid")) {
                System.out.println("Rename: " + info[i] + " -> " + renamed[i]);
            }
        }


        System.out.println("\nTotal files: " + info.length);
    }
}
