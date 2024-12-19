package ds.hw4;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {

        Integer[] req2 = new Integer[]{7, 12, 5, 71, 34, 21, 13, 1, 8};
        selectionSortGen(req2);
        System.out.println(Arrays.toString(req2));
    }

    public static <T extends Comparable> void selectionSortGen(T[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                T tmp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
