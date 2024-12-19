package ds.hw13.sortCompare.stable;

import ds.hw13.sortCompare.Sorter;

import java.util.Arrays;

//其實就是 counting sort，只是counting 指定0->9
//此外根據位數，每位做一次，個位、十位、百位
public class Radix implements Sorter {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void sort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();
        int length = data.length;
        int radix = 10;
        for(int exp = 1; max / exp > 0; exp *= radix) {
            new Counting().sortByRadix(data, length, exp, radix, 0);
        }
    }


    @Override
    public String getName() {
        return "Radix Sort";
    }
}
