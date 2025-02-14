package app.routes;

import app.modules.router.Router;
import app.modules.router.core.BaseRoute;
import app.modules.router.exeptions.BackException;
import app.validation.Validator;

public class AddMenuRoute extends BaseRoute {

    private Router router;

    public AddMenuRoute(Router router, String path) {
        super(path);
        this.router = router;
    }
    @Override
    public void render() {
        System.out.println("""
                1 - Ввод в ручную
                2 - Считайть с файла
                3 - Случайная генерация
                0 - Назад
                """);
    }

    @Override
    public void execute(String args) throws Exception {
            var val = Validator.getValidInt(args);



            switch (val) {
                case 1 -> this.router.navigateTo("/fill");
                case 2 -> this.router.navigateTo("/read");
                case 3 -> this.router.navigateTo("/random");
                case 0 -> this.router.navigateTo("/v3");
            }
    }
}
