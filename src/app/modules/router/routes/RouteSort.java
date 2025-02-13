package app.modules.router.routes;

import app.enums.Option;
import app.enums.OptionsType;
import app.enums.StepsRouter;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;

import app.modules.router.Router;

import app.modules.router.exeptions.BackException;
import app.service.Executor;
import app.service.IExecutor;
import app.service.UniversalComparator;
import app.sort.ShellSort;
import app.sort.Sorter;

import java.io.Serializable;
import java.util.Comparator;

public class RouteSort extends BaseRoute {
    private final Router router;

    public RouteSort(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Сортировка.");
        System.out.println("Нажмите любую клавишу для продолжения.");
    }

    @Override
    public void execute(String args) throws Exception {
        Object[] data = router.getState().Data;

        if (data == null || data.length < 1) {
            throw new BackException("Массив пуст.");
        }

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
        this.sortArray(new Executor<>(OptionsType.CAR), cars);
        this.sortArray( new Executor<>(OptionsType.BOOK), books);
        this.sortArray(new Executor<>(OptionsType.VEGETATION), vegetables);


        //Объединение массивов
        Object[] mergedArray = new Object[data.length];
        System.arraycopy(cars, 0, mergedArray, 0, cars.length);
        System.arraycopy(books, 0, mergedArray, cars.length, books.length);
        System.arraycopy(vegetables, 0, mergedArray, cars.length + books.length, vegetables.length);

        //Сохранение объединенного массива в контекст
        this.router.getState().Data = mergedArray;

        this.router.navigateTo(this.pathToRoute);
    }

    // Сортирует массив с помощью заданного компаратора
    private void sortArray(IExecutor<Serializable> executor, Object[] array) {
        Sorter<Serializable> sorter = new ShellSort<>();
        executor.sort((Serializable[]) array, sorter);
    }
}
