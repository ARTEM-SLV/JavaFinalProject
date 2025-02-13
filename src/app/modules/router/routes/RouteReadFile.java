package app.modules.router.routes;

import app.modules.router.exeptions.ExitException;
import app.modules.router.interfaces.IRoute;

public class RouteReadFile implements IRoute {
    @Override
    public void render() {
        System.out.println("Чтение из файла....");
    }

    @Override
    public void execute(String args) throws ExitException {
        throw new ExitException("EXIT");
    }
}
