package app.sort;

import java.util.Comparator;

public class ShellSort<T> implements Sorter<T> {
    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T key = array[i];
                int j = i;
                while (j >= gap && comparator.compare(array[j - gap], key) > 0) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = key;
            }
        }
    }
}
