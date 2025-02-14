package app.archive.temp.new_routes;
import app.modules.router.core.BaseRoute;
import app.modules.router.exeptions.BackException;
import app.modules.router.state.State;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final String path;
    private final Map<String, BaseRoute> routers = new HashMap<>();
    private final Map<String, Router> groupRouter = new HashMap<>();
    private Router headRouter;
    private BaseRoute currRoute;
    private State state;

    public Router(String path) {
        this.path = path;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPath() {
        return this.path;
    }

    private Router getHeadRouter() {
        return this.headRouter;
    }

    public void addToGroupRouter(String path, Router router) {
        this.groupRouter.put(path, router);
//        if (this.headRouter == null) {
//            this
//        }
        router.headRouter = this;
        router.state = this.state;
    }

    public Router getRouter() {
        return this;
    }

    public Map<String, BaseRoute> getRouters() {
        return routers;
    }

    public State getState() {
        return this.state;
    }

    private Map<String, Router> getGroupRouter() {
        return this.groupRouter;
    }

    private Router getRouterByGroup(String path) {
        return this.groupRouter.get(path);
    }


    public void navigateToRouterPath(String path) {
        // Если путь существует в groupRouter
        if (this.groupRouter.containsKey(path)) {
            Router targetRouter = this.groupRouter.get(path);
            this.currRoute = targetRouter.currRoute;
            this.headRouter.currRoute = this.currRoute;
            targetRouter.headRouter = this; // Устанавливаем обратную ссылку
            return; // Выходим, так как нашли маршрут
        }

        // Если текущий путь совпадает с запрошенным
        if (this.path.equals(path)) {
            this.currRoute = this.routers.get(path);
            return; // Выходим, так как нашли маршрут
        }

//         Если headRouter существует и его путь совпадает с запрошенным
        if (this.headRouter != null && this.headRouter.path.equals(path)) {
            this.headRouter.currRoute = this.headRouter.routers.get(path);
            return; // Выходим, так как нашли маршрут
        }

        System.out.println("Маршрут не найден: " + path);
    }

    public void addRoute(String name, BaseRoute route) {
        if (this.currRoute == null) {
            this.currRoute = route;
        }
        this.routers.put(name, route);
    }

    public void navigateTo(String path) {
        if (!this.routers.containsKey(path)) return;
        var getRoute = this.routers.get(path);
        if (this.headRouter == null) {
            this.currRoute = getRoute;
        } else {
            this.headRouter.currRoute = getRoute;
            this.currRoute = getRoute;
            this.headRouter.headRouter.currRoute = getRoute;
        }
    }

    public void start() {
        this.currRoute.render();
    }

    public void process(String args) throws Exception {
        if (this.currRoute == null) return;
        try {
            this.currRoute.execute(args);
        } catch (Exception e) {
            if (e instanceof BackException) {
                this.navigateTo("/menu");
            }
            throw e;
        }
    }

    public String getFullPath() {
        StringBuilder fullPath = new StringBuilder(this.path);
        Router current = this;

        if (current.getHeadRouter() != null) {
            var cur = current.getHeadRouter();
            while (cur != null) {
                current = current.getHeadRouter();
                fullPath.append(current.getPath() + "/" + this.currRoute.getName());
                cur = cur.headRouter;
            }
        } else {
            var pathRouter = current.getPath();
            var nameRoute = this.currRoute.getName();
            return pathRouter + "/" + nameRoute;
        }

        return fullPath.toString();
    }
}
