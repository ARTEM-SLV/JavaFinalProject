package app;

import app.modules.router.Router;
import app.modules.router.routes.RouteDataInput;
import app.modules.router.routes.RouteOptions;
import app.modules.router.routes.RouteReadInputLength;
import app.modules.router.routes.RouteWriteToFile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            var newRouter = new Router();
            newRouter.addCommand("select/length", new RouteReadInputLength(newRouter, scanner));
            newRouter.addCommand("select/options", new RouteOptions(newRouter, scanner));
            newRouter.addCommand("select/input/data", new RouteDataInput(newRouter, scanner));
            newRouter.addCommand("select/out/file", new RouteWriteToFile(newRouter, scanner));

            newRouter.process();
        }
    }
}