package app;

import app.assembly.RouterAssemblyV1;
import app.assembly.RouterAssemblyV2;
import app.assembly.RouterAssemblyV3;
import app.modules.console.ConsolePort;
import app.modules.router.*;
import app.routes.VersionConsoleMenu;
import app.modules.router.state.State;


public class Main {
    public static void main(String[] args) {
        var state = new State();
        var router = new Router("root");
        router.addRoute(new VersionConsoleMenu(router, "console-version"));
        router.addGroup(RouterAssemblyV1.create(state));
        router.addGroup(RouterAssemblyV2.create(state));
        router.addGroup(RouterAssemblyV3.create(state));
        var cPort = new ConsolePort<Router>();
        cPort.process(router);
    }
}