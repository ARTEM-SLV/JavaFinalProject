package app.modules.fill_manually.commands.records;

public record RecordBook(String author, String name, String pages) {

    @Override
    public String toString() {
        return String.format("%-30s %-15s %-10s", author, name, pages);
    }
}
