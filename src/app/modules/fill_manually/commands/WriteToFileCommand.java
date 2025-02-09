package app.modules.fill_manually.commands;

import app.modules.fill_manually.commands.interfaces.IExecuteVoid;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class WriteToFileCommand implements IExecuteVoid {

    private final Object[] data;

    public WriteToFileCommand(Object[] data) {
        this.data = data;
    }

    @Override
    public void execute() {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_8))) {
            for (Object item : data) {
                if (item != null) {
                    writer.write(item.toString());
                    writer.newLine();
                }
            }
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
