package app.modules.router;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

public class TablePrint {
    public static void print(OptionsType type, Object[] items) {

        var format = "%-40s %-40s %-40s%n";

        switch (type) {
            case BOOK:
                System.out.println("\033[1m***Книги***\033[0m");
                System.out.printf(format, "Автор", "Название", "Страницы");
                break;
            case CAR:
                System.out.println("\033[1m***Автомобили***\033[0m");
                System.out.printf(format, "Модель", "Мощность" , "Год");
                break;
            case VEGETATION:
                System.out.println("\033[1m***Корнеплод***\033[0m");
                System.out.printf(format, "Тип", "Вес", "Цвет");
                break;

        }
        System.out.printf("%-40s %-40s %-20s%n", "-".repeat(40), "-".repeat(40), "-".repeat(20));

        if (items != null) {
            for (var item : items) {
                if(item == null) return;
                switch (type) {

                    case CAR -> {
                        Car car = (Car) item;
                        System.out.printf(format, car.getModel(), car.getPower(), car.getYear());
                    }
                    case BOOK -> {
                        Book book = (Book) item;
                        System.out.printf(format, book.getAuthor(), book.getName(), book.getPageCount());
                    }
                    case VEGETATION -> {
                        Vegetable vegetable = (Vegetable) item;
                        System.out.printf(format, vegetable.getType(), vegetable.getWeight(), vegetable.getColor());
                    }
                }

            }
        }
        System.out.printf("%-40s %-40s %-20s%n", "-".repeat(40), "-".repeat(40), "-".repeat(20));
    }
}
