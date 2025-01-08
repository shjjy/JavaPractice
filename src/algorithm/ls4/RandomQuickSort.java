package algorithm.ls4;

import java.util.Arrays;

public class RandomQuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 10, 13, 5, 8, 3, 2, 11};
        randomQuickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void randomQuickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pIndex = partition(arr, left, right);
            randomQuickSort(arr, left, pIndex - 1);
            randomQuickSort(arr, pIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pIndex = left + (int) (Math.random() * (right - left + 1));
        swap(arr, pIndex, left);
        int pivot = arr[left];
        int i = left;
        for (int j = i + 1; j <= right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i + 1, j);
                i++;
            }
        }
        swap(arr, left, i);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
