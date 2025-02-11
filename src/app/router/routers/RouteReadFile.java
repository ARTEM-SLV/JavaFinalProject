package app.router.routers;

import app.router.ExitException;
import app.router.IRoute;

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
