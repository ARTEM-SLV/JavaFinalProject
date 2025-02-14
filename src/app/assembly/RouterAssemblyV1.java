package app.assembly;

import app.modules.router.Router;
import app.archive.temp.new_routes.RouterCreateFactory;
import app.modules.router.state.State;
import app.routes.*;

public class RouterAssemblyV1 {
    public static Router create(State state) {
        var root = new Router("/main");
        root.setState(state);
        root.addRoute(new MenuRoute(root, "/menu"));


        root.addGroup(RouterCreateFactory.MegaNewRouter.configReadFileRouter(state));
        root.addGroup(RouterCreateFactory.MegaNewRouter.configRandomRouter(state));
        root.addGroup(RouterCreateFactory.MegaNewRouter.configFillManuallyRouter(state));

        return root;
    }

    private static Router configFillManuallyRouter(State state) {
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

    private static Router configRandomRouter(State state) {
        var router = new Router("/random");
        router.setState(state);
        router.addRoute(new ReadInputLengthRoute(router, "/input-length")
                .to("/options"));
        router.addRoute(new SelectOptionType(router,"/options")
                .to("/r-gen").back("/options"));
        router.addRoute(new RandomGenerationRoute(router,"/r-gen")
                .to("/print").back("/options"));
        router.addRoute(new DataPrintRoute(router, "/print")
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

    private static Router configReadFileRouter(State state) {
        var routerReadFile = new Router("/read-file");
        routerReadFile.setState(state);

        routerReadFile.addRoute(new ReadInputLengthRoute(routerReadFile, "/input-length")
                .to("/options"));
        routerReadFile.addRoute(new SelectOptionType(routerReadFile,"/options")
                .to("/read-file").back("/input-length"));
        routerReadFile.addRoute(new ReadFileRoute(routerReadFile, "/read-file")
                .to("/print"));
        routerReadFile.addRoute(new DataPrintRoute(routerReadFile, "/print")
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