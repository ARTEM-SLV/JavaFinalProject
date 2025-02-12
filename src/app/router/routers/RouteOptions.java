package app.router.routers;

import app.enums.StepsRouter;
import app.router.IRoute;
import app.enums.OptionsType;
import app.router.Router;

import java.util.Scanner;

public class RouteOptions implements IRoute {
    private final Router router;

    public RouteOptions(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        switch (router.option){
            case MENU -> sendMsgMenu();
            case TYPE -> sendMsgType();
            case SORT -> sendMsgSort();
            case SEARCH -> sendMsgSearch();
        }
    }

    @Override
    public void execute(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new NumberFormatException("Ошибка: Введите число, а не строку. Попробуйте снова.");
        }
        var value = scanner.nextInt();

        switch (router.option){
            case MENU -> executeMenu(value);
            case TYPE -> executeSelectType(value);
            case SORT -> executeSort(value);
            case SEARCH -> executeSearch(value);
        }
    }

    private void sendMsgMenu(){
        System.out.println("");
        System.out.println("""
                Основное меню:
                1 - Считать с файла
                2 - Ручной ввод
                0 - Выход из программы
                """);
    }

    private void sendMsgType(){
        System.out.println("");
        System.out.println("""
            Какой тип данных будем использовать?
            1 - Автомобиль
            2 - Книга
            3 - Корнеплод
            0 - Главное меню
            """);
    }

    private void sendMsgSort(){
        System.out.println("");
        System.out.println("""
                Выберите следующее действие:
                1 - Сортировать
                2 - Записать в файл
                0 - Главное меню
                """);
    }

    private void sendMsgSearch(){
        System.out.println("");
        System.out.println("""
                Выберите следующее действие:
                1 - Найти элемент
                2 - Записать в файл
                0 - Главное меню
                """);
    }

    private void executeMenu(int value){
        switch (value) {
            case 1 -> this.router.step = StepsRouter.WRITE_FILE;
            case 2 -> this.router.step = StepsRouter.LENGTH;
            case 0 -> this.router.step = StepsRouter.EXIT;
            default -> throw new NumberFormatException("Такой команды не существует.");
        }
    }

    private void executeSelectType(int value){
        switch (value) {
            case 1 -> this.router.getContext().optionsType = OptionsType.CAR;
            case 2 -> this.router.getContext().optionsType = OptionsType.BOOK;
            case 3 -> this.router.getContext().optionsType = OptionsType.VEGETATION;
            case 0 -> {
                this.router.setStepMenu();
                return;
            }
            default -> throw new NumberFormatException("Такой команды не существует!");
        }

        this.router.addCommand(StepsRouter.SORT, new RouteSort<>(this.router));
        this.router.addCommand(StepsRouter.SEARCH,  new RouteSearch<>(this.router));

        this.router.step = StepsRouter.INPUT_DATA;
    }

    private void executeSort(int value){
        switch (value) {
            case 1 -> this.router.step = StepsRouter.SORT;
            case 2 -> this.router.step = StepsRouter.WRITE_FILE;
            case 0 -> this.router.setStepMenu();
            default -> throw new NumberFormatException("Такой команды не существует.");
        }
    }

    private void executeSearch(int value){
        switch (value) {
            case 1 -> this.router.step = StepsRouter.SEARCH;
            case 2 -> this.router.step = StepsRouter.WRITE_FILE;
            case 0 -> this.router.setStepMenu();
            default -> throw new NumberFormatException("Такой команды не существует.");
        }
    }
}
