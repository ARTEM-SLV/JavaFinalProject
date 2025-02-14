package app.routes;

import app.input.RandomBookGenerator;
import app.input.RandomCarGenerator;
import app.input.RandomVegetableGenerator;
import app.modules.router.core.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.BackException;

public class RandomGenerationRoute extends BaseRoute {

    private final Router router;

    public RandomGenerationRoute(Router router, String path) {
        super(path);
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


        // Если массив в состоянии пустой
        if (this.router.getState().Data == null) {
            this.router.getState().Data = new Object[]{};
        }

        // Добавляем новые данные в состояние
        Object[] tempArray = new Object[arr.length + this.router.getState().Data.length];
        System.arraycopy(this.router.getState().Data, 0, tempArray, 0, this.router.getState().Data.length);
        System.arraycopy(arr, 0, tempArray, this.router.getState().Data.length, arr.length);
        this.router.getState().Data = tempArray;

        this.router.navigateTo(this.pathToRoute);
    }
}
