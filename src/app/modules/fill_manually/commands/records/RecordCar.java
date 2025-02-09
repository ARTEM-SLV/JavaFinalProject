package app.modules.fill_manually.commands.records;

public record RecordCar(String power, String model, String year) {

    @Override
    public String toString() {
        return String.format("%-30s %-15s %-10s", power, model, year);
    }
}
