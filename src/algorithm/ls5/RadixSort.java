package algorithm.ls5;

import ds.hw13.sortCompare.stable.Counting;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{324, 155, 931, 288, 614, 261, 312, 912, 137, 333};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int max = Arrays.stream(arr).max().getAsInt();
        int radix = 10;
        for (int exp = 1; max / exp > 0; exp *= radix) {
            countingSortForRadix(arr, arr.length, exp, radix);
        }
    }

    private static void countingSortForRadix(int[] data, int length, int exp, int radix) {
        int[] count = new int[radix];
        int[] result = new int[length];
        for (int i : data) {
            count[(i / exp) % radix]++;
        }
        for (int i = 1; i < radix; i++) {
            count[i] += count[i - 1];
        }
        for (int i = length - 1; i >= 0; i--) {
            int value = data[i];
            int cIndex = (value / exp) % radix;
            result[count[cIndex] - 1] = value;
            count[cIndex]--;
        }
        System.arraycopy(result, 0, data, 0, length);
    }
}
