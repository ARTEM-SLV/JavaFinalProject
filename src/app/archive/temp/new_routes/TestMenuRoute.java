package app.archive.temp.new_routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.Router;

public class TestMenuRoute extends BaseRoute {

    private final Router router;

    public TestMenuRoute(Router router, String name) {
        super(name);
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
            case 2 -> this.router.navigateToGroup("/add");
        }
    }
}
