package app.modules.router.new_routes;

import app.modules.router.BaseRoute;
import app.modules.router.exeptions.ExitException;
import app.modules.router.Router;

import java.util.Map;

public class MenuRoute extends BaseRoute {
    private final Router router;

    public MenuRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
//        var type = this.router.getState().optionsType;
//        var len = this.router.getState().Length;
//        var typeMessage = type == null ? "[Не указан.]" : type;
//        var lenMessage = len <= 0 ? "[Длина не указана.]" : len;

//        Map<String, BaseRoute> routers = this.router.getRouters();
//        int num = 0;
//        for (String key: routers.keySet()){
//            num++;
//            System.out.printf("%s - %s\n", num, key);
//        }

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
            case 1 -> this.router.navigateToRouterPath("/read-file");
            case 2 -> this.router.navigateToRouterPath("/fill-manually");
            case 3 -> this.router.navigateToRouterPath("/random");
            case 0 -> throw new ExitException("Exit");
        }
    }

//    @Override
//    public void execute(String args) throws Exception {
//        if (!this.isNumberString(args)) {
//            throw new NumberFormatException("Ошибка: Введите число.");
//        }
//
//        int value = Integer.parseInt(args);
//
//        switch (value) {
//            case 1 -> this.router.navigateTo("/data-print");
//            case 2 -> this.router.navigateTo("/read-file");
//            case 3 -> this.router.navigateTo("/write-file");
//            case 4 -> this.router.navigateTo("/fill-manually");
//            case 5 -> this.router.navigateTo("/sort");
//            case 6 -> this.router.navigateTo("/search");
//            case 7 -> this.router.navigateTo("/options");
//            case 8 -> this.router.navigateTo("/random");
//            case 9 -> this.router.navigateTo("/len");
//            case 0 -> throw new ExitException("Exit");
//        }
//    }
}
