package ds.hw13.sortCompare.unstable;

import ds.hw13.sortCompare.Sorter;


import static ds.util.ArrayUtil.swap;

//拿一個值當基準(pivot)，最左邊0
//用兩個index紀錄，第一個，比基準小的 index(i)、第二個，目前計算到的 index(j)
//若資料小於基準，i, j 交換，同時 i+1(加大)比基準小的 index
//步驟1，找出 pivot，同時左邊皆小於p, 右邊大於 p
//步驟2 左右各自遞迴
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
