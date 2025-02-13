package app.modules.router.new_routes;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.modules.router.TablePrint;

import java.util.Arrays;

public class DataPrintRoute extends BaseRoute {

    private final Router router;

    public DataPrintRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {

        var selectType = this.router.getState().optionsType;
        var data = this.router.getState().Data;

        if (data == null || data.length < 1) {
            this.router.navigateTo(this.pathToRoute);
            System.out.println("Нажмите что бы продолжить.");
            return;
        }

        var dataCars = Arrays.stream(data).filter((car) -> car instanceof Car).toArray();
        var dataBooks = Arrays.stream(data).filter((book) -> book instanceof Book).toArray();
        var dataVegetable = Arrays.stream(data).filter((vegetable) -> vegetable instanceof Vegetable).toArray();

        if (dataCars != null || dataCars.length > 0) {
            TablePrint.print(OptionsType.CAR, dataCars);
        }

        if (dataBooks != null || dataBooks.length > 0) {
            TablePrint.print(OptionsType.BOOK, dataBooks);
        }

        if (dataVegetable != null || dataVegetable.length > 0) {
            TablePrint.print(OptionsType.VEGETATION, dataVegetable);
        }

//        if (selectType == null) {
//            this.router.navigateTo(this.pathToRoute);
//        } else {
//            TablePrint.print(this.router.getState().optionsType, this.router.getState().Data);
//        }

        System.out.println("Нажмите что бы продолжить.");
    }

    @Override
    public void execute(String args) throws Exception {
        this.router.navigateTo(this.pathToRoute);
    }
}
