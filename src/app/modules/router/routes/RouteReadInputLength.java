package app.modules.router.routes;

import app.modules.router.IProps;
import app.modules.router.IRoute;
import app.modules.router.Router;

import java.util.Scanner;

public class RouteReadInputLength implements IRoute {
    private final Router router;
    private final Scanner scanner;

    public RouteReadInputLength(Router router, Scanner sc) {
        this.router = router;
        this.scanner = sc;
    }

    @Override
    public void render() {
        System.out.println("Введите длину массива: ");
    }

    @Override
    public void execute() {
        this.render();
        while (true) {
            try {
                int length = Integer.parseInt(scanner.nextLine());
                this.clearConsole();
                this.router.getContext().Length = length;
                this.router.navigateTo("select/options");
                break;
            } catch (NumberFormatException e) {
                this.clearConsole();
                System.out.println("Ошибка: Введите число, а не строку. Попробуйте снова.");
            }
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
