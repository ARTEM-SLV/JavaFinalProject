package app.modules.router.routes;

import app.modules.router.ExitException;
import app.modules.router.IRoute;

import java.util.Scanner;

public class RouteReadFile implements IRoute {
    @Override
    public void render() {
        System.out.println("Чтение из файла....");
    }

    @Override
    public void execute(Scanner scanner) throws ExitException {
        throw new ExitException("EXIT");
    }
}
