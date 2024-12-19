package ds.hw13;


import static ds.util.ArrayUtil.printArray;

public class InsertionSortClass {

    public static void main(String[] args) {
        Integer[] data = new Integer[]{0, 2, 6, 14, 1, 8, 3, 7};
        System.out.print("original array: ");
        printArray(data);
        insertionSort(data, data.length - 1);
        System.out.print("sorted array: ");
        printArray(data);

        String[] data2 = new String[]{"", "System", "Apple", "Disc", "Golden", "Cat", "Basic"};
        System.out.print("original array: ");
        printArray(data2);
        insertionSort(data2, data2.length - 1);
        System.out.print("sorted array: ");
        printArray(data2);
    }

    private static <T extends Comparable<T>> void insertionSort(T[] arr, int length) {
        for (int i = 2; i <= length; i++) {
            T value = arr[i];
            insert(arr, value, i - 1);
        }
    }

    private static <T extends Comparable<T>> void insert(T[] arr, T value, int index) {
        arr[0] = value;
        while (value.compareTo(arr[index]) < 0) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[index + 1] = value;
    }


}
