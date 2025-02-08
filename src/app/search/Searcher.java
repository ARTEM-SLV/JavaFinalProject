package app.search;

import java.util.Comparator;

public interface Searcher<T> {
    int search(T[] array, T key, Comparator<T> comparator);
}
