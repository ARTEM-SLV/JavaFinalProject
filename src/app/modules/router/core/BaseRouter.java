package app.modules.router.core;

import app.modules.router.exeptions.BackException;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRouter {
    protected final String name;
    public Map<String, BaseRoute> routes;
    public Map<String, BaseRouter> groups;
    public BaseRoute currentRoute;
    public BaseRouter parentRouter;


    public BaseRouter(String name) {
        this.name = name;
        this.routes = new HashMap<>();
        this.groups = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void addRoute(BaseRoute route) {
        this.routes.put(route.getName(), route);
        if (this.currentRoute == null) {
            this.currentRoute = route; // Устанавливаем первый маршрут как текущий
        }
    }

    public void addGroup(BaseRouter group) {
        this.groups.put(group.getName(), group);
        group.parentRouter = this;
    }

    public void start() {
        if (this.currentRoute != null) {
            this.currentRoute.render();
        } else {
            System.out.println("Текущий маршрут не установлен.");
        }
    }

    public void process(String args) throws Exception {
        if (this.currentRoute != null) {
            try {
                this.currentRoute.execute(args);
            } catch (Exception e) {
                if (e instanceof BackException) {
//                    var root = this.searchRoot(this);
//                    root.currentRoute = this.routes.get(e.getMessage());
                }
                throw e;
            }
        } else {
            System.out.println("Текущий маршрут не установлен.");
        }
    }

    protected BaseRouter searchRoot(BaseRouter router) {
        if (router.parentRouter == null) {
            return router;
        }

        return this.searchRoot(router.parentRouter);
    }
}
