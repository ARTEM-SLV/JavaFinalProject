package app.archive.temp.new_routes;

import app.modules.router.Router;
import app.archive.temp.new_routes.routes.*;
import app.modules.router.state.State;
import app.routes.*;
import app.routes.DataInputRoute;
import app.routes.MenuRoute;
import app.routes.RandomGenerationRoute;

public class RouterCreateFactory {

    public static class MainRouter {
        public static app.archive.temp.new_routes.Router create(State state) {
            var router = new app.archive.temp.new_routes.Router("/root");
            router.setState(state);
            router.addRoute("/root", new RouteMainMenu(router));

            router.addToGroupRouter("/random", RouterCreateFactory.RandomRouter.create());
            router.addToGroupRouter("/fill-manually", RouterCreateFactory.FillManually.create());
            return router;
        }
    }

    public static class ReadFileRouter {
        public static app.archive.temp.new_routes.Router create() {
            var router = new app.archive.temp.new_routes.Router("/read-file");
            router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
            router.addRoute("/options", new RouteOptions(router).to("/read-file").back("/input-length"));
            router.addRoute("/read-file", new RouteReadFile(router).to("/print_data").back("/options"));
            router.addRoute("/print_data", new DataPrintRoute(router).to("/action-options").back("/read-file"));
            router.addRoute("/action-options", new RouteActionOptions(router).back("/read-file"));
            router.addRoute("/sort", new RouteSort(router).to("/action-options").back("/read-file"));
            router.addRoute("/search", new RouteSearch(router).to("/write-file").back("/read-file"));
            router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
//            router.addRoute("/root", new RouteMainMenu(router));
            return router;
        }
    }

    public static class RandomRouter {
        public static app.archive.temp.new_routes.Router create() {
            var router = new app.archive.temp.new_routes.Router("/random");
            router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
            router.addRoute("/options", new RouteOptions(router).to("/random"));
            router.addRoute("/random", new RouteRandom(router).to("/action-options"));
            router.addRoute("/action-options", new RouteActionOptions(router));
            router.addRoute("/sort", new RouteSort(router).to("/action-options"));
            router.addRoute("/search", new RouteSearch(router).to("/write-file"));
            router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
//            router.addRoute("/root", new RouteMainMenu(router));
            return router;
        }
    }

    public static class FillManually {
        public static app.archive.temp.new_routes.Router create() {
            var router = new app.archive.temp.new_routes.Router("/fill-manually");
            router.addRoute("/input-length", new RouteReadInputLength(router).to("/options"));
            router.addRoute("/options", new RouteOptions(router).to("/data-input"));
            router.addRoute("/data-input", new RouteDataInput(router).to("/action-options"));
            router.addRoute("/action-options", new RouteActionOptions(router));
            router.addRoute("/sort", new RouteSort(router).to("/action-options"));
            router.addRoute("/search", new RouteSearch(router).to("/write-file"));
            router.addRoute("/write-file", new RouteWriteToFile(router).to("/root"));
            return router;
        }
    }

//    public static class ConfigRouter {
//        public static Router create(State state) {
//            var router = new Router("/root");
//            router.setState(state);
//            router.addRoute("/menu", new MenuRoute(router));
//            router.addRoute("/sort", new RouteSort(router).to("/menu"));
//            router.addRoute("/search", new RouteSearch(router).to("/menu"));
//            router.addRoute("/read-file", new RouteReadFile(router).to("/menu"));
//            router.addRoute("/write-file", new RouteWriteToFile(router).to("/menu"));
//            router.addRoute("/options", new SelectTypeRoute(router).to("/menu"));
//            router.addRoute("/data-print", new DataPrintRoute(router).to("/menu"));
//            router.addRoute("/random", new RandomGenerationRoute(router).to("/menu"));
//            router.addRoute("/len", new LengthInputRoute(router).to("/menu"));
//            router.addRoute("/fill-manually", new DataInputRoute(router).to("/menu"));
//
////            router.addToGroupRouter("/fill-manually", RouterCreateFactory.FillManually.create());
//            return router;
//        }
//    }

//    public static class ConfigRouterNew {
//        public static Router create(State state) {
//            var router = new Router("/root");
//            router.setState(state);
//            router.addRoute("/root", new MenuRoute(router));
//            router.addToGroupRouter("/read-file", RouterCreateFactory.ReadFileRouter.create());
//            router.addToGroupRouter("/random", RouterCreateFactory.RandomRouter.create());
//            router.addToGroupRouter("/fill-manually", RouterCreateFactory.FillManually.create());
//
//            return router;
//        }
//    }

    public static class MegaNewRouter {
        public static Router create(State state) {
            var root = new Router("/main");
            root.setState(state);
            root.addRoute(new MenuRoute(root, "/menu"));


            root.addGroup(MegaNewRouter.configReadFileRouter(state));
            root.addGroup(MegaNewRouter.configRandomRouter(state));
            root.addGroup(MegaNewRouter.configFillManuallyRouter(state));

            return root;
        }

        public static Router configFillManuallyRouter(State state) {
            var router = new Router("/fill-manually");
            router.setState(state);
            router.addRoute(new ReadInputLengthRoute(router, "/input-length")
                    .to("/options"));
            router.addRoute(new SelectOptionType(router,"/options")
                    .to("/input").back("/options"));
            router.addRoute(new DataInputRoute(router, "/input")
                    .to("/a-options").back("/options"));
            router.addRoute(new ActionOptionsRoute(router, "/a-options")
                    .to("/sort").back("/input-length"));
            router.addRoute(new SortRoute(router, "/sort")
                    .to("/search"));
            router.addRoute(new SearchRoute(router, "/search")
                    .to("/a-options"));
            router.addRoute(new WriteToFileRout(router, "/write-file")
                    .to("/menu"));

            return router;
        }

        public static Router configRandomRouter(State state) {
            var router = new Router("/random");
            router.setState(state);
            router.addRoute(new ReadInputLengthRoute(router, "/input-length")
                    .to("/options"));
            router.addRoute(new SelectOptionType(router,"/options")
                    .to("/r-gen").back("/options"));
            router.addRoute(new RandomGenerationRoute(router,"/r-gen")
                    .to("/print").back("/options"));
            router.addRoute(new app.routes.DataPrintRoute(router, "/print")
                    .to("/a-options"));
            router.addRoute(new ActionOptionsRoute(router, "/a-options")
                    .to("/sort").back("/input-length"));
            router.addRoute(new SortRoute(router, "/sort")
                    .to("/search"));
            router.addRoute(new SearchRoute(router, "/search")
                    .to("/a-options"));
            router.addRoute(new WriteToFileRout(router, "/write-file")
                    .to("/menu"));

            return router;
        }

        public static Router configReadFileRouter(State state) {
            var routerReadFile = new Router("/read-file");
            routerReadFile.setState(state);

            routerReadFile.addRoute(new ReadInputLengthRoute(routerReadFile, "/input-length")
                    .to("/options"));
            routerReadFile.addRoute(new SelectOptionType(routerReadFile,"/options")
                    .to("/read-file").back("/input-length"));
            routerReadFile.addRoute(new ReadFileRoute(routerReadFile, "/read-file")
                    .to("/print"));
            routerReadFile.addRoute(new app.routes.DataPrintRoute(routerReadFile, "/print")
                    .to("/a-options"));
            routerReadFile.addRoute(new ActionOptionsRoute(routerReadFile, "/a-options")
                    .to("/sort"));
            routerReadFile.addRoute(new SortRoute(routerReadFile, "/sort")
                    .to("/search"));
            routerReadFile.addRoute(new SearchRoute(routerReadFile, "/search")
                    .to("/write-file"));
            routerReadFile.addRoute(new WriteToFileRout(routerReadFile, "/write-file")
                    .to("/menu"));
            return routerReadFile;
        }
    }
}
