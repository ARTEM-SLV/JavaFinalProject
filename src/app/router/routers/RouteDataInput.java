package app.router.routers;

import app.enums.Option;
import app.enums.StepsRouter;
import app.router.IRoute;
import app.router.Router;
import app.validation.Validator;

import java.util.Scanner;

public class RouteDataInput implements IRoute {
    private final Router router;

    private Object[] objects; // Массив для хранения объектов
    private int step; // Индекс для отслеживания текущего заполненного элемента

    public RouteDataInput(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        this.router.tablePrint(this.router.getContext().optionsType, this.objects);
    }

    @Override
    public void execute(Scanner scanner) throws Exception {
        var rawData = scanner.nextLine();
        if (rawData.isEmpty()){
            return;
        }

        var len = this.router.getContext().Length;
        var selectOptions = this.router.getContext().optionsType;

        if (this.objects == null) {
            this.objects = new Object[len];
        }

        var d = rawData.split(";");
        if (d.length < 3) {
            throw new Exception("Неверный формат данных. Введите три значения.");
        }

        try {
            switch (selectOptions) {
                case CAR -> this.objects[step] = Validator.checkCar(d);
                case BOOK -> this.objects[step] = Validator.checkBook(d);
                case VEGETATION -> this.objects[step] = Validator.checkVegetable(d);
            }

            this.step++;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

        if (this.step >= len) {
            this.router.getContext().Data = this.objects;
            this.router.step = StepsRouter.OPTIONS;
            this.router.option = Option.SORT;
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
