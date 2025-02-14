package app.archive.temp.new_routes.routes;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;
import app.modules.router.core.BaseRoute;
import app.archive.temp.new_routes.Router;
import app.modules.router.exeptions.ExitException;
import app.utility.FileIO;

public class RouteReadFile extends BaseRoute {

    private final Router router;

    public RouteReadFile(Router router) {
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Чтение файла.");
        if (this.router.getState().optionsType == null) {
            System.out.println("Тип данных не выбран");
            this.router.navigateTo(this.pathToRoute);
        }
        if (this.router.getState().Length == 0) {
            System.out.println("Размер массива не выбран");
            this.router.navigateTo(this.pathToRoute);
        }
        System.out.print("Введите имя файла: ");
    }

    @Override
    public void execute(String args) throws ExitException {
        try {
            if (FileIO.isValidFilename(args)) {
                Object[] data = FileIO.read(args);

                int carCount = 0;
                int bookCount = 0;
                int vegetableCount = 0;

                // Подсчет на количество автомобилей, книг и овощей
                for (Object obj : data) {
                    if (obj instanceof Car) {
                        carCount++;
                    } else if (obj instanceof Book) {
                        bookCount++;
                    } else if (obj instanceof Vegetable) {
                        vegetableCount++;
                    }
                }

                // Создание массивов для каждого типа
                Car[] cars = new Car[carCount];
                Book[] books = new Book[bookCount];
                Vegetable[] vegetables = new Vegetable[vegetableCount];

                int carIndex = 0;
                int bookIndex = 0;
                int vegetableIndex = 0;

                // Заполнение данных в соответствующие массивы
                for (Object obj : data) {
                    if (obj instanceof Car) {
                        cars[carIndex++] = (Car) obj;
                    } else if (obj instanceof Book) {
                        books[bookIndex++] = (Book) obj;
                    } else if (obj instanceof Vegetable) {
                        vegetables[vegetableIndex++] = (Vegetable) obj;
                    }
                }

                switch (this.router.getState().optionsType) {
                    case CAR -> {
                        router.getState().Data = getEntries(cars, this.router.getState().Length);
                    }
                    case BOOK -> {
                        router.getState().Data = getEntries(books, this.router.getState().Length);
                    }
                    case VEGETATION -> {
                        router.getState().Data = getEntries(vegetables, this.router.getState().Length);
                    }
                }

            } else {
                System.out.println("Формат имени файла: file.txt");
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения из файла");
        } finally {
            this.router.navigateTo(this.pathToRoute);
        }
    }

    public static Object[] getEntries(Object[] array, int N) {
        // Проверка на длину массива
        if (N > array.length) {
            N = array.length;
        }

        // Создает массив результат размером N
        Object[] result = new Object[N];

        // Копирует исходный массив в результат
        System.arraycopy(array, 0, result, 0, N);

        return result;
    }
}
