package app.modules.router.routes;

import app.modules.router.records.RecordBook;
import app.modules.router.records.RecordCar;
import app.modules.router.records.RecordVegetation;
import app.modules.router.IRoute;
import app.modules.router.OptionsType;
import app.modules.router.Router;

import java.util.Scanner;

public class RouteDataInput implements IRoute {
    private final Router router;
    private final Scanner scanner;

    public RouteDataInput(Router router, Scanner sc) {
        this.router = router;
        this.scanner = sc;
    }

    @Override
    public void render() {
        this.tablePrint(this.router.getContext().optionsType, null);
    }

    @Override
    public void execute() {
        this.render();
        var len = this.router.getContext().Length;
        var selectOptions = this.router.getContext().optionsType;

        Object[] objects = new Object[len];
        var step = 0;

        do {
            var rawData = scanner.nextLine();
            var d = rawData.split(" ");
            if (d.length < 3) {
               System.out.println("Неверный формат данных. Введите три значения.");
               break;
            }

            switch (selectOptions) {
                case CAR -> objects[step] = new RecordCar(d[0], d[1], d[2]);
                case BOOK -> objects[step] = new RecordBook(d[0], d[1], d[2]);
                case VEGETATION -> objects[step] = new RecordVegetation(d[0], d[1], d[2]);
            }
            this.clearConsole();
            this.tablePrint(selectOptions, objects);
            step++;
            if (step >= len) {
                this.router.getContext().Data = objects;
                this.router.navigateTo("select/out/file");
                break;
            }
        } while (true);

//        var count = 0;
//
//        T[] types = (T[]) new Object[this.length];
//
//        do {
//            var rawData = scanner.nextLine();
//            System.out.print(rawData);
//            var d = rawData.split(" ");
//            if (d.length < 3) {
//                System.out.println("Неверный формат данных. Введите три значения.");
//                continue;
//            }
//
//            // Используем функцию для создания объекта типа T
//            types[count] = creator.apply(d);
//
//            this.clearConsole();
//            this.tablePrint(types);
//
//            count++;
//            if (count >= length) {
//                return types;
//            }
//        } while (true);
    }

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
                    System.out.println(item);
                }
            }
        }
    }

//    private void tablePrint(/*T[] items*/) {
//
//        switch (this.type) {
//            case BOOK:
//                System.out.printf("%-30s %-15s %-10s%n", "Автор", "Название", "Страницы");
//
//                break;
//            case CAR:
//                System.out.printf("%-30s %-15s %-10s%n", "Мощность", "Модель", "Год");
//                break;
//            case VEGETATION:
//                System.out.printf("%-30s %-15s %-10s%n", "Тип", "Вес", "Цвет");
//                break;
//
//        }
//        System.out.println("------------------------------------------");
//
//        if (items != null) {
//            for (var item : items) {
//                if (item != null) {
//                    System.out.println(item);
//                }
//            }
//        }
//    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
