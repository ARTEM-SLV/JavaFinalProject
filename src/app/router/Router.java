package app.router;

import app.enums.Option;
import app.enums.StepsRouter;
import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Router {
    private final Map<StepsRouter, IRoute> commands = new HashMap<>();
    private IRoute curCommand;
    private final Context context = new Context();
    private final Scanner scanner;
    public StepsRouter step;
    public Option option;
    private Boolean isRun = true;
    public Router(Scanner sc) {
        this.scanner = sc;
    }

    public Context getContext() {
        return this.context;
    }

    public void addCommand(StepsRouter name, IRoute command) {
        if (this.curCommand == null) this.curCommand = command;
        this.commands.put(name, command);
    }

    public void navigateTo() throws Exception {
        if (!this.commands.containsKey(this.step)) return;
        this.curCommand = this.commands.get(this.step);

        this.clearDisplay();
        this.curCommand.render();
        this.curCommand.execute(this.scanner);
    }

    public void process() {
        if (this.curCommand == null) return;
        setStepMenu();

        while (this.isRun) {
            try {
                navigateTo();
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                if (e instanceof ExitException) {
                    System.out.println(e.getMessage());
                    this.isRun = false;
                    break;
                }
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    public void setStepMenu(){
        this.step = StepsRouter.OPTIONS;
        this.option = Option.MENU;
    }

    private void clearDisplay() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void tablePrint(OptionsType type, Object[] items) {
        System.out.println("");

        switch (type) {
            case BOOK:
                System.out.printf("%-30s %-15s %-10s%n", "Автор", "Название", "Страницы");
                break;
            case CAR:
                System.out.printf("%-30s %-15s %-10s%n", "Мощность", "Модель", "Год");
                break;
            case VEGETATION:
                System.out.printf("%-30s %-15s %-10s%n", "Тип", "Вес", "Цвет");
                break;

        }
        System.out.println("-------------------------------------------------------------");

        if (items != null) {
            for (var item : items) {
                if (item != null) {
                    switch (type){
                        case BOOK:
                            Book book = (Book) item;
                            System.out.println(book.toStringTable());
                            break;
                        case CAR:
                            Car car = (Car) item;
                            System.out.println(car.toStringTable());
                            break;
                        case VEGETATION:
                            Vegetable vegetable = (Vegetable) item;
                            System.out.println(vegetable.toStringTable());
                            break;
                    }
                }
            }
        }
    }
}
