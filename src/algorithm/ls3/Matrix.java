package algorithm.ls3;

import java.util.Random;

//k=64 有最佳效能(64 以下用一般運算即可)
//長度/執行時間(ms) 一般方法          strassen        strassen k8     strassen k16    strassen k32    strassen k64    strassen k256
//2               0               0               0               0               0               0               0
//4               0               0               0               0               0               0               0
//8               0               0               0               0               0               0               0
//16              0               1               0               0               0               0               0
//32              0               5               0               0               0               0               0
//64              0               34              0               0               0               0               0
//128             0               189             3               1               0               0               0
//256             7               1320            21              10              6               6               6
//512             89              8392            155             71              48              43              48
//1024            787             66846           1125            515             351             313             368
public class Matrix {
    public static void main(String[] args) {
        System.out.printf("%-10s %-13s %-15s %-15s %-15s %-15s %-15s %-15s%n", "長度/執行時間(ms)", "一般方法", "strassen",
                "strassen k8", "strassen k16", "strassen k32", "strassen k64", "strassen k256");
        for (int n = 2; n <= 1024; n *= 2) {
            int[][] _A = buildMatrix(n);
            int[][] _B = buildMatrix(n);

            long time1 = System.nanoTime();
            multiplyMatrices(_A, _B);
            long time2 = System.nanoTime();
            multiplyMatricesStrassen(_A, _B, 1);
            long time3 = System.nanoTime();
            multiplyMatricesStrassen(_A, _B, 8);
            long time4 = System.nanoTime();
            multiplyMatricesStrassen(_A, _B, 16);
            long time5 = System.nanoTime();
            multiplyMatricesStrassen(_A, _B, 32);
            long time6 = System.nanoTime();
            multiplyMatricesStrassen(_A, _B, 64);
            long time7 = System.nanoTime();
            multiplyMatricesStrassen(_A, _B, 256);
            long time8 = System.nanoTime();

            System.out.printf("%-15d %-15d %-15d %-15d %-15d %-15d %-15d %-15d%n", n,
                    (time2 - time1) / 1_000_000,
                    (time3 - time2) / 1_000_000,
                    (time4 - time3) / 1_000_000,
                    (time5- time4) / 1_000_000,
                    (time6- time5) / 1_000_000,
                    (time7- time6) / 1_000_000,
                    (time8- time7) / 1_000_000);
        }
    }

    private static int[][] buildMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix;
    }

    private static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int size = a.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d ", value);
            }
            System.out.println();
        }
    }

    private static int[][] multiplyMatricesStrassen(int[][] a, int[][] b, int normalSize) {
        int n = a.length;
        if (n <= normalSize) {
            return multiplyMatrices(a, b); // 使用普通乘法
        }
        int halfLength = n / 2;

        int[][] a11 = new int[halfLength][halfLength];
        int[][] a12 = new int[halfLength][halfLength];
        int[][] a21 = new int[halfLength][halfLength];
        int[][] a22 = new int[halfLength][halfLength];

        int[][] b11 = new int[halfLength][halfLength];
        int[][] b12 = new int[halfLength][halfLength];
        int[][] b21 = new int[halfLength][halfLength];
        int[][] b22 = new int[halfLength][halfLength];

        splitMatrix(a, a11, a12, a21, a22);
        splitMatrix(b, b11, b12, b21, b22);

        int[][] m1 = multiplyMatricesStrassen(addMatrix(a11, a22), addMatrix(b11, b22), normalSize);
        int[][] m2 = multiplyMatricesStrassen(addMatrix(a21, a22), b11, normalSize);
        int[][] m3 = multiplyMatricesStrassen(a11, subtractMatrix(b12, b22), normalSize);
        int[][] m4 = multiplyMatricesStrassen(a22, subtractMatrix(b21, b11), normalSize);
        int[][] m5 = multiplyMatricesStrassen(addMatrix(a11, a12), b22, normalSize);
        int[][] m6 = multiplyMatricesStrassen(subtractMatrix(a21, a11), addMatrix(b11, b12), normalSize);
        int[][] m7 = multiplyMatricesStrassen(subtractMatrix(a12, a22), addMatrix(b21, b22), normalSize);

        int[][] c11 = subtractMatrix(addMatrix(addMatrix(m1, m4), m7), m5);
        int[][] c12 = addMatrix(m3, m5);
        int[][] c21 = addMatrix(m2, m4);
        int[][] c22 = subtractMatrix(addMatrix(addMatrix(m1, m3), m6), m2);

        int[][] result = new int[n][n];
        combineMatrix(result, c11, c12, c21, c22);
        return result;
    }

    private static void splitMatrix(int[][] source, int[][] a11, int[][] a12, int[][] a21, int[][] a22) {
        int halfLength = source.length / 2;
        for (int i = 0; i < halfLength; i++) {
            System.arraycopy(source[i], 0, a11[i], 0, halfLength);
            System.arraycopy(source[i], halfLength, a12[i], 0, halfLength);
            System.arraycopy(source[i + halfLength], 0, a21[i], 0, halfLength);
            System.arraycopy(source[i + halfLength], halfLength, a22[i], 0, halfLength);
        }
    }

    private  static int[][] addMatrix(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    private static int[][] subtractMatrix(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    private static void combineMatrix(int[][] result, int[][] c11, int[][] c12, int[][] c21, int[][] c22) {
        int halfLength = result.length / 2;
        for (int i = 0; i < halfLength; i++) {
            System.arraycopy(c11[i], 0, result[i], 0, halfLength);
            System.arraycopy(c12[i], 0, result[i], halfLength, halfLength);
            System.arraycopy(c21[i], 0, result[i + halfLength], 0, halfLength);
            System.arraycopy(c22[i], 0, result[i + halfLength], halfLength, halfLength);
        }
    }
}
