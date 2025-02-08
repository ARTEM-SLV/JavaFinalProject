package app;

import app.enums.Classes;
import app.model.Car;
import app.search.BinarySearch;
import app.search.Searcher;
import app.service.Executor;
import app.service.UniversalExecutor;
import app.sort.ShellSort;
import app.sort.Sorter;
import app.service.UniversalComparator;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Sorter<Car> sorter = new ShellSort<>();
        Searcher<Car> searcher = new BinarySearch<>();

        Car[] cars = createCars();
        Arrays.stream(cars)
                .forEach(System.out::println);

        Comparator carComparator = UniversalComparator.getComparator(Classes.CAR);

        Executor<Car> executor = new UniversalExecutor<>();
        executor.sort(cars, sorter, carComparator);
        Car car = new Car.CarBuilder().power(200).model("BMW").year(2020).build();
        executor.search(cars, searcher, carComparator, car);
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