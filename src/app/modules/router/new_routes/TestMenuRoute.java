package app.modules.router.new_routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;

public class TestMenuRoute extends BaseRoute {

    private final Router router;

    public TestMenuRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                1 - fsklgfsklfjn
                2 - Добавить
                """);
    }

    @Override
    public void execute(String args) throws Exception {
        var val = Integer.parseInt(args);

        switch (val) {
            case 1 -> System.out.println("");
            case 2 -> this.router.navigateToRouterPath("/router-group-add");
        }
    }
}
