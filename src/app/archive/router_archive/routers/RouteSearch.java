package app.archive.router_archive.routers;

import app.enums.StepsRouter;
import app.router.ExitException;
import app.router.IRoute;
import app.router.Router;

import java.util.Scanner;

public class RouteSearch<T> implements IRoute<T> {
    private final Router router;

    public RouteSearch(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        this.router.tablePrint(this.router.getContext().optionsType, this.router.getContext().Data);
        System.out.println("");
        System.out.println("Сортировка массива:");
    }

    @Override
    public void execute(Scanner scanner) throws ExitException {
        // TODO: 11.02.2025 Доделать функционал поиска

        this.router.step = StepsRouter.OPTIONS;
    }
}
