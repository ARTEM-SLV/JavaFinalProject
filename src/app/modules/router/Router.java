package app.modules.router;

import app.modules.router.core.BaseRouter;
import app.modules.router.state.State;

public class Router extends BaseRouter {
    private State state;

    public Router(String name) {
        super(name);
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public BaseRouter to(String path) {
        this.navigateTo(path);
        return this;
    }


    public void navigateTo(String path) {
        var route = super.routes.get(path);

        var root = this.searchRoot(this);

        if (route != null) {
            root.currentRoute = route;
            return;
        }



        var search = searchRouter(this, path);
        root.currentRoute = search.currentRoute;
    }


    public void navigateToGroup(String path) {
        var group = super.groups.get(path);
        if(group == null) return;


        var root = searchRoot(this);

        root.currentRoute = group.currentRoute;
    }

    private BaseRouter searchRouter(BaseRouter router, String path) {
        // Проверяем текущий роутер
        if (router.routes.containsKey(path)) {
            return router;
        }

        // Проверяем группы текущего роутера
        if (router.groups.containsKey(path)) {
            return router.groups.get(path);
        }

        // Рекурсивно проверяем родительские роутеры
        if (router.parentRouter != null) {
            return searchRouter(router.parentRouter, path);
        }

        // Если ничего не найдено
        return null;
    }
}
