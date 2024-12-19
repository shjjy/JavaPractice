package ds.hw13.sortCompare.unstable;

import ds.hw13.sortCompare.Sorter;
import ds.util.ArrayUtil;

//由左至右找出最小的放最左邊，此時左邊為最小
//往右以此類推

public class Selection implements Sorter {
    @Override
    public void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ArrayUtil.swap(data, minIndex, i);
            }
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }
}
