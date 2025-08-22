import java.util.Scanner;


public class TextCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter mathematical expression: ");
        String expr = sc.nextLine();


        if (!isValid(expr)) {
            System.out.println("Invalid expression.");
        } else {
            System.out.println("=== CALCULATION STEPS ===");
            System.out.println("Original: " + expr);
            StringBuilder steps = new StringBuilder();


            String finalExpr = solveParentheses(expr, steps);
            int result = evaluate(finalExpr, steps);


            steps.append("Final Result = ").append(result).append("\n");
            System.out.println(steps.toString());
        }


        sc.close();
    }


    public static boolean isValid(String expr) {
        int balance = 0;
        char prev = ' ';
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')' && c != ' ') {
                return false;
            }
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
            if ((c == '+' || c == '-' || c == '*' || c == '/') && (prev == '+' || prev == '-' || prev == '*' || prev == '/')) {
                return false;
            }
            if (c != ' ') prev = c;
        }
        return balance == 0;
    }


    public static String solveParentheses(String expr, StringBuilder steps) {
        while (expr.contains("(")) {
            int close = expr.indexOf(")");
            int open = expr.lastIndexOf("(", close);
            String inside = expr.substring(open + 1, close);
            int val = evaluate(inside, steps);
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
            steps.append("Parentheses -> ").append(expr).append("\n");
        }
        return expr;
    }


    public static int evaluate(String expr, StringBuilder steps) {
        expr = expr.replaceAll(" ", "");
        int[] nums = new int[expr.length()];
        char[] ops = new char[expr.length()];
        int nCount = 0, oCount = 0;


        int i = 0;
        while (i < expr.length()) {
            if (Character.isDigit(expr.charAt(i))) {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                nums[nCount++] = Integer.parseInt(expr.substring(i, j));
                i = j;
            } else {
                ops[oCount++] = expr.charAt(i);
                i++;
            }
        }


        for (int k = 0; k < oCount; k++) {
            if (ops[k] == '*' || ops[k] == '/') {
                if (ops[k] == '*') nums[k] = nums[k] * nums[k + 1];
                else nums[k] = nums[k] / nums[k + 1];
                for (int m = k + 1; m < nCount - 1; m++) nums[m] = nums[m + 1];
                nCount--;
                for (int m = k; m < oCount - 1; m++) ops[m] = ops[m + 1];
                oCount--;
                k--;
                steps.append("Step -> ").append(rebuild(nums, nCount, ops, oCount)).append("\n");
            }
        }


        while (oCount > 0) {
            int res = 0;
            if (ops[0] == '+') res = nums + nums;
            else if (ops == '-') res = nums - nums;
            nums = res;
            for (int m = 1; m < nCount - 1; m++) nums[m] = nums[m + 1];
            nCount--;
            for (int m = 0; m < oCount - 1; m++) ops[m] = ops[m + 1];
            oCount--;
            steps.append("Step -> ").append(rebuild(nums, nCount, ops, oCount)).append("\n");
        }


        return nums;
    }


    public static String rebuild(int[] nums, int nCount, char[] ops, int oCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nCount; i++) {
            sb.append(nums[i]);
            if (i < oCount) sb.append(ops[i]);
        }
        return sb.toString();
    }
}
