package app.modules.router.new_routes;

import app.modules.router.BaseRoute;
import app.modules.router.exeptions.ExitException;
import app.modules.router.Router;

public class MenuRoute extends BaseRoute {
    private final Router router;

    public MenuRoute(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        var type = this.router.getState().optionsType;
        var len = this.router.getState().Length;
        var typeMessage = type == null ? "[Не указан.]" : type;
        var lenMessage = len <= 0 ? "[Длина не указана.]" : len;

        System.out.println("""
                
                1 - Данные                  4 - Ручной ввод
                2 - Считать с файла         5 - Сортировка
                3 - Запись в файл           6 - Поиск
                
      
                ---7 - Выбрать тип
                8 - Случайная генерация
                ---9 = Указать длину массива
                0 - Выход из программы
                
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
