package app.modules.fill_manually.commands;

import app.modules.fill_manually.commands.interfaces.IDisplayView;
import app.modules.fill_manually.commands.interfaces.IHandlerInput;
import app.modules.fill_manually.commands.types.Type;
import java.util.Scanner;
import java.util.function.Function;

public class InputDataCommand<T> implements IDisplayView, IHandlerInput<T[]> {

    private final Scanner scanner;
    private Integer length;
    private final Function<String[], T> creator;
    private final Type type;

    public InputDataCommand(Scanner scanner, Function<String[], T> creator, Type type) {
        this.scanner = scanner;
        this.creator = creator;
        this.type = type;
    }

    public void setLength(int value) {
        this.length = value;
    }

    @Override
    public void displayView() {
        this.tablePrint(null);
    }

    @Override
    public T[] handlerInput() {
        var count = 0;

        T[] types = (T[]) new Object[this.length];

        do {
            var rawData = scanner.nextLine();
            System.out.print(rawData);
            var d = rawData.split(" ");
            if (d.length < 3) {
                System.out.println("Неверный формат данных. Введите три значения.");
                continue;
            }

            // Используем функцию для создания объекта типа T
            types[count] = creator.apply(d);

            this.clearConsole();
            this.tablePrint(types);

            count++;
            if (count >= length) {
                return types;
            }
        } while (true);
    }

    private void tablePrint(T[] items) {

        switch (this.type) {
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

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}