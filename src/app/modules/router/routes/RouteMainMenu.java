package app.modules.router.routes;

import app.modules.router.EnumRoutes;
import app.modules.router.IRoute;
import app.modules.router.Router;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RouteMainMenu implements IRoute {
    private final Router router;

    public RouteMainMenu(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                1 - Считать с файла
                2 - Ручной ввод
                0 - Выход из программы
                """);
    }

    @Override
    public void execute(Scanner scanner) throws NumberFormatException {
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        var value = scanner.nextInt();

        switch (value) {
            case 1 -> this.router.navigateTo(EnumRoutes.WRITE_FILE);
            case 2 -> this.router.navigateTo(EnumRoutes.LENGTH);
            case 0 -> this.router.navigateTo(EnumRoutes.EXIT);
            default -> throw new NumberFormatException("Такой команды не существует.");
        }
    }
}
