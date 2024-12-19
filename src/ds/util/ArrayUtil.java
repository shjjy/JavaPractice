package ds.util;

import java.util.Arrays;

public class ArrayUtil {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void printArray(T[] arr) {
        Arrays.stream(arr, 1, arr.length).forEach(i -> System.out.printf(i + "-> "));
        System.out.println();
    }
}
