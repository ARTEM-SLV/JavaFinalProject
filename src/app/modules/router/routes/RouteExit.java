package app.modules.router.routes;

import app.modules.router.ExitException;
import app.modules.router.IRoute;

public class RouteExit implements IRoute {
    @Override
    public void render() {

    }

    @Override
    public void execute(String args) throws ExitException {
        throw new ExitException("EXIT");
    }
}
