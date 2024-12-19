package ds.hw13.sortCompare.unstable;

import ds.hw13.sortCompare.Sorter;


import static ds.util.ArrayUtil.swap;

public class Quick implements Sorter {
    @Override
    public void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private void sort(int[] data, int left, int right) {
        if (left < right) {
            int pIndex = partition(data, left, right);
            sort(data, left, pIndex - 1);
            sort(data, pIndex + 1, right);
        }
    }

    private int partition(int[] data, int left, int right) {
        int pivot = data[left];
        int i = left + 1;
        for (int j = i; j <= right; j++) {
            if (data[j] <= pivot) {
                swap(data, i, j);
                i++;
            }
        }
        swap(data, left, i - 1);
        return i - 1;
    }


    @Override
    public String getName() {
        return "Quick Sort";
    }
}
