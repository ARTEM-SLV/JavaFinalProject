package app.modules.router.routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.ExitException;
import app.utility.FileIO;

public class RouteReadFile extends BaseRoute {

    private final Router router;

    public RouteReadFile(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Чтение файла.");
        System.out.print("Введите имя файла: ");
    }

    @Override
    public void execute(String args) throws ExitException {
        try {
            if (FileIO.isValidFilename(args)) {
                router.getState().Data = FileIO.read(args);
            } else {
                System.out.println("Формат имени файла: file.txt");
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения из файла");
        } finally {
            this.router.navigateTo(this.pathToRoute);
        }
    }
}
