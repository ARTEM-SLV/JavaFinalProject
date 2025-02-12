package app;

import app.modules.router.ConsolePort;
import app.modules.router.RouterCreateFactory;
import app.modules.router.state.State;

public class Main {
    public static void main(String[] args) {
        var state = new State();
        var mainRouter = RouterCreateFactory.MainRouter.create(state);

        var consolePort = new ConsolePort();
        consolePort.process(mainRouter);
    }
}