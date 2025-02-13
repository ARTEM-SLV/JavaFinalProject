package app.input;

import app.model.Car;


public class RandomCarGenerator extends AbstractRandomGenerator<Car> {

    public RandomCarGenerator() {
        super("cars.txt", Car.class);
    }
}
