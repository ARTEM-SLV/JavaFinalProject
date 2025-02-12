package app.modules.router.routes;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;

import app.modules.router.Router;

import app.service.Executor;
import app.service.IExecutor;
import app.sort.ShellSort;
import app.sort.Sorter;

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
