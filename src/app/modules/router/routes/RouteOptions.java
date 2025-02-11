package app.modules.router.routes;

import app.modules.router.*;

public class RouteOptions extends BaseRoute {
    private final Router router;

    public RouteOptions(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
            Какой тип данных будем использовать?
            1 - Автомобиль
            2 - Книга
            3 - Корнеплод
            """);
    }

    @Override
    public void execute(String args) {
        if (!this.isNumberString(args)) {
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        var selectOption = Integer.parseInt(args.trim());

        switch (selectOption) {
            case 1 -> {
                this.router.getState().optionsType = OptionsType.CAR;
            }
            case 2 -> {
                this.router.getState().optionsType = OptionsType.BOOK;
            }
            case 3 -> {
                this.router.getState().optionsType = OptionsType.VEGETATION;
            }
        }
        this.router.navigateTo(this.pathToRoute);
    }
}
