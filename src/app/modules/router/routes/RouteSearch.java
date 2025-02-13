package app.modules.router.routes;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.BackException;
import app.search.BinarySearch;
import app.search.Searcher;
import app.service.Executor;
import app.service.IExecutor;
import app.validation.Validator;

public class RouteSearch extends BaseRoute {

    private final Router router;

    public RouteSearch(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Поиск");
        System.out.println("Введите искомый объект: ");
    }

    @Override
    public void execute(String args) throws Exception {

        var selectOptionType = this.router.getState().optionsType;
        if (this.router.getState().Data == null || this.router.getState().Data.length < 1) {
            throw new BackException("Массив пустой.");
        }

        if (selectOptionType == null) {
            throw new BackException("Тип не выбран.");
        }

        int carCount = 0;
        int bookCount = 0;
        int vegetableCount = 0;

        // Подсчет на количество автомобилей, книг и овощей
        for (Object obj : this.router.getState().Data) {
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
        for (Object obj : this.router.getState().Data) {
            if (obj instanceof Car) {
                cars[carIndex++] = (Car) obj;
            } else if (obj instanceof Book) {
                books[bookIndex++] = (Book) obj;
            } else if (obj instanceof Vegetable) {
                vegetables[vegetableIndex++] = (Vegetable) obj;
            }
        }

        switch (selectOptionType) {
            case CAR -> {
                if (cars.length > 0) {
                    search(cars, selectOptionType, args);
                }
                else {
                    System.out.println("Автомобилей нет");
                }
            }
            case BOOK -> {
                if (books.length > 0) {
                    search(books, selectOptionType, args);
                }
                else {
                    System.out.println("Книг нет");
                }
            }
            case VEGETATION -> {
                if (vegetables.length > 0) {
                    search(vegetables, selectOptionType, args);
                }
                else {
                    System.out.println("Корнеплодов нет");
                }
            }
        }


        this.router.navigateTo(this.pathToRoute);
    }

    private void search(Object[] data, OptionsType type, String args) throws Exception {
        String[] values = args.split("[,:;.]+");

        switch (type) {
            case CAR -> {
                var car = Validator.checkCar(values);
                searchByType(data, car, type);
            }
            case BOOK -> {
                var book = Validator.checkBook(values);
                searchByType(data, book, type);
            }
            case VEGETATION -> {
                var vegetable = Validator.checkVegetable(values);
                searchByType(data, vegetable, type);
            }
        }

    }

    private <U, E extends OptionsType> void searchByType(U[] arr, U obj, E type) {
        Searcher<U> searcher = new BinarySearch<>();
        IExecutor<U> IExecutor = new Executor<>(type);
        IExecutor.search(arr, searcher, obj);
    }
}
