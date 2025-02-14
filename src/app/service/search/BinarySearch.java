package app.service.search;

import java.util.Comparator;

public class BinarySearch<T> implements Searcher<T> {
    public int search(T[] array, T key, Comparator<T> comparator) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = comparator.compare(array[mid], key);
            if (cmp == 0) {
                return mid;
            }

            if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
