package app.archive.router_archive.routers;

import app.router.ExitException;
import app.router.IRoute;

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
