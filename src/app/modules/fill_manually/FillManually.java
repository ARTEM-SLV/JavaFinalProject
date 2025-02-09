package app.modules.fill_manually;

import app.modules.fill_manually.commands.InputDataCommand;
import app.modules.fill_manually.commands.ReadLengthCommand;
import app.modules.fill_manually.commands.SelectOptionCommand;
import app.modules.fill_manually.commands.WriteToFileCommand;
import app.modules.fill_manually.commands.records.RecordBook;
import app.modules.fill_manually.commands.records.RecordCar;
import app.modules.fill_manually.commands.records.RecordVegetation;
import app.modules.fill_manually.commands.types.Type;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class FillManually {

    private final Map<Type, InputDataCommand<?>> map = new EnumMap<>(Type.class);

    public void init(Scanner scanner) {
        this.clearConsole();
        this.map.put(Type.CAR, new InputDataCommand<>(
                scanner,
                data -> new RecordCar(data[0], data[1], data[2]),
                Type.CAR
        ));
        this.map.put(Type.BOOK, new InputDataCommand<>(
                scanner,
                data -> new RecordBook(data[0], data[1], data[2]),
                Type.BOOK
        ));
        this.map.put(Type.VEGETATION, new InputDataCommand<>(
                scanner,
                data -> new RecordVegetation(data[0], data[1], data[2]),
                Type.VEGETATION
        ));

        var readLength = new ReadLengthCommand(scanner);
        readLength.displayView();
        var length = readLength.handlerInput();

        var selectOptionsCommand = new SelectOptionCommand(scanner);
        selectOptionsCommand.displayView();
        var selectType = selectOptionsCommand.handlerInput();

        var dataCommand = this.map.get(selectType);
        if (dataCommand == null) {
            return;
        }
        dataCommand.setLength(length);
        dataCommand.displayView();
        var data = dataCommand.handlerInput();

        var writeToFile = new WriteToFileCommand(data);
        writeToFile.execute();
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
