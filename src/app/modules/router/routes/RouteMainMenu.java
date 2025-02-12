package app.modules.router.routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;

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
            case 2 -> this.router.navigateToPath("/fill-manually");
            case 3 -> this.router.navigateToPath("/random");
            default -> throw new NumberFormatException("Такой команды не существует.");
        }
    }
}
