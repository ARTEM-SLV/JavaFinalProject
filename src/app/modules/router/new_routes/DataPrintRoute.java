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

        System.out.println("Текущие данные:");
        System.out.println(Arrays.toString(this.router.getState().Data));
        System.out.println("");

        /*var selectType = this.router.getState().optionsType;
        var data = this.router.getState().Data;

        if (selectType == null) {
            this.router.navigateTo(this.pathToRoute);
        } else {
            TablePrint.print(this.router.getState().optionsType, this.router.getState().Data);
        }*/

        System.out.println("Нажмите что бы продолжить.");
    }

    @Override
    public void execute(String args) throws Exception {
        this.router.navigateTo(this.pathToRoute);
    }
}
