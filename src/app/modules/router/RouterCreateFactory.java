package app.modules.router;

import app.modules.router.new_routes.*;
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
            router.addRoute("/search", new RouteSearch(router).to("/root"));
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

    public static class Add {
        public static Router create(State state) {
           var routerMain = new Router("/root");
            routerMain.setState(state);
            routerMain.addRoute("/root", new TestMenuRoute(routerMain));



            ////////////////////////////
            var routerGroupAdd = new Router("/group-add");
            routerGroupAdd.setState(state);
            routerGroupAdd.addRoute("/group-add", new AddMenuRoute(routerGroupAdd));

            //
            var routerFillManually = new Router("/group-fill-manually");
            routerFillManually.addRoute("/len", new LengthInputRoute(routerFillManually).to("/options"));
            routerFillManually.addRoute("/options", new SelectTypeRoute(routerFillManually).to("/fill-manually"));
            routerFillManually.addRoute("/fill-manually", new DataInputRoute(routerFillManually));
            //

            var routerReadFile = new Router("/group-read-file");
            routerReadFile.addRoute("/len", new LengthInputRoute(routerReadFile).to("/options"));
            routerReadFile.addRoute("/options", new SelectTypeRoute(routerReadFile).to("/read-file"));
            routerReadFile.addRoute("/read-file", new RouteReadFile(routerReadFile));


            var routerRandom= new Router("/group-random");
            routerRandom.addRoute("/len", new LengthInputRoute(routerRandom).to("/options"));
            routerRandom.addRoute("/options", new SelectTypeRoute(routerRandom).to("/random"));
            routerRandom.addRoute("/random", new RandomGenerationRoute(routerRandom));


            routerGroupAdd.addToGroupRouter("/routerFillManually", routerFillManually);
            routerGroupAdd.addToGroupRouter("/routerReadFile", routerReadFile);
            routerGroupAdd.addToGroupRouter("/routerRandom", routerRandom);
//            return routerGroupAdd;
            ///////////////////////////

            routerMain.addToGroupRouter("/router-group-add", routerGroupAdd);
            return routerMain;

//            router.addRoute("/read", new RouteReadFile(router).to("/menu"));

//            var group = new Router("/g-random");
//            group.addRoute("/len", new LengthInputRoute(group).to("/options"));
//            group.addRoute("/options", new SelectTypeRoute(group).to("/random"));
//            group.addRoute("/random", new RandomGenerationRoute(group).to("/g-random"));
//            router.addToGroupRouter("/g-random", group);
//
//            routerM.addToGroupRouter("/add", router);

//            return router;
        }
    }

    public static class MaksimConfigRouter {
        public static Router create(State state) {
            var router = new Router("/root");
            router.setState(state);
            router.addRoute("/menu", new MenuRoute(router));
            router.addRoute("/sort", new RouteSort(router).to("/menu"));
            router.addRoute("/search", new RouteSearch(router).to("/menu"));
            router.addRoute("/read-file", new RouteReadFile(router).to("/menu"));
            router.addRoute("/write-file", new RouteWriteToFile(router).to("/menu"));
            router.addRoute("/options", new SelectTypeRoute(router).to("/menu"));
            router.addRoute("/data-print", new DataPrintRoute(router).to("/menu"));
            router.addRoute("/random", new RandomGenerationRoute(router).to("/menu"));
            router.addRoute("/len", new LengthInputRoute(router).to("/menu"));
            router.addRoute("/fill-manually", new DataInputRoute(router).to("/menu"));

//            router.addToGroupRouter("/fill-manually", RouterCreateFactory.FillManually.create());
            return router;
        }
    }
}
