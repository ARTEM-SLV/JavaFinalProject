package app.router.routers;

import app.enums.Option;
import app.enums.StepsRouter;
import app.router.ExitException;
import app.router.IRoute;
import app.router.Router;
import app.utility.FileIO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class RouteReadFile implements IRoute {

    private final Router router;

    public RouteReadFile(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Чтение из файла.....");
    }

    @Override
    public void execute(Scanner scanner) {
            try {
                router.getContext().Data = FileIO.read("output.txt");
            } catch (Exception e) {
                System.out.println("Ошибка чтения из файла");
            }
            finally {
                this.router.setStepMenu();
            }
    }
}
