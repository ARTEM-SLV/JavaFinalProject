package app.modules.router.routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.utility.FileIO;

import java.io.*;

public class RouteWriteToFile extends BaseRoute {
    private final Router router;

    public RouteWriteToFile(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Запись в файл.....");
    }

    @Override
    public void execute(String args) throws Exception{
        var data = this.router.getState().Data;

        if (data == null || data.length < 1) {
            throw new Exception("Массив пуст");
        }

        try {
            Serializable[] serializableData = new Serializable[data.length];

            for (int i = 0; i < data.length; i++) {
                serializableData[i] = (Serializable) data[i];
            }

            FileIO.write("output.txt", false, serializableData);

            this.router.navigateToPath(this.pathToRoute);
        } catch (Exception e) {
            System.out.println("Ошибка>>RouteWriteToFile");
       }
    }
}
