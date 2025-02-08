package app.modules.fill_manually.commands;

import app.modules.fill_manually.commands.interfaces.IExecuteReturnValue;
import java.util.Scanner;

public class InputDataCommand implements IExecuteReturnValue<String> {
    private final Scanner scanner;
    private final String select;
    private final Integer length;

    public InputDataCommand(Scanner scanner, String select, Integer length) {
        this.scanner = scanner;
        this.select = select;
        this.length = length;
    }

    @Override
    public String execute() {
        var count = 0;
        StringBuilder data = new StringBuilder();
        do {
            System.out.println(this.select);
            var rawData = scanner.nextLine();
            data.append(rawData).append("\n").append("<===========>\n");
           
            count++;
            if (count >= length) {
                return data.toString();
            }
            this.clearConsole();
        } while (true);
    }

    // TODO
    // private void tablePrint(StringBuilder str) {
    //     System.out.printf("%-30s %-15s %-10s%n", "Мощность", "Модель", "Год производства");
    //     System.out.println("------------------------------------------");
    //     for (car : cars) {
    //
    //         System.out.printf("%-30s %-15d %-10s%n", object.getPower(), object.getModel(), object.getYear());
    //     }
    // }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
