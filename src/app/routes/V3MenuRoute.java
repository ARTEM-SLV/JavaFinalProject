package app.routes;

import app.modules.router.Router;
import app.modules.router.core.BaseRoute;
import app.modules.router.exeptions.ExitException;
import app.validation.Validator;

public class V3MenuRoute extends BaseRoute {
    private final Router router;

    public V3MenuRoute(Router router, String name) {
        super(name);
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("""
                1 - Данные
                2 - Добавить
                3 - Запись в файл
                4 - Сортировка
                5 - Поиск
                0 - Выход
                """);
    }

    @Override
    public void execute(String args) throws Exception {
        var val = Validator.getValidInt(args);


        switch (val){
            case 1 -> this.router.navigateTo("/print");
            case 2 -> this.router.navigateTo("/v3-add-menu");
            case 3 -> this.router.navigateTo("/write-file");
            case 4 -> this.router.navigateTo("/sort");
            case 5 -> this.router.navigateTo("/option");
            case 0 -> this.router.navigateTo("/search");

        }

    }
}
