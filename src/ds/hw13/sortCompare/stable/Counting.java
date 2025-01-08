package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

import java.util.Arrays;

//建立記數 Array，長度為所有值的 max - min + 1
//計算每個值出現的次數
//從尾巴開始排序，根據與 min 的距離與數量找出對應的位置
public class Counting implements Sorter {
    @Override
    public void sort(int[] data) {
        int max = Arrays.stream(data).max().orElse(Integer.MAX_VALUE);
        int min = Arrays.stream(data).min().orElse(Integer.MIN_VALUE);
        int range = max - min + 1;
        sortByRadix(data, data.length, 1, range, min);
    }

    public void sortByRadix(int[] data, int length, int exp, int radix, int offset) {
        int[] count = new int[radix];
        int[] result = new int[length];
        for (int i : data) {
            count[((i - offset) / exp) % radix]++;
        }
        for (int i = 1; i < radix; i++) {
            count[i] += count[i - 1];
        }
        for (int i = length - 1; i >= 0; i--) {
            int value = data[i];
            int cIndex = ((value - offset) / exp) % radix;
            result[count[cIndex] - 1] = value;
            count[cIndex]--;
        }
        System.arraycopy(result, 0, data, 0, length);
    }

    @Override
    public String getName() {
        return "Counting Sort";
    }
}
