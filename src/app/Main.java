package app;

import app.enums.StepsRouter;
import app.router.Router;
import app.router.routers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startRouter();
    }

    private static void startRouter() {
        try (Scanner scanner = new Scanner(System.in)) {
            var newRouter = new Router(scanner);
            newRouter.addCommand(StepsRouter.LENGTH, new RouteReadInputLength(newRouter));
            newRouter.addCommand(StepsRouter.OPTIONS, new RouteOptions(newRouter));
            newRouter.addCommand(StepsRouter.PRINT, new RoutePrint(newRouter));
            newRouter.addCommand(StepsRouter.SORT, new RouteSort<>(newRouter));
            newRouter.addCommand(StepsRouter.INPUT_DATA, new RouteDataInput(newRouter));
            newRouter.addCommand(StepsRouter.WRITE_FILE, new RouteWriteToFile(newRouter));
            newRouter.addCommand(StepsRouter.READ_FILE, new RouteReadFile(newRouter));
            newRouter.addCommand(StepsRouter.EXIT, new RouteExit());

            newRouter.process();
        }
    }
}