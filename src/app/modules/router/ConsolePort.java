package app.modules.router;

import app.modules.router.exeptions.ExitException;

import java.util.Scanner;

public class ConsolePort {

    public void process(Router router) {
        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    router.start();
                    String value = scanner.nextLine();

                    if (value.equals("q")) {
                        System.out.println("EXIT");
                        System.exit(0);
                    }

                    router.process(value);

                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    if (e instanceof ExitException) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Произошла ошибка: " + e.getMessage());
                }
            }

        }
    }
}
