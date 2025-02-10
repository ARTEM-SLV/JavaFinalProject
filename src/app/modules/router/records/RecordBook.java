package app.modules.router.records;

import java.io.Serializable;

public record RecordBook(String author, String name, String pages) implements Serializable, ITableToString {

    @Override
    public String toString() {
        return "Книга, " + "Автор: " + author + ", " + "Имя: " + name + ", " + "Страницы: " + pages;
    }

    @Override
    public String toStringTable() {
        return String.format("%-30s %-15s %-10s", author, name, pages);
    }
}
