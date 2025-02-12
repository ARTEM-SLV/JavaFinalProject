package app;

import app.modules.router.ConsolePort;
import app.modules.router.RouterCreateFactory;
import app.modules.router.state.State;

public class Main {
    public static void main(String[] args) {
        var state = new State();
        var mainRouter = RouterCreateFactory.MainRouter.create(state);
        var consolePort = new ConsolePort();
        consolePort.process(mainRouter);
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