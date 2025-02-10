package app.modules.router.routes;

import app.modules.router.EnumRoutes;
import app.modules.router.records.ITableToString;
import app.modules.router.records.RecordBook;
import app.modules.router.records.RecordCar;
import app.modules.router.records.RecordVegetation;
import app.modules.router.IRoute;
import app.modules.router.OptionsType;
import app.modules.router.Router;

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
        this.tablePrint(this.router.getContext().optionsType, this.objects);
    }

    @Override
    public void execute(Scanner scanner) throws Exception {
        var rawData = scanner.nextLine();
        var len = this.router.getContext().Length;
        var selectOptions = this.router.getContext().optionsType;

        if (this.objects == null) {
            this.objects = new Object[len];
        }

        if (this.step >= len) {
            this.router.getContext().Data = this.objects;
            this.router.navigateTo(EnumRoutes.WRITE_FILE);
            return;
        }


        var d = rawData.split(" ");

        if (d.length < 3) {
            throw new Exception("Неверный формат данных. Введите три значения.");
        }

        switch (selectOptions) {
            case CAR -> this.objects[step] = new RecordCar(d[0], d[1], d[2]);
            case BOOK -> this.objects[step] = new RecordBook(d[0], d[1], d[2]);
            case VEGETATION -> this.objects[step] = new RecordVegetation(d[0], d[1], d[2]);
        }

        this.step++;
    }

//    @Override
//    public void execute() {
//        var len = this.router.getContext().Length;
//        var selectOptions = this.router.getContext().optionsType;
//
//        Object[] objects = new Object[len];
//        var step = 0;
//
//        do {
//            var rawData = scanner.nextLine();
//            var d = rawData.split(" ");
//            if (d.length < 3) {
//               System.out.println("Неверный формат данных. Введите три значения.");
//               break;
//            }
//
//            switch (selectOptions) {
//                case CAR -> objects[step] = new RecordCar(d[0], d[1], d[2]);
//                case BOOK -> objects[step] = new RecordBook(d[0], d[1], d[2]);
//                case VEGETATION -> objects[step] = new RecordVegetation(d[0], d[1], d[2]);
//            }
//            this.clearConsole();
//            this.tablePrint(selectOptions, objects);
//            step++;
//            if (step >= len) {
//                this.router.getContext().Data = objects;
//                this.router.navigateTo("select/out/file");
//                break;
//            }
//        } while (true);
//
//    }

    private void tablePrint(OptionsType type, Object[] items) {

        switch (type) {
            case BOOK:
                System.out.printf("%-30s %-15s %-10s%n", "Автор", "Название", "Страницы");
                break;
            case CAR:
                System.out.printf("%-30s %-15s %-10s%n", "Мощность", "Модель", "Год");
                break;
            case VEGETATION:
                System.out.printf("%-30s %-15s %-10s%n", "Тип", "Вес", "Цвет");
                break;

        }
        System.out.println("------------------------------------------");

        if (items != null) {
            for (var item : items) {
                if (item != null) {
                    if (item instanceof ITableToString) {
                        System.out.println(((ITableToString) item).toStringTable());
                    }
                }
            }
        }
    }


    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
