package app.archive.temp.new_routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.exeptions.BackException;

public class LengthInputRoute extends BaseRoute {

    private final Router router;

    public LengthInputRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Укажите длину массива.");
    }

    @Override
    public void execute(String args) throws Exception {
        if (!this.isNumberString(args)) {
            throw new BackException("Укажите в числах.");
        }

        this.router.getState().Length = Integer.parseInt(args);

        this.router.navigateTo(this.pathToRoute);
    }
}
