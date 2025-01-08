package algorithm.ls8;

import java.util.Arrays;
import java.util.Random;

public class UniversalHashing {

    public static void main(String[] args) {
        int[] testData = {12, 25, 36, 20, 30, 8, 42, 10, 15, 12};
        UniversalHashing hashing = new UniversalHashing(10); // 設定哈希表大小為 10

        for (int key : testData) {
            hashing.insert(key);
        }

        hashing.printTable();
        System.out.println("Searching for key 20, Index is : " + hashing.search(20));
        System.out.println("Searching for key 50, Index is : " + hashing.search(50));
    }

    private final int p;
    private final int m;
    private int a, b;

    private int[] table;

    public UniversalHashing(int size) {
        this.m = size; // 設定哈希表大小
        this.p = findNextPrime(m * 2);
        table = new int[m];
        Arrays.fill(table, -1);
        Random rand = new Random();
        this.a = rand.nextInt(p - 1) + 1;
        this.b = rand.nextInt(p);
    }

    // h(x) = ((a * x + b) % p) % m
    private int hash(int key) {
        return ((a * key + b) % p) % m;
    }

    private void insert(int key) {
        int index = hash(key);
        int maxAttempts = m;
        while (maxAttempts-- >= 0 && table[index] != -1) {
            if (table[index] == key) {
                return;
            }
            index = (index + 1) % m;
        }
        if (maxAttempts == -1) {
            throw new IllegalStateException("Hash table is full, cannot insert new element.");
        }
        table[index] = key; // 插入成功
    }

    private int search(int key) {
        int index = hash(key);
        int maxAttempts = m; // 限制搜尋次數為哈希表大小
        while (maxAttempts-- > 0 && table[index] != -1) {
            if (table[index] == key) {
                return index;
            }
            index = (index + 1) % m;
        }

        return -1;
    }

    private int findNextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private void printTable() {
        for (int i = 0; i < m; i++) {
            System.out.println("Index " + i + ": " + table[i]);
        }
    }

}