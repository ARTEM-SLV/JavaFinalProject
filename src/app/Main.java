package app;

import app.modules.router.state.State;
import app.modules.router.ExitException;
import app.modules.router.Router;
import app.modules.router.routes.*;

import java.util.Scanner;

public class Main {
    private static Router randomRouter() {
        var router = new Router("/random");
        router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
        router.addRoute("/options", new RouteOptions(router).to("/random"));
        router.addRoute("/random", new RouteRandom(router).to("/action-options"));
        router.addRoute("/action-options", new RouteActionOptions(router));
        router.addRoute("/sort", new RouteSort(router).to("/action-options"));
        router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
        return router;
    }

    private static Router fillManuallyRouter() {
        var router = new Router("/fill-manually");
        router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
        router.addRoute("/options", new RouteOptions(router).to("/data-input"));
        router.addRoute("/data-input", new RouteDataInput(router).to("/action-options"));
        router.addRoute("/action-options", new RouteActionOptions(router));
        router.addRoute("/sort", new RouteSort(router).to("/action-options"));
        router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
        return router;
    }



    public static void main(String[] args) {
        var state = new State();
        var mainRouter = new Router("/root");
        mainRouter.setState(state);
        mainRouter.addRoute("/root", new RouteMainMenu(mainRouter));


        mainRouter.addToGroupRouter("/random", randomRouter());
        mainRouter.addToGroupRouter("/fill-manually", fillManuallyRouter());

        try(Scanner scanner = new Scanner(System.in)) {

            while (true) {
                try {
                    mainRouter.start();
                    String sc = scanner.nextLine();

                    if (sc.equals("exit")) {
                        System.out.println("EXIT");
                        System.exit(0);
                    }

                    mainRouter.process(sc);

                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    if (e instanceof ExitException) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Произошла ошибка: " + e.getMessage());
                }
            }

        }
    }
}