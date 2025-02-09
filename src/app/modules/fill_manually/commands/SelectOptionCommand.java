package app.modules.fill_manually.commands;

import app.modules.fill_manually.commands.interfaces.IDisplayView;
import app.modules.fill_manually.commands.interfaces.IHandlerInput;
import app.modules.fill_manually.commands.types.Type;
import java.util.Scanner;

public class SelectOptionCommand implements IDisplayView, IHandlerInput<Type> {

    private final Scanner scanner;

    public SelectOptionCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void displayView() {
        System.out.println("""
            Какой тип данных будем использовать?
            1 - Автомобиль
            2 - Книга
            3 - Корнеплод
            """);
    }

    @Override
    public Type handlerInput() {
        while (true) {
            try {

                var selectOption = Integer.parseInt(scanner.nextLine());

                switch (selectOption) {
                    case 1 -> {
                        return Type.CAR;
                    }
                    case 2 -> {
                        return Type.BOOK;
                    }
                    case 3 -> {
                        return Type.VEGETATION;
                    }
                }

                this.clearConsole();
            } catch (Exception e) {
                System.out.println("Ошибка: Введите число, а не строку. Попробуйте снова.");
            }
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
