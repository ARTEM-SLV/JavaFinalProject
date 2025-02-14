package app.archive.temp.new_routes.routes;

import app.input.RandomBookGenerator;
import app.input.RandomCarGenerator;
import app.input.RandomVegetableGenerator;
import app.modules.router.core.BaseRoute;
import app.archive.temp.new_routes.Router;

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
                arr = new RandomBookGenerator().generateRandom(len);
                break;
            }
            case CAR:
            {
                arr = new RandomCarGenerator().generateRandom(len);
                break;
            }
            case VEGETATION: {
                arr = new RandomVegetableGenerator().generateRandom(len);
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
