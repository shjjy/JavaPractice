package ds.hw2;

public class BinarySearch {
    public static void main(String[] args) {
        int[] req = new int[]{1, 4, 5, 7, 9, 10, 12, 15};
        System.out.println("binary search find position " + binarySearch(req, 9));
        System.out.println("binary search recursive  find position "
                + binarySearchR(req, 9, 0, req.length - 1));
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            }else if (arr[mid] < target) {
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    private static int binarySearchR(int[] arr, int target, int left, int right) {
        if(left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                return binarySearchR(arr, target, left, mid - 1);
            }else if (arr[mid] < target) {
                return binarySearchR(arr, target, mid + 1, right);
            }else{
                return mid;
            }
        }
        return -1;
    }

}
