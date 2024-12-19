package ds.hw2;

public class Permutation {
    public static void main(String[] args) {
        permutation(new String[] { "a", "b", "c", "d"}, 0, 3);
    }

    private static void permutation(String[] arr, int start, int end) {
        if (start == end) {
            for(String str : arr) {
                System.out.print(str);
            }
            System.out.println();
        }else {
            for (int i = start; i <= end; i++) {
                swap(arr, start, i);
                permutation(arr, start + 1, end);
                swap(arr, start, i);
            }
        }
    }

    private  static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
