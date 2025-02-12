package app.modules.router;

import app.modules.router.routes.*;
import app.modules.router.state.State;

public class RouterCreateFactory {

    public static class MainRouter {
        public static Router create(State state) {
            var router = new Router("/root");
            router.setState(state);
            router.addRoute("/root", new RouteMainMenu(router));

            router.addToGroupRouter("/random", RouterCreateFactory.RandomRouter.create());
            router.addToGroupRouter("/fill-manually", RouterCreateFactory.FillManually.create());
            return router;
        }
    }

    public static class RandomRouter {
        public static Router  create() {
            var router = new Router("/random");
            router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
            router.addRoute("/options", new RouteOptions(router).to("/random"));
            router.addRoute("/random", new RouteRandom(router).to("/action-options"));
            router.addRoute("/action-options", new RouteActionOptions(router));
            router.addRoute("/sort", new RouteSort(router).to("/action-options"));
            router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
            return router;
        }
    }

    public static class FillManually {
        public static Router  create() {
            var router = new Router("/fill-manually");
            router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
            router.addRoute("/options", new RouteOptions(router).to("/data-input"));
            router.addRoute("/data-input", new RouteDataInput(router).to("/action-options"));
            router.addRoute("/action-options", new RouteActionOptions(router));
            router.addRoute("/sort", new RouteSort(router).to("/action-options"));
            router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
            return router;
        }
    }
}
