package app.modules.router.records;

import java.io.Serializable;

public record RecordVegetation(String type, String weight, String color) implements Serializable, ITableToString {

    @Override
    public String toString() {
        return "Vegetable, " + "Тип: " + type + ", " + "Вес: " + weight + ", " + "Цвет: " + color;
    }


    @Override
    public String toStringTable() {
        return String.format("%-30s %-15s %-10s", type, weight, color);
    }
}
