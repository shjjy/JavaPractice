package ds.hw13.sortCompare;

import ds.hw13.sortCompare.stable.*;
import ds.hw13.sortCompare.unstable.Heap;
import ds.hw13.sortCompare.unstable.Quick;
import ds.hw13.sortCompare.unstable.Selection;

import java.util.Random;

public class SortAlgCompare {

    public static void main(String[] args) {
        //List Sort：Insert, Merge 可以用 Linked List 先記錄前後，再用 List Sort 交換
        // first 4
        //  I  1 2 3 4
        //  V  3 2 4 1  => 1 2 3 4
        // NI  3 1 0 2     2 3 4 0
        // PI  2 4 1 0     0 1 2 3
        //Table Sort：所有演算法適用，找出循環群，每群用一個Tmp 再循環移動直到正確位置
        // I 1 2 3 4
        // V 4 2 3 1  => {1, 4}, {2}, {3}
        // P 4 2 3 1
        Sorter[] sorters = new Sorter[]{
                new Bubble(),
                new Insertion(),
                new Counting(),
                new Radix(),
                new Merge(),
                new BinaryTree(),
                new Tim(),
                new Selection(),
                new Heap(),
                new Quick(),
        };
        int[] dataSize = new int[] {1000, 10000, 100000};
//        int[] dataSize = new int[] {5, 40};

        System.out.printf("%-20s", "Data Size");
        for(Sorter sorter : sorters) {
            System.out.printf("%-20s", sorter.getName());
        }
        System.out.println();

        for (int size : dataSize) {
            int[] data = getRandomData(size);
            System.out.printf("%-20s", size);
            for (Sorter sorter : sorters) {
                System.out.printf("%-20s", getExecuteTIme(sorter, data.clone()));
            }
            System.out.println();
        }


    }

    private static double getExecuteTIme(Sorter sorter, int[] data) {
        long startTime = System.nanoTime();
        sorter.sort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    private static int[] getRandomData(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(data.length);
        }
        return data;
    }
}
