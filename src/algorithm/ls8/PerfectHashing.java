package algorithm.ls8;

import java.util.Arrays;
import java.util.Random;

public class PerfectHashing {

    public static void main(String[] args) {
        int[] testData = {12, 25, 36, 20, 30, 8, 42, 10, 15, 12};
        PerfectHashing hashing = new PerfectHashing(getNextIntegerSquare(testData.length));

        for (int key : testData) {
            hashing.insert(key);
        }

        hashing.printTable();
        searchAndPrint(hashing, 20);
        searchAndPrint(hashing, 50);
    }

    private final int p = 101;
    private final int m;
    private int a, b;

    private int[][][] table;

    public PerfectHashing(int size) {
        this.m = size; // 設定哈希表大小
        table = new int[m][2][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(table[i][j], -1); // 先填充 -1
            }
        }
        Random rand = new Random();
        this.a = rand.nextInt(p - 1) + 1;
        this.b = rand.nextInt(p);
    }

    //table[mainIndex]= [[a, b][value...]]
    private void insert(int key) {
        int mainIndex = hash(key);
        int valueIndex = hash(key, getAB(mainIndex)[0], getAB(mainIndex)[1]);
        int maxAttempts = m; // 設定最大嘗試次數，避免無窮迴圈
        while (maxAttempts-- > 0 && table[mainIndex][1][valueIndex] != -1) {
            if (table[mainIndex][1][valueIndex] == key) {
                return; // 如果發現重複值則忽略插入
            }
            valueIndex = (valueIndex + 1) % m; // 進行線性探測
        }

        if (maxAttempts == -1) {
            throw new IllegalStateException("Hash table is full, cannot insert new element.");
        }
        table[mainIndex][1][valueIndex] = key; // 插入成功
    }

    private int hash(int key) {
        return ((a * key + b) % p) % m;
    }

    private int hash(int key, int a, int b) {
        return ((a * key + b) % p) % m;
    }

    private int[] getAB(int mainIndex) {
        Random rand = new Random();
        if (table[mainIndex][0][0] == -1) {
            table[mainIndex][0] = new int[]{rand.nextInt(p - 1) + 1, rand.nextInt(p)};
        }
        return table[mainIndex][0];
    }

    public int[] search(int key) {
        int mainIndex = hash(key);
        int valueIndex = hash(key, getAB(mainIndex)[0], getAB(mainIndex)[1]);
        int maxAttempts = m;

        while (maxAttempts-- >= 0) {
            if (table[mainIndex][1][valueIndex] == key) {
                return new int[]{mainIndex, valueIndex}; // 找到返回主索引和輔助索引
            }
            if (table[mainIndex][1][valueIndex] == -1) {
                return new int[]{-1, -1}; // 沒有找到返回 -1
            }
            valueIndex = (valueIndex + 1) % m;
        }
        return new int[]{-1, -1}; // 如果超過最大嘗試仍未找到，返回 -1
    }

    private void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Index " + i + ": ");
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(Arrays.toString(table[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public static int getNextIntegerSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return (sqrt * sqrt == n) ? sqrt : sqrt + 1;
    }

    private static void searchAndPrint(PerfectHashing hashing, int searchKey) {
        int[] result = hashing.search(searchKey);
        if (result[0] != -1) {
            System.out.println("Searching for key " + searchKey + ": Found at mainIndex " + result[0] + ", valueIndex " + result[1]);
        } else {
            System.out.println("Searching for key " + searchKey + ": Not found");
        }
    }
}