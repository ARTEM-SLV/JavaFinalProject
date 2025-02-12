package app.input;

import app.model.Vegetable;


public class RandomVegetableGenerator extends AbstractRandomGenerator<Vegetable> {

    public RandomVegetableGenerator() {
        super("vegetables.txt", Vegetable.class);
    }
}
