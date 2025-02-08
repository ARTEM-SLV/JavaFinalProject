package app.modules.fill_manually;

import app.modules.fill_manually.commands.InputDataCommand;
import app.modules.fill_manually.commands.ReadLengthCommand;
import app.modules.fill_manually.commands.SelectOptionCommand;
import app.modules.fill_manually.commands.WriteToFileCommand;
import java.util.Scanner;

public class FillManually {

    private final NavFillManually nav = new NavFillManually();

    public void init(Scanner scanner) {
        this.clearConsole();

        var readLength = new ReadLengthCommand(scanner, nav);
        var length = readLength.execute();
        var selectOption = new SelectOptionCommand(scanner, nav);
        var select = selectOption.execute();
        var inputData = new InputDataCommand(scanner, select, length);
        var data = inputData.execute();
        var writeToFile = new WriteToFileCommand(data);
        writeToFile.execute();
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
