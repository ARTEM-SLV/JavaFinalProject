package app.modules.router.routes;

import app.input.RandomBookGenerator;
import app.input.RandomCarGenerator;
import app.input.RandomInput;
import app.input.RandomVegetableGenerator;
import app.modules.router.BaseRoute;
import app.modules.router.Router;

public class RouteRandom extends BaseRoute {
    private final Router router;

    public RouteRandom(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("RANDOM");
    }

    @Override
    public void execute(String args) {
        var len = this.router.getState().Length;
        var selectType = this.router.getState().optionsType;


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
