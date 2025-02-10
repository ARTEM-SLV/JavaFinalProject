package app.modules.router.routes;

import app.modules.router.EnumRoutes;
import app.modules.router.IRoute;
import app.modules.router.Router;

import java.util.Scanner;

public class RouteReadInputLength implements IRoute {
    private final Router router;

    public RouteReadInputLength(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Введите длину массива: ");
    }

    @Override
    public void execute(Scanner scanner) throws NumberFormatException {
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }

        this.router.getContext().Length = Integer.parseInt(scanner.nextLine());
        this.router.navigateTo(EnumRoutes.OPTIONS);

    }
}