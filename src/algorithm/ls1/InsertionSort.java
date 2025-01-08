package algorithm.ls1;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] req = new int[]{8, 2, 4, 9, 3, 6};
        doSort(req);
        System.out.println(Arrays.toString(req));
    }

    private static void doSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                }else {
                    break;
                }
            }
        }
    }
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
