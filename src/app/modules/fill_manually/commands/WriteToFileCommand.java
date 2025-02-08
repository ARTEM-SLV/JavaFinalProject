package app.modules.fill_manually.commands;

import app.modules.fill_manually.commands.interfaces.IExecuteVoid;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileCommand implements IExecuteVoid {

    private final String data;

    public WriteToFileCommand(String data) {
        this.data = data;
    }

    @Override
    public void execute() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(data);
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
