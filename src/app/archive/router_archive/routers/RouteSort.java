package app.archive.router_archive.routers;

import app.enums.Option;
import app.enums.OptionsType;
import app.enums.StepsRouter;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.router.ExitException;
import app.router.IRoute;
import app.router.Router;
import app.service.IExecutor;
import app.service.UniversalComparator;
import app.service.Executor;
import app.sort.ShellSort;
import app.sort.Sorter;

import java.util.Comparator;
import java.util.Scanner;

public class RouteSort<T> implements IRoute<T> {
    private final Router router;

    public RouteSort(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Сортировка массива:");
    }

    @Override
    public void execute(Scanner scanner) throws ExitException {
        Object[] data = router.getContext().Data;

        int carCount = 0;
        int bookCount = 0;
        int vegetableCount = 0;

        // Подсчет на количество автомобилей, книг и овощей
        for (Object obj : data) {
            if (obj instanceof Car) {
                carCount++;
            } else if (obj instanceof Book) {
                bookCount++;
            } else if (obj instanceof Vegetable) {
                vegetableCount++;
            }
        }

        // Создание массивов для каждого типа
        Car[] cars = new Car[carCount];
        Book[] books = new Book[bookCount];
        Vegetable[] vegetables = new Vegetable[vegetableCount];

        int carIndex = 0;
        int bookIndex = 0;
        int vegetableIndex = 0;

        // Заполнение данных в соответствующие массивы
        for (Object obj : data) {
            if (obj instanceof Car) {
                cars[carIndex++] = (Car) obj;
            } else if (obj instanceof Book) {
                books[bookIndex++] = (Book) obj;
            } else if (obj instanceof Vegetable) {
                vegetables[vegetableIndex++] = (Vegetable) obj;
            }
        }

        //Сортировка объектов
        this.sortArray(UniversalComparator.getComparator(OptionsType.CAR), new Executor<>(OptionsType.CAR), cars);
        this.sortArray(UniversalComparator.getComparator(OptionsType.BOOK), new Executor<>(OptionsType.BOOK), books);
        this.sortArray(UniversalComparator.getComparator(OptionsType.VEGETATION), new Executor<>(OptionsType.VEGETATION), vegetables);


        //Объединение массивов
        Object[] mergedArray = new Object[data.length];
        System.arraycopy(cars, 0, mergedArray, 0, cars.length);
        System.arraycopy(books, 0, mergedArray, cars.length, books.length);
        System.arraycopy(vegetables, 0, mergedArray, cars.length + books.length, vegetables.length);

        //Сохранение объединенного массива в контекст
        this.router.getContext().Data = mergedArray;

        this.router.step = StepsRouter.OPTIONS;
        this.router.option = Option.SEARCH;
    }

    // Сортирует массив с помощью заданного компаратора
    private void sortArray(Comparator comparator, IExecutor<T> executor, Object[] array) {
        Sorter<T> sorter = new ShellSort<>();
        executor.sort((T[]) array, sorter);
    }
}
