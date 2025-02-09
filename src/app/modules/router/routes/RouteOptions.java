package app.modules.router.routes;

import app.modules.router.IRoute;
import app.modules.router.OptionsType;
import app.modules.router.Router;

import java.util.Scanner;

public class RouteOptions implements IRoute {
    private final Router router;
    private final Scanner scanner;

    public RouteOptions(Router router, Scanner sc) {
        this.router = router;
        this.scanner = sc;
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
    public void execute() {
        this.render();

        while (true) {
            try {

                var selectOption = Integer.parseInt(scanner.nextLine());

                switch (selectOption) {
                    case 1 -> {
                        this.router.getContext().optionsType = OptionsType.CAR;
                        break;
                    }
                    case 2 -> {
                        this.router.getContext().optionsType = OptionsType.BOOK;
                        break;
                    }
                    case 3 -> {
                        this.router.getContext().optionsType = OptionsType.VEGETATION;
                        break;
                    }
                }

                this.clearConsole();
                this.router.navigateTo("select/input/data");
                return;
            } catch (Exception e) {
                System.out.println("Ошибка: Введите число, а не строку. Попробуйте снова.");
            }
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
