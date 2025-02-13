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

        int length = Integer.parseInt(args);
        if (length == 0){
            this.router.navigateTo(this.pathBackRoute);
            return;
        }
        this.router.getState().Length = length;
        this.router.navigateTo(this.pathToRoute);
    }
}