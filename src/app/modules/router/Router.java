package app.modules.router;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Router {
    private final Map<EnumRoutes, IRoute> commands = new HashMap<>();
    private IRoute curCommand;
    private final Context context = new Context();
    private final Scanner scanner;

    public Router(Scanner sc) {
        this.scanner = sc;
    }

    public Context getContext() {
        return this.context;
    }

    public void addCommand(EnumRoutes name, IRoute command) {
        if (this.curCommand == null) this.curCommand = command;
        this.commands.put(name, command);
    }
    private Boolean isRun = true;
    public void navigateTo(EnumRoutes name) {


        while (this.isRun) {
            try {
                if (!this.commands.containsKey(name)) return;
                this.curCommand = this.commands.get(name);

                this.clearDisplay();
                this.curCommand.render();
                this.curCommand.execute(this.scanner);

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

    public void process() {
        if (this.curCommand == null) return;
        this.navigateTo(EnumRoutes.MENU);
    }

    private void clearDisplay() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
