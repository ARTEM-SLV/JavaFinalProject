package app.modules.router.core;

import app.modules.router.interfaces.IRoute;

public abstract class BaseRoute implements IRoute {
    protected String pathToRoute;
    protected String pathBackRoute;
    protected String pathToGroupRouter;
    protected String name;

    public BaseRoute() {
    }

    public BaseRoute(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public BaseRoute to(String routePath) {
        this.pathToRoute = routePath;
        return this;
    }

    public BaseRoute back(String routePath) {
        this.pathBackRoute = routePath;
        return this;
    }

    public BaseRoute toGroup(String routePath) {
        this.pathToGroupRouter = routePath;
        return this;
    }

    protected Boolean isNumberString(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }
}
