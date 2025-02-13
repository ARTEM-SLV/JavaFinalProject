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

        search(this.router.getState().Data, selectOptionType, args);
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
