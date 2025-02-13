package app.router.routers;

import app.router.ExitException;
import app.router.IRoute;
import app.router.Router;

import java.util.Arrays;
import java.util.Scanner;

public class RoutePrint implements IRoute {
    private final Router router;

    public RoutePrint(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Текущие данные:");
        System.out.println(Arrays.toString(this.router.getContext().Data));
        System.out.println("");
    }

    @Override
    public void execute(Scanner scanner) throws ExitException {
        this.router.setStepMenu();
    }
}
