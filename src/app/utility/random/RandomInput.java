package app.utility.random;

import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

import java.util.Random;

public class RandomInput {

    Random random = new Random();

    private static final String[] COLORS = {"Black", "White", "Green", "Yellow"};

    public Book[] generateRandomBook(int count){
        Book[] books = new Book[count];
        for (int i = 0; i < books.length; i++) {
            books[i] = generateRandomBook();
        }
        return books;
    }

    public Car[] generateRandomCar(int count){
        Car[] cars = new Car[count];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = generateRandomCar();
        }
        return cars;
    }

    public Vegetable[] generateRandomVegetable(int count){
        Vegetable[] vegetables = new Vegetable[count];
        for (int i = 0; i < vegetables.length; i++) {
            vegetables[i] = generateRandomVegetable();
        }
        return vegetables;
    }

    public Book generateRandomBook(){
        return new Book.BookBuilder()
                .author(randomString(7))
                .name(randomString(16))
                .pageCount(random.nextInt(10, 600))
                .build();
    }

    public Car generateRandomCar(){
        return new Car.CarBuilder()
                .power(random.nextInt(100))
                .model(randomString(10))
                .year(random.nextInt(1990, 2025))
                .build();
    }

    public Vegetable generateRandomVegetable(){
        return new Vegetable.VegetableBuilder()
                .type(randomString(10))
                .weight(random.nextDouble(100))
                .color(COLORS[random.nextInt(COLORS.length)])
                .build();
    }

    private String randomString(int length) {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'


        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
