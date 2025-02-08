package app.service;

import app.enums.Classes;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

import java.util.Comparator;

public class UniversalComparator {
    public static Comparator getComparator(Classes classType) {
        if (classType == Classes.CAR) {
            Comparator<Car> comparator = Comparator
                    .comparing(Car::getModel).reversed()
                    .thenComparingInt(Car::getPower)
                    .thenComparing(Comparator.comparingInt(Car::getYear).reversed());
            return comparator;
        } else if (classType == Classes.BOOK) {
            Comparator<Book> comparator = Comparator
                    .comparing(Book::getName).reversed()
                    .thenComparing(Book::getAuthor)
                    .thenComparing(Comparator.comparingInt(Book::getPageCount).reversed());
            return comparator;
        } else if (classType == Classes.VEGETABLE) {
            Comparator<Vegetable> comparator = Comparator
                    .comparing(Vegetable::getType).reversed()
                    .thenComparing(Vegetable::getColor)
                    .thenComparing(Comparator.comparingDouble(Vegetable::getWeight).reversed());
            return comparator;
        }

        return null;
    }
}
