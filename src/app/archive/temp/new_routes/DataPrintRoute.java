package app.archive.temp.new_routes;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.core.BaseRoute;
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

        this.printDataIfNotEmpty(OptionsType.CAR, dataCars);
        this.printDataIfNotEmpty(OptionsType.BOOK, dataBooks);
        this.printDataIfNotEmpty(OptionsType.VEGETATION, dataVegetable);

        System.out.println("Нажмите что бы продолжить.");
    }

    @Override
    public void execute(String args) throws Exception {
        this.router.navigateTo(this.pathToRoute);
    }

    private void printDataIfNotEmpty(OptionsType type, Object[] data) {
        if (data != null && data.length > 0) {
            TablePrint.print(type, data);
        }
    }
}
