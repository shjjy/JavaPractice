package ds.hw13;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] data = new int[]{26, 5, 37, 1, 61, 11, 59, 15, 48, 19};
        quickSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));

        int[] data2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        quickSort(data2, 0, data2.length - 1);
        System.out.println(Arrays.toString(data));
        Arrays.sort(data);
        Arrays.sort(new Integer[1]);
    }

    private static void quickSort(int[] data, int left, int right) {
        if (left < right) {
            int i = left, j = right + 1, pivot = data[left];
            do {
                do {
                    i++;
                } while (data[i] < pivot);
                do {
                    j--;
                } while (data[j] > pivot);
                if (i < j) {
                    swap(data, i, j);
                }
            } while (i < j);
            swap(data, left, j);
            quickSort(data, left, j - 1);
            quickSort(data, j + 1, right);
        }
    }

    private static void quickSort2(int[] data, int left, int right) {
        if (left < right) {
            int i = left, j = right + 1, pivot = getMedianIndex(data, left, right);
            do {
                do {
                    i++;
                } while (data[i] < pivot);
                do {
                    j--;
                } while (data[j] > pivot);
                if (i < j) {
                    swap(data, i, j);
                }
            } while (i < j);
            swap(data, left, j);
            quickSort(data, left, j - 1);
            quickSort(data, j + 1, right);
        }
    }

    private static int getMedianIndex(int[] data, int left, int right) {
        int mid = left + (right - left) / 2;
        if (data[left] > data[mid]) {
            swap(data, left, mid);
        }
        if (data[left] > data[right]) {
            swap(data, left, right);
        }
        if (data[mid] > data[right]) {
            swap(data, right, mid);
        }
        return mid;
    }

    private static void swap(int[] data, int i, int j) {
        Integer temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
