package algorithm.ls13;

import java.util.Arrays;

public class DynamicTable {

    public static void main(String[] args) {
        DynamicTable dt = new DynamicTable(2);

        dt.insert(1);
        dt.insert(2);
        dt.printTable();

        dt.insert(3);
        dt.printTable();

        dt.delete(2);
        dt.printTable();

        dt.delete(0);
        dt.printTable();

        dt.delete(1);
    }

    private int[] table;
    private int size;

    public DynamicTable(int initialCapacity) {
        table = new int[initialCapacity];
        size = 0;
    }

    public void insert(int value) {
        if (size == table.length) {
            resize(table.length * 2);
        }
        table[size++] = value;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        for (int i = index; i < size - 1; i++) {
            table[i] = table[i + 1];
        }

        size--;
        table[size] = 0;
        if (size > 0 && size == table.length / 2) {
            resize(table.length / 2);
        }
    }

    private void resize(int newCapacity) {
        table = Arrays.copyOf(table, newCapacity);
    }

    public void printTable() {
        System.out.println("Table: " + Arrays.toString(Arrays.copyOf(table, size)));
        System.out.println("Capacity: " + table.length);
    }

}