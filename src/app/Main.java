package app;

import app.modules.router.EnumRoutes;
import app.modules.router.Router;
import app.modules.router.routes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            var newRouter = new Router(scanner);
            newRouter.addCommand(EnumRoutes.MENU, new RouteMainMenu(newRouter));
//            newRouter.addCommand("read/file", new RouteReadFile());
            newRouter.addCommand(EnumRoutes.LENGTH, new RouteReadInputLength(newRouter));
            newRouter.addCommand(EnumRoutes.OPTIONS, new RouteOptions(newRouter));
            newRouter.addCommand(EnumRoutes.INPUT_DATA, new RouteDataInput(newRouter));
            newRouter.addCommand(EnumRoutes.WRITE_FILE, new RouteWriteToFile(newRouter));
            newRouter.addCommand(EnumRoutes.EXIT, new RouteExit());

            newRouter.process();
        }
    }
}