package app.archive.temp.new_routes;

import app.modules.router.Router;
import app.modules.router.state.State;
import app.routes.*;
import app.routes.DataInputRoute;

public class RouterConfigV3 {
    public static Router create(State state) {
        var router = new Router("root");
            router.setState(state);
            router.addRoute(new TestMenuRoute(router, "/menu"));

            var add = RouterConfigV3.addMenu(state);

            router.addGroup(add);

           return router;
    }


    public static Router addMenu(State state) {
        var router = new Router("/add");
        router.setState(state);
        router.addRoute(new AddMenuRoute(router, "/menu"));
        router.addGroup(RouterConfigV3.fill(state));
        return router;
    }

    public static Router fill(State state) {
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
                .to("root/menu"));

        return router;
    }


}
