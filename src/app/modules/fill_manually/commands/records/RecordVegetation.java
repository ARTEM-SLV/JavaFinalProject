package app.modules.fill_manually.commands.records;

public record RecordVegetation(String type, String weight, String color) {

    @Override
    public String toString() {
        return String.format("%-30s %-15s %-10s", type, weight, color);
    }
}
