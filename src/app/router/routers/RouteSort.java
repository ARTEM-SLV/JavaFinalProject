package app.router.routers;

import app.enums.Option;
import app.enums.OptionsType;
import app.enums.StepsRouter;
import app.router.ExitException;
import app.router.IRoute;
import app.router.Router;
import app.service.IExecutor;
import app.service.UniversalComparator;
import app.service.Executor;
import app.sort.ShellSort;
import app.sort.Sorter;

import java.util.Comparator;
import java.util.Scanner;

public class RouteSort<T> implements IRoute<T> {
    private final Router router;

    public RouteSort(Router router) {
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
        OptionsType type = router.getContext().optionsType;
        Object[] data = router.getContext().Data;
        Comparator comparator = UniversalComparator.getComparator(type);

        Sorter<T> sorter =new ShellSort<>();
        IExecutor<T> IExecutor = new Executor<>(type);
        IExecutor.sort((T[]) data, sorter);

        this.router.step = StepsRouter.OPTIONS;
        this.router.option = Option.SEARCH;
    }
}
