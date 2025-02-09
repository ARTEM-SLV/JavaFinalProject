package app.modules.router;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, IRoute> commands = new HashMap<>();
    private IRoute curCommand;
    private final Context context = new Context();

    public Context getContext() {
        return this.context;
    }

    public void addCommand(String name, IRoute command) {
        if (this.curCommand == null) this.curCommand = command;
        this.commands.put(name, command);
    }

    public void navigateTo(String name) {
        if (!this.commands.containsKey(name)) return;

        this.curCommand = this.commands.get(name);

        this.curCommand.execute();
    }

    public void process() {
        if (this.curCommand == null) return;
        this.curCommand.execute();
    }
}
