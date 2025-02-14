package app.archive.temp.new_routes;

import app.modules.router.Router;
import app.modules.router.state.State;
import app.routes.*;
import app.routes.DataInputRoute;
import app.routes.DataPrintRoute;
import app.routes.RandomGenerationRoute;

public class RouterConfigV2 {
    public static Router create(State state) {
        var router = new Router("/root");
            router.setState(state);
            router.addRoute(new MenuRouteNNNN(router, "/menu"));
            router.addRoute(new SortRoute(router, "/sort").to("/menu"));
            router.addRoute(new SearchRoute(router, "/search").to("/menu"));
            router.addRoute(new ReadFileRoute(router, "/read-file").to("/menu"));
            router.addRoute(new WriteToFileRout(router, "/write-file").to("/menu"));
            router.addRoute(new SelectOptionType(router, "/options").to("/menu"));
            router.addRoute(new DataPrintRoute(router, "/data-print").to("/menu"));
            router.addRoute(new RandomGenerationRoute(router, "/random").to("/menu"));
            router.addRoute(new ReadInputLengthRoute(router, "/len").to("/menu"));
            router.addRoute(new DataInputRoute(router, "/fill-manually").to("/menu"));
//
////            router.addToGroupRouter("/fill-manually", RouterCreateFactory.FillManually.create());
           return router;
//        }
    }
}
