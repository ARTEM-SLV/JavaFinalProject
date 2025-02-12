package app.utility;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

import java.io.Serializable;

public class ObjectSerializer {

    //Десериализует объект из файла в память
    public static Serializable deserialize(String[] data) {

        return switch (data[0].toLowerCase()) {
            case "car" -> new Car.CarBuilder()
                    .power(Integer.parseInt(data[1]))
                    .model(data[2])
                    .year(Integer.parseInt(data[3]))
                    .build();
            case "book" -> new Book.BookBuilder()
                    .author(data[1])
                    .name(data[2])
                    .pageCount(Integer.parseInt(data[3]))
                    .build();
            case "vegetable" -> new Vegetable.VegetableBuilder()
                    .type(data[1])
                    .weight(Double.parseDouble(data[2]))
                    .color(data[3])
                    .build();
            default -> null;
        };
    }
}
