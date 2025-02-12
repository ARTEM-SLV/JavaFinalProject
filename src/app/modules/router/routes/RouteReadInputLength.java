package app.modules.router.routes;

import app.modules.router.BaseRoute;
import app.modules.router.Router;

public class RouteReadInputLength extends BaseRoute {
    private final Router router;

    public RouteReadInputLength(Router router) {
        super("InputLength");
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Введите длину массива: ");
    }

    @Override
    public void execute(String args) throws NumberFormatException {
        if (!this.isNumberString(args)) {
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        this.router.getState().Length = Integer.parseInt(args);
        this.router.navigateTo(this.pathToRoute);
    }
}