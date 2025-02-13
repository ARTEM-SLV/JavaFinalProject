package app.input;

import app.model.Book;


public class RandomBookGenerator extends AbstractRandomGenerator<Book> {

    public RandomBookGenerator() {
        super("books.txt", Book.class);
    }
}
