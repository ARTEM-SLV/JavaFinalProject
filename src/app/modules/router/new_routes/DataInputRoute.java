package app.modules.router.new_routes;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.modules.router.TablePrint;

public class DataInputRoute extends BaseRoute {
    private Router router;

    private Object[] objects; // Массив для хранения объектов
    private int step; // Индекс для отслеживания текущего заполненного элемента

    public DataInputRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        TablePrint.print(this.router.getState().optionsType, this.objects);
    }

    @Override
    public void execute(String args) throws Exception {
        var len = this.router.getState().Length;

        if (this.step >= len) {
            this.router.getState().Data = this.objects;
            this.router.navigateTo(this.pathToRoute);
            this.step = 0;
            return;
        }

        var selectOptions = this.router.getState().optionsType;
        var rawData = this.validationArgs(args, selectOptions);


        if (this.objects == null) {
            this.objects = new Object[len];
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
