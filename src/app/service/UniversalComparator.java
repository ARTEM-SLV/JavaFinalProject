package app.service;

import app.enums.OptionsType;
import app.model.Book;
import app.model.Car;
import app.model.Vegetable;

import java.util.Comparator;

public class UniversalComparator {
    public static Comparator getComparator(OptionsType classType) {
        if (classType == OptionsType.CAR) {
            Comparator<Car> comparator = Comparator
                    .comparing(Car::getModel)
                    .thenComparingInt(Car::getPower)
                    .thenComparing(Comparator.comparingInt(Car::getYear));
            return comparator;
        } else if (classType == OptionsType.BOOK) {
            Comparator<Book> comparator = Comparator
                    .comparing(Book::getName)
                    .thenComparing(Book::getAuthor)
                    .thenComparing(Comparator.comparingInt(Book::getPageCount));
            return comparator;
        } else if (classType == OptionsType.VEGETATION) {
            Comparator<Vegetable> comparator = Comparator
                    .comparing(Vegetable::getType)
                    .thenComparing(Vegetable::getColor)
                    .thenComparing(Comparator.comparingDouble(Vegetable::getWeight));
            return comparator;
        }

        return null;
    }
}
