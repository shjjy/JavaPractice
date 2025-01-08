package algorithm.ls3;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacciR(10));
        System.out.println(fibonacciI(10));
        System.out.println(fibonacciM(10));
        System.out.println(fibonacciQ(8));
//        System.out.println(fibonacciStrassen(10));
    }

    private static int fibonacciR(int n) {
        if (n <= 1) return n;
        return fibonacciR(n - 1) + fibonacciR(n - 2);
    }

    private static int fibonacciI(int n) {
        if (n <= 1) return n;
        int f1 = 0, f2 = 1, result = 0;
        for (int i = 2; i <= n; i++) {
            result = f1 + f2;
            f1 = f2;
            f2 = result;
        }
        return result;
    }

    //目標矩陣 n-1 次方，矩陣 [0][0] 就是F(n)
    private static int fibonacciM(int n) {
        if (n <= 1) return n;
        int[][] result = {{1, 1}, {1, 0}};
        int[][] base = {{1, 1}, {1, 0}}; // 目標矩陣
        for (int i = 2; i < n ; i++) {
            result = multiplyMatrices(result, base); // 每次乘上 base
        }
        return result[0][0];
    }

    public static int fibonacciQ(int n) {
        if (n <= 1) return n;
        int[][] result = {{1, 0}, {0, 1}};
        int[][] base = {{1, 1}, {1, 0}};
        int exp = n - 1;

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = multiplyMatrices(result, base); // 如果是奇數，乘上 base
            }
            base = multiplyMatrices(base, base); // 將 base 平方
            exp /= 2; // 次方數除以 2
        }
        return result[0][0]; // F(n) 是矩陣 [0][0]
    }

    private static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return result;
    }

}
