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
            throw new BackException("Тип не выбран.");
        }

        search(data, selectOptionType, args);
        this.router.navigateTo(this.pathToRoute);
    }

    private void search(Object[] data, OptionsType type, String args) throws Exception {
        String[] values = args.split("[,:;.]+");

        switch (type) {
            case CAR -> {
                var car = Validator.checkCar(values);
                searchByType((Car[]) data, car, type);
            }
            case BOOK -> {
                var book = Validator.checkBook(values);
                searchByType((Book[]) data, book, type);
            }
            case VEGETATION -> {
                var vegetable = Validator.checkVegetable(values);
                searchByType((Vegetable []) data, vegetable, type);
            }
        }

    }

    private <U, E extends OptionsType> void searchByType(U[] arr, U obj, E type) {
        Searcher<U> searcher = new BinarySearch<>();
        IExecutor<U> IExecutor = new Executor<>(type);
        IExecutor.search(arr, searcher, obj);
    }
}
