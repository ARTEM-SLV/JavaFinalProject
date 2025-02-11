package app.router;

import java.util.Scanner;

public interface IRoute<T> {
    void render();
    void execute(Scanner scanner) throws Exception;
}
