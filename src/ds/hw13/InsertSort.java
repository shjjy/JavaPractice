package ds.hw13;

import static ds.util.ArrayUtil.printArray;

public class InsertSort {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{0, 2, 6, 14, 1, 8, 3, 7};
        System.out.print("original array: ");
        printArray(data);
        insertionSort(data);
        System.out.print("sorted array: ");
        printArray(data);

        String[] data2 = new String[]{"", "System", "Apple", "Disc", "Golden", "Cat", "Basic"};
        System.out.print("original array: ");
        printArray(data2);
        insertionSort(data2);
        System.out.print("sorted array: ");
        printArray(data2);
    }

    public static <T extends Comparable> void insertionSort(T[] data) {
        for (int i = 1; i < data.length; i++) {
            T current = data[i];
            int j = i - 1;
            while (j >= 0 && data[j].compareTo(current) > 0) {
                data[j + 1] = data[j--];
            }
            data[j + 1] = current;
        }
    }
}
