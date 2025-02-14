package app.routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.ExitException;

public class MenuRoute extends BaseRoute {
    private final Router router;

    public MenuRoute(Router router, String path) {
        super(path);
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                
                1 - Считать с файла
                2 - Ручной ввод
                3 - Случайная генерация
                0 - Выход из программы

                """);
    }

    @Override
    public void execute(String args) throws Exception {
        if (!this.isNumberString(args)) {
            throw new NumberFormatException("Ошибка: Введите число.");
        }

        int value = Integer.parseInt(args);

        switch (value) {
            case 1 -> this.router.navigateToGroup("/read-file");
            case 2 -> this.router.navigateToGroup("/fill-manually");
            case 3 -> this.router.navigateToGroup("/random");
            case 0 -> throw new ExitException("Exit");
        }
    }
}
