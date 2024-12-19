package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

import static ds.util.ArrayUtil.swap;

//每輪比較並交換：每一輪內，相鄰兩個元素比較，如果前一個大於後一個，就交換
//逐漸向上冒泡：每輪將最大的元素「冒泡」到陣列的末尾，逐步縮減未排序的範圍

public class Bubble implements Sorter {
    @Override
    public void sort(int[] data) {
        int currentIndex = data.length - 1;
        while (currentIndex > 1) {
            boolean swapFlag = false;
            for (int j = 0; j < currentIndex; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    swapFlag = true;
                }
            }
            if (!swapFlag) {
                break;
            }
            currentIndex--;
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
