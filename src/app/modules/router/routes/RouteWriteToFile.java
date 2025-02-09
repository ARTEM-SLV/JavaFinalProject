package app.modules.router.routes;

import app.modules.router.IRoute;
import app.modules.router.Router;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RouteWriteToFile implements IRoute {
    private final Router router;
    private final Scanner scanner;

    public RouteWriteToFile(Router router, Scanner sc) {
        this.router = router;
        this.scanner = sc;
    }

    @Override
    public void render() {
        System.out.println("Запись в файл.....");
    }

    @Override
    public void execute() {
        var data = this.router.getContext().Data;

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_8))) {
            for (Object item : data) {
                if (item != null) {
                    writer.write(item.toString());
                    writer.newLine();
                }
            }
            System.out.println("Данные успешно записаны в файл.");
            this.router.navigateTo("exit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
