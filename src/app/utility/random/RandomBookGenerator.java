package app.utility.random;

import app.model.Book;


public class RandomBookGenerator extends AbstractRandomGenerator<Book> {

    public RandomBookGenerator() {
        super("E:\\Java\\FinalProject\\resources\\books.txt", Book.class);
    }
}
