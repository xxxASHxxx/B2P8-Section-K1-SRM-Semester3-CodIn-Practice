public class StringArrays {
    // TODO: Create a method that takes a string array of names
    // and returns the longest name
    public static String findLongestName(String[] names) {
        if (names == null || names.length == 0) return "";
        int length = 0;
        int ele = 0;
        for (int x = 0; x < names.length; x++) {
            if (names[x] != null && names[x].length() > length) {
                length = names[x].length();   
                ele = x;
            }
        }
        return names[ele];
    }

    // TODO: Create a method that counts how many names
    // start with a given letter (case-insensitive)
    public static int countNamesStartingWith(String[] names, char letter) {
        if (names == null) return 0;
        int count = 0;
        char target = Character.toLowerCase(letter);
        for (int x = 0; x < names.length; x++) {   
            if (names[x] == null || names[x].isEmpty()) continue;
            char first = Character.toLowerCase(names[x].charAt(0)); 
            if (first == target) {
                count++;
            }
        }
        return count;
    }

    // TODO: Create a method that formats all names to "Last, First" format
    // Assume names are given as "First Last"
    public static String[] formatNames(String[] names) {
        if (names == null) return new String[0];
        String[] formatted = new String[names.length];
        for (int x = 0; x < names.length; x++) {   
            String full = names[x];
            if (full == null) { formatted[x] = ""; continue; }
            String[] parts = full.trim().split("\\s+");
            if (parts.length >= 2) {
                String first = parts[0];
                String last = parts[parts.length - 1]; 
                formatted[x] = last + ", " + first;
            } else {
                formatted[x] = full; 
            }
        }
        return formatted; 
    }

    public static void main(String[] args) {
        String[] students = { "John Smith", "Alice Johnson", "Bob Brown",
                              "Carol Davis", "David Wilson" };
        findLongestName(students);
        countNamesStartingWith(students, 'A');
        formatNames(students);
        // TODO: Test all your methods and display results
    }
}
