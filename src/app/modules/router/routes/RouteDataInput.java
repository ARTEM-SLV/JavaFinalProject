package app.modules.router.routes;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.*;

public class RouteDataInput extends BaseRoute {
    private final Router router;

    private Object[] objects; // Массив для хранения объектов
    private int step; // Индекс для отслеживания текущего заполненного элемента

    public RouteDataInput(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        this.tablePrint(this.router.getState().optionsType, this.objects);
    }

    @Override
    public void execute(String args) throws Exception {
        var selectOptions = this.router.getState().optionsType;
        var rawData = this.validationArgs(args, selectOptions);

        var len = this.router.getState().Length;


        if (this.objects == null) {
           this.objects = new Object[len];
        }

        if (this.step >= len) {
            this.router.getState().Data = this.objects;
            this.router.navigateTo(this.pathToRoute);
            this.step = 0;
            return;
       }

        switch (selectOptions) {
            case CAR -> {
                this.objects[step] = new Car.CarBuilder()
                        .model(rawData[0])
                        .power(Integer.parseInt(rawData[1]))
                        .year(Integer.parseInt(rawData[2]))
                        .build();
            }
            case BOOK -> {
                this.objects[step] = new Book.BookBuilder()
                        .author(rawData[0])
                        .name(rawData[1])
                        .pageCount(Integer.parseInt(rawData[1]))
                        .build();
            }
            case VEGETATION -> {
                this.objects[step] = new Vegetable.VegetableBuilder()
                        .type(rawData[0])
                        .weight(Double.parseDouble(rawData[1]))
                        .color(rawData[2])
                        .build();
            }
        }

        this.step++;
    }

    private void tablePrint(OptionsType type, Object[] items) {

        switch (type) {
            case BOOK:
                System.out.printf("%-30s %-15s %-10s%n", "Автор", "Название", "Страницы");
                break;
            case CAR:
                System.out.printf("%-30s %-15s %-10s%n", "Модель", "Мощность" , "Год");
                break;
            case VEGETATION:
                System.out.printf("%-30s %-15s %-10s%n", "Тип", "Вес", "Цвет");
                break;

        }
        System.out.println("------------------------------------------");

        if (items != null) {
            for (var item : items) {
                if(item == null) return;
                switch (type) {

                    case CAR -> {
                        Car car = (Car) item;
                        System.out.printf("%-30s %-15d %-10d%n", car.getModel(), car.getPower(), car.getYear());
                    }
                    case BOOK -> {
                        Book book = (Book) item;
                        System.out.printf("%-30s %-15s %-10s%n", book.getAuthor(), book.getName(), book.getPageCount());
                    }
                    case VEGETATION -> {
                        Vegetable vegetable = (Vegetable) item;
                        System.out.printf("%-30s %-15s %-10s%n", vegetable.getType(), vegetable.getWeight(), vegetable.getColor());
                    }
                }

            }
        }
    }

    private String[] validationArgs(String args, OptionsType type) throws Exception {
        var items = args.split(",\\s*");

        if (items.length != 3) {
            switch (type) {
                case BOOK ->
                        throw new Exception("Неверный формат данных. author - строка, name - Строка, pages - Число ");
                case CAR -> throw new Exception("Неверный формат данных. Мощность - Число, Модель - Строка, Год - Число");
                case VEGETATION ->
                        throw new Exception("Неверный формат данных. Тип - Строка, Вес - Число, Цвет - Строка");
            }
        }


        switch (type) {
            case BOOK -> {
                if(this.isNumberString(items[0]) || this.isNumberString(items[1]) || !this.isNumberString(items[2])) {
                    throw new Exception("Неверный формат данных. Автор - Строка, Название - Строка, pages - Число");
                }
            }
            case CAR -> {
                if (this.isNumberString(items[0]) || !this.isNumberString(items[1]) || !this.isNumberString(items[2])) {
                    throw new Exception("Неверный формат данных. Модель - Строка, Мощность - Число, Год - Число");
                }
            }
            case VEGETATION -> {
                var check = this.isNumberString(items[0]) || !this.isNumberString(items[1]) || this.isNumberString(items[2]);
                if(check) {
                    throw new Exception("Неверный формат данных. Тип - Строка, Вес - Число, Цвет - Строка");
                }
            }
        }

        return items;
    }
}