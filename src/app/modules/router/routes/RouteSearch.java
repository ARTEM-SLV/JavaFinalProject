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
        var printMessage = this.router.getState().optionsType == null ? "Тип не выбран." : this.router.getState().optionsType;
        System.out.println("""
                Поиск:
                """ + printMessage + "\n" +  "Нажмите любую клавишу для продолжения.");
    }

    @Override
    public void execute(String args) throws Exception {
        var data = this.router.getState().Data;
        var selectOptionType = this.router.getState().optionsType;
        if (data == null || data.length < 1) {
            throw new BackException("Массив пустой.");
        }

        if (selectOptionType == null) {
            throw new BackException("Массив пустой.");
        }

        search(data, selectOptionType, args);
        this.router.navigateTo(this.pathToRoute);
    }

    private void search(Object[] data, OptionsType type, String args) throws Exception {
        String[] values = args.split("[,:;.]+");

        switch (type) {
            case CAR -> {
                var car = Validator.checkCar(values);
                carSearch((Car[]) data, car, type);
            }
            case BOOK -> {
                var book = Validator.checkBook(values);
                bookSearch((Book[]) data, book, type);
            }
            case VEGETATION -> {
                var vegetable = Validator.checkVegetable(values);
                vegetableSearch((Vegetable []) data, vegetable, type);
            }
        }

    }

    private void carSearch(Car[] cars, Car car, OptionsType type) {
        Searcher<Car> searcher = new BinarySearch<>();
        IExecutor<Car> IExecutor = new Executor<>(type);
        IExecutor.search(cars, searcher, car);
    }

    private void bookSearch(Book[] books, Book book, OptionsType type) {
        Searcher<Book> searcher = new BinarySearch<>();
        IExecutor<Book> IExecutor = new Executor<>(type);
        IExecutor.search(books, searcher, book);
    }

    private void vegetableSearch(Vegetable[] vegetables, Vegetable vegetable, OptionsType type) {
        Searcher<Vegetable> searcher = new BinarySearch<>();
        IExecutor<Vegetable> IExecutor = new Executor<>(type);
        IExecutor.search(vegetables, searcher, vegetable);
    }
}
