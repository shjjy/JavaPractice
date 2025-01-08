package algorithm.ls6;

import java.util.Arrays;

public class Select {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 10, 13, 5, 8, 3, 2, 11};
        int n = 7;
        System.out.println(select(arr, n));
        int i = 2;
        System.out.println(select(arr, i));
    }

    public static int select(int[] arr, int n) {
        return selectHelper(arr, 0, arr.length - 1, n - 1);
    }

    private static int selectHelper(int[] arr, int left, int right, int targetIndex) {
        if (left == right) {
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        if (targetIndex == pivotIndex) {
            return arr[pivotIndex];
        } else if (targetIndex < pivotIndex) {
            return selectHelper(arr, left, pivotIndex - 1, targetIndex);
        } else {
            return selectHelper(arr, pivotIndex + 1, right, targetIndex);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int numElements = right - left + 1;
        int numGroups = (numElements + 4) / 5;

        int[] medians = new int[numGroups];
        for (int i = 0; i < numGroups; i++) {
            int groupStart = left + i * 5;
            int groupEnd = Math.min(groupStart + 4, right);
            Arrays.sort(arr, groupStart, groupEnd + 1);
            medians[i] = arr[(groupStart + groupEnd) / 2];
        }

        if (numGroups == 1) {
            return medians[0];
        }

        return selectHelper(medians, 0, numGroups - 1, numGroups / 2);
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        int pIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                pIndex = i;
                break;
            }
        }
        swap(arr, pIndex, left);
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
