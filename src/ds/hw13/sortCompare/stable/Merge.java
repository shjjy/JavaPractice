package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

import java.util.Arrays;

//將 Array 分割到長度只有1
//開始 兩兩 merge， 由於此時左右各自排好序，只要誰小誰先出列即可
//需注意左右都會有長度不足2 的次方，需特別處理

public class Merge implements Sorter {
    @Override
    public void sort(int[] data) {
        sortByPart(data, 0, data.length - 1);
    }

    void sortByPart(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        sortByPart(data, left, mid);
        sortByPart(data, mid + 1, right);
        merge(data, left, mid, right);
    }

    private void merge(int[] data, int left, int mid, int right) {
        int[] leftArray = Arrays.copyOfRange(data, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(data, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                data[k++] = leftArray[i++];
            } else {
                data[k++] = rightArray[j++];
            }
        }

        while (i < leftArray.length) {
            data[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            data[k++] = rightArray[j++];
        }
    }


    @Override
    public String getName() {
        return "Merge Sort";
    }
}
