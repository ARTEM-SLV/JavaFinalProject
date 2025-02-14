package app.routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.Router;

public class ActionOptionsRoute extends BaseRoute {
    private final Router router;

    public ActionOptionsRoute(Router router, String name) {
        super(name);
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                
                1 - Сортировка
                2 - Поиск
                3 - Записать в файл
                0 - Предыдущее меню
                
                """);
    }

    @Override
    public void execute(String args) {
            if (!isNumberString(args)) {
                throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
            }

            var val = Integer.parseInt(args);

            switch (val) {
                case 1: {
                    this.router.navigateTo("/sort");
                    break;
                }
                case 2: {
                    this.router.navigateTo("/search");
                    break;
                }
                case 3: {
                    this.router.navigateTo("/write-file");
                    break;
                }
                case 0: {
                    this.router.navigateTo(this.pathBackRoute);
                    break;
                }
            }
    }
}
