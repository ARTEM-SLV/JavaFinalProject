package app.model;

import java.io.Serializable;

public class Car implements Serializable {
    private Integer power;
    private String model;
    private Integer year;

    private Car(Integer power, String model, Integer year) {
        this.power = power;
        this.model = model;
        this.year = year;
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

    @Override
    public String toString() {
        return "CarBuilder{" +
                "model='" + model + '\'' +
                ", power=" + power +
                ", year=" + year +
                '}';
    }
}
