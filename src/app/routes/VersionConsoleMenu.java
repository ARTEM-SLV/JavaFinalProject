package app.routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.Router;

public class VersionConsoleMenu extends BaseRoute {

    private Router router;

    public VersionConsoleMenu(Router router, String name) {
        super(name);
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                1 - V1
                2 - V2
                """);
    }

    @Override
    public void execute(String args) throws Exception {
        if (!isNumberString(args)) return;

        var val = Integer.parseInt(args);
        switch (val) {
            case 1 -> this.router.navigateTo("/main");
            case 2 ->  this.router.navigateTo("/root");
        }
    }
}
