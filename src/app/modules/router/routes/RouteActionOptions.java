package app.modules.router.routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;

public class RouteActionOptions extends BaseRoute {
    private final Router router;

    public RouteActionOptions(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                1 - Sort
                2 - Поиск
                3 - Записать в файл
                """);
    }

    @Override
    public void execute(String args) {
            if (!isNumberString(args)) {
                throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
            }

            var val = Integer.parseInt(args);

            switch (val) {
                case 1: {
                    this.router.navigateTo("/sort");
                    break;
                }
                case 2: {
                    this.router.navigateTo("/search");
                    break;
                }
                case 3: {
                    this.router.navigateTo("/write-file");
                    break;
                }
            }
    }
}
