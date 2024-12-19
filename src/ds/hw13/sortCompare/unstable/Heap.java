package ds.hw13.sortCompare.unstable;

import ds.hw13.sortCompare.Sorter;

import static ds.util.ArrayUtil.swap;

//heap 的精神為父大子小
//用 array 建置出完整的 heap，從中間就行，因為會往上堆疊
//此時最大值一定在 index 0
//因此0 跟最後一個元素交換，排除交換後最右邊的元素，剩下的重排 heap 再交換，以此類堆

public class Heap implements Sorter {
    @Override
    public void sort(int[] data) {
        data = new int[]{3, 5, 7, 1, 8, 2};
        int length = data.length - 1;
        int beginIndex = (length >> 1) - 1;
        for (int i = beginIndex; i >= 0; i--) {
            buildMaxHeap(data, i, length);
        }
        for (int i = length; i > 0; i--) {
            swap(data, 0, i);
            buildMaxHeap(data, 0, i - 1);
        }
    }

    private void buildMaxHeap(int[] data, int nowIndex, int length) {
        int lIndex = (nowIndex << 1) + 1;
        if (lIndex > length) return;
        int rIndex = lIndex + 1;
        int maxIndex = lIndex;
        if (rIndex <= length && data[rIndex] > data[lIndex]) {
            maxIndex = rIndex;
        }
        if (data[maxIndex] > data[nowIndex]) {
            swap(data, maxIndex, nowIndex);
            buildMaxHeap(data, maxIndex, length);
        }
    }

    @Override
    public String getName() {
        return "Heap Sort";
    }
}
