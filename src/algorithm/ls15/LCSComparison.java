package algorithm.ls15;

import java.util.Arrays;

public class LCSComparison {

    public static void main(String[] args) {
        String text1 = "ABCBDAB";
        String text2 = "BDCABA";

        long startBruteForce = System.nanoTime();
        int bruteForceResult = lcsBruteForce(text1, text2);
        long endBruteForce = System.nanoTime();

        long startDynamic = System.nanoTime();
        int dynamicResult = lcsDynamic(text1, text2);
        long endDynamic = System.nanoTime();

        long startOptimized = System.nanoTime();
        int optimizedResult = lcsDynamicOptimized(text1, text2);
        long endOptimized = System.nanoTime();

        System.out.println("text1: " + text1);
        System.out.println("text2: " + text2);
        System.out.println("暴力法 LCS 長度: " + bruteForceResult);
        System.out.println("耗時: " + (endBruteForce - startBruteForce) / 1_000_000.0 + " 毫秒");
        System.out.println("動態規劃 LCS 長度: " + dynamicResult);
        System.out.println("耗時: " + (endDynamic - startDynamic) / 1_000_000.0 + " 毫秒");
        System.out.println("空間優化動態規劃 LCS 長度: " + optimizedResult);
        System.out.println("耗時: " + (endOptimized - startOptimized) / 1_000_000.0 + " 毫秒");
    }

    public static int lcsBruteForce(String x, String y) {
        return findLCSBruteForce(x, y, x.length(), y.length());
    }

    private static int findLCSBruteForce(String x, String y, int i, int j) {
        if (i == 0 || j == 0) return 0;
        if (x.charAt(i - 1) == y.charAt(j - 1)) {
            return 1 + findLCSBruteForce(x, y, i - 1, j - 1);
        } else {
            return Math.max(findLCSBruteForce(x, y, i - 1, j), findLCSBruteForce(x, y, i, j - 1));
        }
    }

    public static int lcsDynamic(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static int lcsDynamicOptimized(String x, String y) {
        int m = x.length();
        int n = y.length();

        if (m < n) {
            String temp = x;
            x = y;
            y = temp;
            m = x.length();
            n = y.length();
        }

        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        return curr[n];
    }

}