package algorithm.ls1;

public class Unimodal {
    public static void main(String[] args) {
        int[] req = new int[]{1, 3, 5, 7, 4, 2};
        System.out.printf(String.valueOf(getUnimodalMaxValue(req)));
    }

    private static int getUnimodalMaxValue(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > arr[mid + 1]) {
                end = mid - 1;
            }else if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            }
        }
        return arr[start];
    }
}
