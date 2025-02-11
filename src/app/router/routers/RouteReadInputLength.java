package app.router.routers;

import app.enums.Option;
import app.enums.StepsRouter;
import app.router.IRoute;
import app.router.Router;

import java.util.Scanner;

public class RouteReadInputLength implements IRoute {
    private final Router router;

    public RouteReadInputLength(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Введите длину массива: ");
        System.out.println("0 - возврат в главное меню");
    }

    @Override
    public void execute(Scanner scanner) throws Exception {
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        var len = scanner.nextInt();
        if (len < 0){
            throw new Exception("Ошибка ввода значения " + len + "! Введите положительное число.");
        } else if (len == 0) {
            this.router.step = StepsRouter.MENU;
            return;
        }

        this.router.getContext().Length = len;
        this.router.step = StepsRouter.OPTIONS;
        this.router.option = Option.TYPE;
    }
}