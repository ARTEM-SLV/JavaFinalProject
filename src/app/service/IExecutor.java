package app.service;

import app.service.search.Searcher;
import app.service.sort.Sorter;

public interface IExecutor<T> {
    void sort(T[] arr, Sorter<T> sorter);
    void search(T[] arr, Searcher<T> searcher, T element);
}
