package app.validation;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

public class Validator {
    public static Car checkCar(String powerS,String modelS, String yearS) throws Exception {
        int power = getValidInt(modelS);
        String model = getValidString(modelS);
        int year = getValidInt(yearS);

        return new Car.CarBuilder().power(power).model(model).year(year).build();
    }
    public static Car checkCar(String[] inputData) throws Exception {
        int power = getValidInt(inputData[0]);
        String model = getValidString(inputData[1]);
        int year = getValidInt(inputData[2]);

        return new Car.CarBuilder().power(power).model(model).year(year).build();
    }

    public static Book checkBook(String authorS, String nameS, String pageCountS) throws Exception {
        String author = getValidString(authorS);
        String name = getValidString(nameS);
        int pageCount = getValidInt(pageCountS);

        return new Book.BookBuilder().author(author).name(name).pageCount(pageCount).build();
    }

    public static Book checkBook(String[] inputData) throws Exception {
        String author = getValidString(inputData[0]);
        String name = getValidString(inputData[1]);
        int pageCount = getValidInt(inputData[2]);

        return new Book.BookBuilder().author(author).name(name).pageCount(pageCount).build();
    }

    public static Vegetable checkVegetable(String typeS, String weightS, String colorS) throws Exception {
        String type = getValidString(typeS);
        Double weight = getValidDouble(weightS);
        String color = getValidString(colorS);

        return new Vegetable.VegetableBuilder().type(type).weight(weight).color(color).build();
    }

    public static Vegetable checkVegetable(String[] inputData) throws Exception {
        String type = getValidString(inputData[0]);
        Double weight = getValidDouble(inputData[1]);
        String color = getValidString(inputData[2]);

        return new Vegetable.VegetableBuilder().type(type).weight(weight).color(color).build();
    }

    // Валидация ввода числа (int)
    private static int getValidInt(String input) throws Exception {
        try {
            input = input.strip();
            int value = Integer.parseInt(input);
            if (value < 0) {
                throw new Exception("Ошибка ввода значения " + value + "! Введите положительное число.");
            } else {
                return value;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка! Введите целое число.\n" + e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Валидация ввода числа (double)
    private static double getValidDouble(String input) throws Exception {
        try {
            input = input.strip();
            double value = Double.parseDouble(input);
            if (value < 0) {
                throw new Exception("Ошибка ввода значения " + value + "! Введите положительное число.");
            } else {
                return value;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка! Введите целое число.\n" + e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Валидация ввода строки
    private static String getValidString(String input) throws Exception {
        input = input.strip();
        if (input.isEmpty()) {
            throw new Exception("Ошибка ввода значения " + input + "! Поле не может быть пустым.");
        } else {
            return input;
        }
    }
}
