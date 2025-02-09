package app.modules.fill_manually.commands;

import app.modules.fill_manually.commands.interfaces.IDisplayView;
import app.modules.fill_manually.commands.interfaces.IHandlerInput;
import java.util.Scanner;

public class ReadLengthCommand implements IDisplayView, IHandlerInput<Integer> {

    private final Scanner scanner;

    public ReadLengthCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void displayView() {
        System.out.println("Введите длину массива: ");
    }

    @Override
    public Integer handlerInput() {
        while (true) {
            try {
                int length = Integer.parseInt(scanner.nextLine());
                this.clearConsole();
                return length;
            } catch (NumberFormatException e) {
                this.clearConsole();
                System.out.println("Ошибка: Введите число, а не строку. Попробуйте снова.");
            }
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
