package app.enums;

public enum FillingOptions {
    FROM_FILE("Из файла"),
    RANDOM("В случайном порядке"),
    MANUALLY("Вручную");

    private final String description;

    FillingOptions(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
