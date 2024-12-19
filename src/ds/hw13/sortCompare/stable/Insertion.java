package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

//從左到右檢查，將未排序的元素插入到已排序陣列中的正確位置(由右往左)
public class Insertion implements Sorter {
    @Override
    public void sort(int[] data) {
        sortByPart(data, 0, data.length - 1);
    }

    void sortByPart(int[] data, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int temp = data[i];
            int j = i - 1;
            while(j >= start && data[j] > temp) {
                data[j + 1] = data[j--];
            }
            data[j + 1] = temp;
        }
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
