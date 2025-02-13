package app.modules.router.new_routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;

public class AddMenuRoute extends BaseRoute {

    private final Router router;

    public AddMenuRoute(Router router) {
        super("add-menu");
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                1 - Ручной ввод
                2 - Считать с файла
                3 - Случайная генерация
                """);
    }

    @Override
    public void execute(String args) throws Exception {

        var val = Integer.parseInt(args);
        switch (val) {
            case 1 -> this.router.navigateToRouterPath("/routerFillManually");
            case 2 -> this.router.navigateToRouterPath("/routerReadFile");
            case 3 -> this.router.navigateToRouterPath("/routerRandom");
        }
    }
}
