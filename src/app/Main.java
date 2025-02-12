package app;

import app.enums.StepsRouter;
import app.enums.OptionsType;
import app.model.Car;
import app.router.Router;
import app.router.routers.*;
import app.search.BinarySearch;
import app.search.Searcher;
import app.service.IExecutor;
import app.service.UniversalComparator;
import app.service.Executor;
import app.sort.ShellSort;
import app.sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startRouter();

//        testSortAndSearch();
    }

    private static void startRouter(){
        try(Scanner scanner = new Scanner(System.in)) {
            var newRouter = new Router(scanner);
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