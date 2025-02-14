package app.utility.random;

import app.model.Vegetable;


public class RandomVegetableGenerator extends AbstractRandomGenerator<Vegetable> {

    public RandomVegetableGenerator() {
        super("vegetables.txt", Vegetable.class);
    }
}
