package app.archive.temp.new_routes.routes;

import app.modules.router.exeptions.ExitException;
import app.modules.router.interfaces.IRoute;

public class RouteExit implements IRoute {
    @Override
    public void render() {

    }

    @Override
    public void execute(String args) throws ExitException {
        throw new ExitException("EXIT");
    }
}
