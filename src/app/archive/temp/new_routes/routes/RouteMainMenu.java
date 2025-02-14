package app.archive.temp.new_routes.routes;

import app.modules.router.core.BaseRoute;
import app.archive.temp.new_routes.Router;

public class RouteMainMenu extends BaseRoute {
    private final Router router;

    public RouteMainMenu(Router router) {
        super("RouteMainMenu");
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                2 - Ручной ввод
                3 - Рандом
                exit - Выход из программы
                """);
    }

    @Override
    public void execute(String args) throws NumberFormatException {
        if (!this.isNumberString(args)) {
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        var value = Integer.parseInt(args);

        switch (value) {
            case 2 -> this.router.navigateToRouterPath("/fill-manually");
            case 3 -> this.router.navigateToRouterPath("/random");
            default -> throw new NumberFormatException("Такой команды не существует.");
        }
    }
}
