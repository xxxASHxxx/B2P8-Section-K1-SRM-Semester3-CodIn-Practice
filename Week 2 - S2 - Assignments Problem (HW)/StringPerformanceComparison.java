public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");


        long t1 = System.nanoTime();
        String r1 = withString(2000);
        long t2 = System.nanoTime();
        System.out.println("String time: " + (t2 - t1) + " ns");


        long t3 = System.nanoTime();
        String r2 = withBuilder(2000);
        long t4 = System.nanoTime();
        System.out.println("StringBuilder time: " + (t4 - t3) + " ns");


        long t5 = System.nanoTime();
        String r3 = withBuffer(2000);
        long t6 = System.nanoTime();
        System.out.println("StringBuffer time: " + (t6 - t5) + " ns");


        demoBuilder();
        demoThreadSafe();
        compareStrings();
        demoMemory();
    }


    public static String withString(int n) {
        String r = "";
        for (int i = 0; i < n; i++) {
            r += "Java" + i + " ";
        }
        return r;
    }


    public static String withBuilder(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("Java").append(i).append(" ");
        }
        return sb.toString();
    }


    public static String withBuffer(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("Java").append(i).append(" ");
        }
        return sb.toString();
    }


    public static void demoBuilder() {
        StringBuilder sb = new StringBuilder("Hello World");
        sb.append(" Again");
        sb.insert(5, "_Java_");
        sb.delete(0, 2);
        sb.deleteCharAt(3);
        sb.reverse();
        sb.replace(0, 5, "Hi");
        sb.setCharAt(2, 'X');
        System.out.println("Builder result: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(100);
        sb.trimToSize();
        System.out.println("Capacity after ensure/trim: " + sb.capacity());
    }


    public static void demoThreadSafe() {
        StringBuffer buf = new StringBuffer("X");
        Runnable r = () -> {
            for (int i = 0; i < 100; i++) {
                buf.append("A");
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {}
        System.out.println("Buffer len (thread-safe): " + buf.length());


        StringBuilder sb = new StringBuilder("X");
        Runnable r2 = () -> {
            for (int i = 0; i < 100; i++) {
                sb.append("B");
            }
        };
        Thread t3 = new Thread(r2);
        Thread t4 = new Thread(r2);
        t3.start();
        t4.start();
        try {
            t3.join();
            t4.join();
        } catch (Exception e) {}
        System.out.println("Builder len (not thread-safe): " + sb.length());
    }


    public static void compareStrings() {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        System.out.println("s1==s2: " + (s1 == s2));
        System.out.println("s1==s3: " + (s1 == s3));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s1.equalsIgnoreCase(\"hello\"): " + s1.equalsIgnoreCase("hello"));
        System.out.println("s1.compareTo(s3): " + s1.compareTo(s3));
        System.out.println("s1.compareToIgnoreCase(\"hello\"): " + s1.compareToIgnoreCase("hello"));
    }


    public static void demoMemory() {
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");
        System.out.println("s1 from pool: " + (s1 == s2));
        System.out.println("s1 vs s3 (heap vs pool): " + (s1 == s3));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) sb.append(i);
        System.out.println("Builder cap: " + sb.capacity() + ", len: " + sb.length());
    }
}
