package app.modules.router.records;

import java.io.Serializable;

public record RecordCar(String power, String model, String year) implements Serializable, ITableToString {

    @Override
    public String toString() {
        return "Car, " + "Сила: " + power + ", " + "Модель: " + model + ", " + "Год: " + year;
    }

    @Override
    public String toStringTable() {
        return String.format("%-30s %-15s %-10s", power, model, year);
    }
}
