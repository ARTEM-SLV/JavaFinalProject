package app.routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.BackException;
import app.utility.FileIO;

import java.io.Serializable;

public class WriteToFileRout extends BaseRoute {
    private final Router router;

    public WriteToFileRout(Router router, String name) {
        super(name);
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Запись в файл.");
        System.out.print("Введите имя файла: ");
    }

    @Override
    public void execute(String args) throws Exception {
        var data = this.router.getState().Data;

        if (data == null || data.length < 1) {
            throw new BackException("Массив пуст");
        }

        try {

            if (FileIO.isValidFilename(args)) {
                Serializable[] serializableData = new Serializable[data.length];

                for (int i = 0; i < data.length; i++) {
                    serializableData[i] = (Serializable) data[i];
                }

                FileIO.write(args, false, serializableData);
            } else {
                System.out.println("Формат имени файла: file.txt");
            }

            this.router.navigateTo(this.pathToRoute);
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл");
        }
    }
}
