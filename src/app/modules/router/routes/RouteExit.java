package app.modules.router.routes;

import app.modules.router.ExitException;
import app.modules.router.IRoute;

import java.util.Scanner;

public class RouteExit implements IRoute {
    @Override
    public void render() {

    }

    @Override
    public void execute(Scanner scanner) throws ExitException {
        throw new ExitException("EXIT");
    }
}
