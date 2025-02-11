package app.modules.router.routes;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.sort.ShellSort;


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

        switch (this.router.getState().optionsType) {
            case BOOK: {
                ShellSort<Book> sort = new ShellSort<>();
                var books = (Book[]) data;
                sort.sort(books, new BookComparator());
                break;
            }
            case CAR: {
                ShellSort<Car> sort = new ShellSort<>();
                if (data instanceof Car[]) {
                    sort.sort((Car[])data, new CarComparator());
                }
                break;
            }
            case VEGETATION: {
                ShellSort<Vegetable> sort = new ShellSort<>();
                var car = (Vegetable[]) data;
                sort.sort(car, new VegetableComparator());
                break;
            }
        }

        for (var item : data) {
            System.out.println(item);
        }

        this.router.navigateTo(this.pathToRoute);
    }
}
