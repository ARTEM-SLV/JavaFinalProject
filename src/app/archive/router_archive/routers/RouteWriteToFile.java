package app.archive.router_archive.routers;

import app.router.IRoute;
import app.router.Router;
import app.utility.FileIO;

import java.io.*;
import java.util.Scanner;

public class RouteWriteToFile implements IRoute {
    private final Router router;

    public RouteWriteToFile(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Запись в файл.....");
    }

    @Override
    public void execute(Scanner scanner) {
        var data = this.router.getContext().Data;
        if (data != null && data.length > 0) {
            try {
                Serializable[] serializableData = new Serializable[data.length];

                for (int i = 0; i < data.length; i++) {
                    serializableData[i] = (Serializable) data[i];
                }

                FileIO.write("output.txt", false, serializableData);

            } catch (Exception e) {
                System.out.println("Ошибка>>RouteWriteToFile");
            }
            finally {
               this.router.setStepMenu();
            }
        }
        else {
            System.out.println("Нет данных для записи.");
            this.router.setStepMenu();
        }
    }
}
