package app.modules.fill_manually.commands;

import app.modules.fill_manually.NavFillManually;
import app.modules.fill_manually.commands.interfaces.IExecuteReturnValue;
import java.util.Scanner;

public class ReadLengthCommand implements IExecuteReturnValue<Integer> {

    private final Scanner scanner;
    private final NavFillManually nav;

    public ReadLengthCommand(Scanner scanner, NavFillManually nav) {
        this.scanner = scanner;
        this.nav = nav;
    }

    @Override
    public Integer execute() {
        while (true) {
            System.out.println(nav.getNav().getFirstMessage());
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
