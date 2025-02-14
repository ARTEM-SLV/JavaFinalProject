package app.utility.random;

import app.model.Vegetable;


public class RandomVegetableGenerator extends AbstractRandomGenerator<Vegetable> {

    public RandomVegetableGenerator() {
        super("E:\\Java\\FinalProject\\resources\\vegetables.txt", Vegetable.class);
    }
}
