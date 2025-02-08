package app.model;

public class Car {
    private Integer power;
    private String model;
    private Integer year;

    private Car(Integer power, String model, Integer year) {
        this.power = power;
        this.model = model;
        this.year = year;
    }

    public Integer getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String toString(){
        return "Car{" + "power=" + power + ", model='" + model + '\'' + ", year=" + year + '}';
    }

    public static class CarBuilder{
        private Integer power;
        private String model;
        private Integer year;

        public CarBuilder power(Integer power)
        {
            this.power = power;
            return this;
        }

        public CarBuilder model(String model)
        {
            this.model = model;
            return this;
        }

        public CarBuilder year(Integer year)
        {
            this.year = year;
            return this;
        }

        public Car build(){
            return new Car(power,model,year);
        }
    }
}
