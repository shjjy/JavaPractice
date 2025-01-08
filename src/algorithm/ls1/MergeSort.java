package algorithm.ls1;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] req = new int[]{8, 2, 4, 9, 3, 6};
        doSort(req);
        System.out.println(Arrays.toString(req));
    }

    private static void doSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] firstArr = Arrays.copyOfRange(arr, start, mid + 1);
        int[] secondArr = Arrays.copyOfRange(arr, mid + 1, end + 1);
        int i = 0, j = 0, k = start;
        while (i < firstArr.length && j < secondArr.length) {
            if (firstArr[i] <= secondArr[j]) {
                arr[k++] = firstArr[i++];
            } else {
                arr[k++] = secondArr[j++];
            }
        }
        while (i < firstArr.length) {
            arr[k++] = firstArr[i++];
        }
        while (j < secondArr.length) {
            arr[k++] = secondArr[j++];
        }
    }
}
