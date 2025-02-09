package app.modules.router;

import java.util.Scanner;

public interface IRoute {
    void render();
    void execute(Scanner scanner) throws Exception;
}
