package algorithm.ls5;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 8, 4, 3, 2, 9, 1, 3};
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void countingSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();
        int range = max - min + 1;
        int[] count = new int[range];
        int[] result = new int[arr.length];
        for (int i : arr) {
            count[i - min]++;
        }
        for(int i=1; i<count.length; i++){
            count[i] += count[i-1];
        }
        for(int i = arr.length-1; i>=0; i--){
            int val = arr[i];
            int cIndex = val - min;
            result[count[cIndex]-1] = val;
            count[cIndex]--;
        }
        System.arraycopy(result, 0, arr, 0, arr.length);
    }
}
