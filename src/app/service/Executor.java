package app.service;

import app.search.Searcher;
import app.sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

public class Executor<T> implements IExecutor<T> {
    @Override
    public void sort(T[] arr, Sorter<T> sorter, Comparator comparator) {
        sorter.sort(arr, comparator);
        Arrays.stream(arr)
                .forEach(System.out::println);
    }

    @Override
    public void search(T[] arr, Searcher<T> searcher, Comparator comparator, T element) {
        int index = searcher.search(arr, element, comparator);
        System.out.println("Результат поиска элемента: " + (index >= 0 ? element + " найден в позиции " + index : "не найден"));
    }
}
