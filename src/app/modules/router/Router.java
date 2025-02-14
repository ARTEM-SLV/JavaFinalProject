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

    private BaseRouter searchRoot(BaseRouter router) {
        if (router.parentRouter == null) {
            return router;
        }

        return this.searchRoot(router.parentRouter);
    }

    public void navigateTo(String path) {
        var route = super.routes.get(path);

        var root = this.searchRoot(this);

        if (route != null) {
            root.currentRoute = route;
            return;
        }



        var s = searchRouter(this, path);
        root.currentRoute = s.currentRoute;
        System.out.println(s);
//        if (s.name.equals(path)) {
//            root.currentRoute = s.currentRoute;
//        } else if (route == null) {
//            root.currentRoute = root.routes.get(path);
//        } else {
//            root.currentRoute = route;
//        }



//        if (super.routes == null) {
//            super.parentRouter.currentRoute = route;
//        } else {
//            super.currentRoute = route;
//        }

//      если нашли маршрут то записываем и выходим
//        if (!super.routes.containsKey(path)) {
//            var split = path.split("/");
//            var step = 0;
//            var link = this.parentRouter;
//            while (link != null) {
//                if(link.name.equals(split[step])) {
//                    link.currentRoute = link.routes.get("/"+split[1]);
//                    link = link.parentRouter;
//                    step++;
//                }
//            }
//        }
    }


    public void navigateToGroup(String path) {
        var group = super.groups.get(path);
        if(group == null) return;

//        var router = this.searchRouter(this, path);
//        if (router != null) {
//            super.currentRoute = router.currentRoute;
//        } else {
//            super.currentRoute = group.currentRoute;
//        }
        var root = searchRoot(this);

        root.currentRoute = group.currentRoute;
//        this.parentRouter = this;
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
