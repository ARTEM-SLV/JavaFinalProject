package app.assembly;

import app.archive.temp.new_routes.LengthInputRoute;
import app.modules.router.Router;
import app.modules.router.state.State;
import app.routes.*;

public class RouterAssemblyV3 {
    public static Router create(State state) {
        var router = new Router("/v3");
        router.setState(state);
        router.addRoute(new V3MenuRoute(router, "/v3-menu"));

        router.addRoute(new DataPrintRoute(router, "/print").to("/v3-menu"));

        router.addRoute(new WriteToFileRout(router, "/write-file").to("/v3-menu"));

        router.addRoute(new SortRoute(router, "/sort").to("/v3-menu"));

        router.addRoute(new SelectOptionType(router, "/option").to("/search"));
        router.addRoute(new SearchRoute(router, "/search").to("/v3"));




        router.addGroup(addMenu(state));

        return router;

    }

    private static Router addMenu(State state) {
        var addMenu = new Router("/v3-add-menu");
        addMenu.setState(state);
        addMenu.addRoute(new AddMenuRoute(addMenu, "/menu"));

        addMenu.addRoute(new DataInputRoute(addMenu, "/fill-manually").to("/menu"));


        var bebe = new Router("/fill");
        bebe.setState(state);
        bebe.addRoute(new ReadInputLengthRoute(bebe, "/fill-length").to("/options"));
        bebe.addRoute(new SelectOptionType(bebe, "/options").to("/fill-manually"));
        bebe.addRoute(new DataInputRoute(bebe, "/fill-manually").to("/menu"));

        var random = new Router("/random");
        random.setState(state);
        random.addRoute(new ReadInputLengthRoute(random, "/fill-length").to("/options"));
        random.addRoute(new SelectOptionType(random, "/options").to("/random"));
        random.addRoute(new RandomGenerationRoute(random, "/random").to("/print"));
        random.addRoute(new DataPrintRoute(random, "/print").to("/menu"));


        var read = new Router("/read");
        read.setState(state);
        read.addRoute(new ReadInputLengthRoute(read, "/fill-length").to("/options"));
        read.addRoute(new SelectOptionType(read, "/options").to("/read"));
        read.addRoute(new ReadFileRoute(read, "/read").to("/print"));
        read.addRoute(new DataPrintRoute(read, "/print").to("/menu"));


        addMenu.addGroup(bebe);
        addMenu.addGroup(random);
        addMenu.addGroup(read);
        return addMenu;
    }
}
