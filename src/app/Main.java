package app;

import app.assembly.RouterAssemblyV1;
import app.assembly.RouterAssemblyV2;
import app.enums.StepsRouter;
import app.enums.OptionsType;
import app.model.Car;
import app.modules.console.ConsolePort;
import app.modules.router.*;
import app.routes.VersionConsoleMenu;
import app.modules.router.state.State;
import app.archive.router_archive.routers.*;
import app.search.BinarySearch;
import app.search.Searcher;
import app.service.IExecutor;
import app.service.Executor;
import app.sort.ShellSort;
import app.sort.Sorter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var state = new State();
        var router = new Router("root");
        router.addRoute(new VersionConsoleMenu(router, "console-version"));
        router.addGroup(RouterAssemblyV1.create(state));
        router.addGroup(RouterAssemblyV2.create(state));
        var cPort = new ConsolePort<Router>();
        cPort.process(router);


//        startRouter();

//        var state = new State();
//        var testRouter = RouterCreateFactory.ConfigRouterNew.create(state);
//        var consolePort = new ConsolePort();
//        consolePort.process(testRouter);



//        var state = new State();
//        var testRouter = RouterCreateFactory.MegaNewRouter.create(state);
//        var consolePort2 = new ConsolePort2();
//        consolePort2.process(testRouter);




//        var testRouter2 = RouterConfigV2.create(state2);



//
//        var state2 = new State();
//        var testRouter2 = RouterConfigV2.create(state2);
//        var consolePort3 = new ConsolePort2();
//        consolePort3.process(testRouter2);




//        var mainRouter = RouterCreateFactory.MainRouter.create(state);
//        var consolePort = new ConsolePort();
//        consolePort.process(mainRouter);
//        testSortAndSearch();
    }

    private static void startRouter(){
        try(Scanner scanner = new Scanner(System.in)) {
            var newRouter = new app.router.Router(scanner);
            newRouter.addCommand(StepsRouter.LENGTH, new RouteReadInputLength(newRouter));
            newRouter.addCommand(StepsRouter.OPTIONS, new RouteOptions(newRouter));
            newRouter.addCommand(StepsRouter.INPUT_DATA, new RouteDataInput(newRouter));
            newRouter.addCommand(StepsRouter.WRITE_FILE, new RouteWriteToFile(newRouter));
            newRouter.addCommand(StepsRouter.EXIT, new RouteExit());

            newRouter.process();
        }
    }

    private static void testSortAndSearch(){
        Sorter<Car> sorter = new ShellSort<>();
        Searcher<Car> searcher = new BinarySearch<>();

        Car[] cars = createCars();
        Arrays.stream(cars)
                .forEach(System.out::println);

//        Comparator carComparator = UniversalComparator.getComparator(OptionsType.CAR);

        IExecutor<Car> IExecutor = new Executor<>(OptionsType.CAR);
        IExecutor.sort(cars, sorter);
        Car car = new Car.CarBuilder().power(200).model("BMW").year(2020).build();
        IExecutor.search(cars, searcher, car);
    }

    private static Car[] createCars(){
        Car.CarBuilder carBuilder = new Car.CarBuilder();
        Car[] cars = {
                carBuilder.power(200).model("BMW").year(2020).build(),
                carBuilder.power(150).model("Audi").year(2018).build(),
                carBuilder.power(180).model("Toyota").year(2019).build(),
                carBuilder.power(200).model("Audi").year(2021).build(),
                carBuilder.power(180).model("BMW").year(2022).build()
        };

        return cars;
    }
}