package app.utility.random;

import app.model.Car;


public class RandomCarGenerator extends AbstractRandomGenerator<Car> {

    public RandomCarGenerator() {
        super("E:\\Java\\FinalProject\\resources\\cars.txt", Car.class);
    }
}
