package app.modules.router.new_routes;

import app.input.RandomInput;
import app.modules.router.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.BackException;

public class RandomGenerationRoute extends BaseRoute {

    private final Router router;

    public RandomGenerationRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Нажмите для продолжения.");
    }

    @Override
    public void execute(String args) throws Exception {
        var len = this.router.getState().Length;
        var selectType = this.router.getState().optionsType;

        if (selectType == null) {
            throw new BackException("Массив пуст.");
        }

        if (len <= 0) {
            throw new BackException("Не указана длина массива.");
        }


        Object[] arr = null;

        switch (selectType){
            case BOOK: {
//                arr = new RandomBookGenerator().generateRandom(len);
                arr = new RandomInput().generateRandomBook(len);
                break;
            }
            case CAR:
            {
//                arr = new RandomCarGenerator().generateRandom(len);
                arr = new RandomInput().generateRandomCar(len);
                break;
            }
            case VEGETATION: {
//                arr = new RandomVegetableGenerator().generateRandom(len);
                arr = new RandomInput().generateRandomVegetable(len);
                break;
            }
        }

        for (var result : arr) {
            System.out.println(result);
        }

        this.router.getState().Data = arr;

        this.router.navigateTo(this.pathToRoute);
    }
}
