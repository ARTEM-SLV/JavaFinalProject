package app.modules.router.routes;

import app.modules.router.EnumRoutes;
import app.modules.router.IRoute;
import app.modules.router.OptionsType;
import app.modules.router.Router;

import java.util.Scanner;

public class RouteOptions implements IRoute {
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
    public void execute(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        var selectOption = Integer.parseInt(scanner.nextLine().trim());

        switch (selectOption) {
            case 1 -> {
                this.router.getContext().optionsType = OptionsType.CAR;
            }
            case 2 -> {
                this.router.getContext().optionsType = OptionsType.BOOK;
            }
            case 3 -> {
                this.router.getContext().optionsType = OptionsType.VEGETATION;
            }
        }

        this.router.navigateTo(EnumRoutes.INPUT_DATA);
    }
}
