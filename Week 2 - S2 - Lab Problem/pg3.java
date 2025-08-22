package w2lab;
import java.util.Scanner;
public class pg3 {
    private static long[] sc(int n, String s) {
        long st = System.currentTimeMillis();
        String r = "";
        for (int i = 0; i < n; i++) {
            r += s;
        }
        long et = System.currentTimeMillis();
        return new long[]{et - st, r.length()};
    }
private static long[] sbc(int n, String s) {
        long st = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        long et = System.currentTimeMillis();
        return new long[]{et - st, sb.length()};
    }
  private static long[] sfc(int n, String s) {
        long st = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        long et = System.currentTimeMillis();
        return new long[]{et - st, sb.length()};
    }
private static void dr(long[] sr, long[] br, long[] fr) {
        System.out.println("Method\t\tTime(ms)\tLength");
        System.out.println("String\t\t" + sr[0] + "\t\t" + sr[1]);
        System.out.println("StringBuilder\t" + br[0] + "\t\t" + br[1]);
        System.out.println("StringBuffer\t" + fr[0] + "\t\t" + fr[1]);
    }
public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = scn.nextInt();
        String s = "abc";
        long[] sr = sc(n, s);
        long[] br = sbc(n, s);
        long[] fr = sfc(n, s);
        dr(sr, br, fr);
        scn.close();
    }
}
