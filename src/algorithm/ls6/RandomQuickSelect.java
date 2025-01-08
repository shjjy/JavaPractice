package algorithm.ls6;

public class RandomQuickSelect {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 10, 13, 5, 8, 3, 2, 11};
        int n = 7;
        System.out.println(findNthLargest(arr, n));
        int i = 2;
        System.out.println(findNthLargest(arr, i));
    }

    private static int findNthLargest(int[] arr, int n) {
        return ranQuickSelect(arr, 0, arr.length - 1, n - 1);
    }

    private static int ranQuickSelect(int[] arr, int left, int right, int target) {
        if (left == right) {
            return arr[left];
        }
        int pIndex = partition(arr, left, right);
        if (pIndex == target) {
            return arr[pIndex];
        } else if (pIndex > target) {
            return ranQuickSelect(arr, left, pIndex - 1, target);
        } else {
            return ranQuickSelect(arr, pIndex + 1, right, target);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pIndex = left + (int) (Math.random() * (right - left + 1));
        swap(arr, pIndex, left);
        int pivot = arr[left];
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
