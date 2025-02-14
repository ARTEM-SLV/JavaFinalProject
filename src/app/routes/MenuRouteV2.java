package app.routes;

import app.modules.router.core.BaseRoute;
import app.modules.router.Router;
import app.modules.router.exeptions.ExitException;

public class MenuRouteV2 extends BaseRoute {
    private final Router router;

    public MenuRouteV2(Router router, String path) {
        super(path);
        this.router = router;
    }

    @Override
    public void render() {
        var type = this.router.getState().optionsType;
        var len = this.router.getState().Length;
        var typeMessage = type == null ? "[Не указан.]" : type;
        var lenMessage = len <= 0 ? "[Длина не указана.]" : len;

        System.out.println("""
                1 - Данные          2 - Считать с файла         8 - Случайная генерация
                5 - Сортировка      3 - Запись в файл           0 - Выход из программы
                6 - Поиск           4 - Ручной ввод
                
                ---7 - Выбрать тип
                ---9 - Указать длину массива
            
                """
                + "Длина: " + lenMessage + "    " + "Тип: " + typeMessage);
    }

    @Override
    public void execute(String args) throws Exception {
        if (!this.isNumberString(args)) {
            throw new NumberFormatException("Ошибка: Введите число.");
        }

        int value = Integer.parseInt(args);

        switch (value) {
            case 1 -> this.router.navigateTo("/data-print");
            case 2 -> this.router.navigateTo("/read-file");
            case 3 -> this.router.navigateTo("/write-file");
            case 4 -> this.router.navigateTo("/fill-manually");
            case 5 -> this.router.navigateTo("/sort");
            case 6 -> this.router.navigateTo("/search");
            case 7 -> this.router.navigateTo("/options");
            case 8 -> this.router.navigateTo("/random");
            case 9 -> this.router.navigateTo("/len");
            case 0 -> throw new ExitException("Exit");
        }
    }
}
