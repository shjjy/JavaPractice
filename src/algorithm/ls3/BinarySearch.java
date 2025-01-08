package algorithm.ls3;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 8;
        System.out.print("binary search find position " + binarySearch(arr, target));
    }

    private static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) {
                end = mid - 1;
            }else if (arr[mid] < target) {
                start = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
