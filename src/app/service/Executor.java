package app.service;

import app.model.Car;
import app.search.Searcher;
import app.sort.Sorter;

import java.util.Comparator;

public interface Executor<T> {
    void sort(T[] arr, Sorter<T> sorter, Comparator comparator);
    void search(T[] arr, Searcher<T> searcher, Comparator comparator, int finderElement);
}
