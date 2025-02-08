package app.service;

import app.model.Car;
import app.search.Searcher;
import app.sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

public class UniversalExecutor <T> implements Executor<T>{
    @Override
    public void sort(T[] arr, Sorter<T> sorter, Comparator comparator) {
        System.out.println("\nПосле сортировки:");

        sorter.sort(arr, comparator);
        Arrays.stream(arr)
                .forEach(System.out::println);
    }

    @Override
    public void search(T[] arr, Searcher<T> searcher, Comparator comparator, T element) {
        System.out.println("\nПоиск элемента:");

        int index = searcher.search(arr, element, comparator);
        System.out.println("Результат поиска элемента: " + (index >= 0 ? element : "не найден"));
    }
}
