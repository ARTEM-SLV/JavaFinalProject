package app.router.routers;

import app.enums.StepsRouter;
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
        try {
            Serializable[] serializableData = new Serializable[data.length];

            for (int i = 0; i < data.length; i++) {
                serializableData[i] = (Serializable) data[i];
            }

            FileIO.write("output.txt", serializableData);

            this.router.step = StepsRouter.MENU;
        } catch (Exception e) {
            System.out.println("Ошибка>>RouteWriteToFile");
       }
    }
}
