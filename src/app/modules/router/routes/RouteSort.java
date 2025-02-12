package app.modules.router.routes;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;

import app.modules.router.Router;

import app.service.Executor;
import app.service.IExecutor;
import app.sort.ShellSort;
import app.sort.Sorter;

import java.util.Comparator;


class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book c1, Book c2) {
        return Integer.compare(c1.getPageCount(), c2.getPageCount());
    }
}

class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return Integer.compare(c1.getYear(), c2.getYear());
    }
}

class VegetableComparator implements Comparator<Vegetable> {
    @Override
    public int compare(Vegetable c1, Vegetable c2) {
        return Double.compare(c1.getWeight(), c2.getWeight());
    }
}


public class RouteSort extends BaseRoute {
    private final Router router;

    public RouteSort(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Сортировка");
    }

    @Override
    public void execute(String args) throws Exception {
        var data = this.router.getState().Data;
        var selectOptionType = this.router.getState().optionsType;

        switch (selectOptionType) {
            case BOOK: {
                if (data instanceof Book[]) {
                    Sorter<Book> sorter = new ShellSort<>();
                    IExecutor<Book> IExecutor = new Executor<>(selectOptionType);
                    IExecutor.sort((Book[]) data, sorter);
                }
                break;
            }
            case CAR: {
                if (data instanceof Car[]) {
                    Sorter<Car> sorter = new ShellSort<>();
                    IExecutor<Car> IExecutor = new Executor<>(selectOptionType);
                    IExecutor.sort((Car[]) data, sorter);
                }
                break;
            }
            case VEGETATION: {
                if (data instanceof Vegetable[]) {
                    Sorter<Vegetable> sorter = new ShellSort<>();
                    IExecutor<Vegetable> IExecutor = new Executor<>(selectOptionType);
                    IExecutor.sort((Vegetable[]) data, sorter);
                }
                break;
            }
        }

        for (var item : data) {
            System.out.println(item);
        }

        this.router.navigateTo(this.pathToRoute);
    }
}
