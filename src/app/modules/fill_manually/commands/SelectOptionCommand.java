package app.modules.fill_manually.commands;

import app.modules.fill_manually.NavFillManually;
import app.modules.fill_manually.commands.interfaces.IExecuteReturnValue;
import java.util.Scanner;

public class SelectOptionCommand implements IExecuteReturnValue<String> {

    private final Scanner scanner;
    private final NavFillManually nav;

    public SelectOptionCommand(Scanner scanner, NavFillManually nav) {
        this.scanner = scanner;
        this.nav = nav;
    }

    @Override
    public String execute() {
        while (true) {
            try {
                System.out.println(this.nav.getNav().getOptionsMessage());
                var selectOption = Integer.parseInt(scanner.nextLine());
                var opt = this.nav.getNav().getOptions().get(selectOption);

                if (opt != null) {
                    return opt;
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
