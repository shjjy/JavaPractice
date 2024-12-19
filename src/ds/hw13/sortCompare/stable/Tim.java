package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

import java.util.Arrays;

//32 個元素分一段，每段作 insertion sort，注意最後一段長度會不滿 32
//最後用 merge sort 合併所有段
//要注意 right可能不會滿

public class Tim implements Sorter {

    private final int _RUN = 32;

    @Override
    public void sort(int[] data) {
        int length = data.length;
        for (int i = 0; i < length; i += _RUN) {
            new Insertion().sortByPart(data, i, Math.min(i + _RUN - 1, length - 1));
        }

        for (int size = _RUN; size < length; size *= 2) {
            for (int left = 0; left < length; left += 2 * size) {
                int mid = Math.min(left + size - 1, length - 1);
                int right = Math.min(left + 2 * size - 1, length - 1);
                if (mid < right) {
                    new Merge().sortByPart(data, left, right);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Tim Sort";
    }
}
