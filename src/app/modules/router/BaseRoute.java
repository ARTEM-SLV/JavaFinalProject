package app.modules.router;

public abstract class BaseRoute implements IRoute{
    protected String pathToRoute;
    protected String name;

    public BaseRoute() {}
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

    protected Boolean isNumberString(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
