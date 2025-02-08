package app.model;

import java.io.Serializable;

public class Vegetable implements Serializable {
    private String type;
    private Double weight;
    private String color;

    private Vegetable(String type, Double weight, String color) {
        this.type = type;
        this.weight = weight;
        this.color = color;
    }

    public static class VegetableBuilder{
        private String type;
        private Double weight;
        private String color;

        public VegetableBuilder type(String type){
            this.type = type;
            return this;
        }

        public VegetableBuilder weight(Double weight){
            this.weight = weight;
            return this;
        }

        public VegetableBuilder color(String color){
            this.color = color;
            return this;
        }

        public Vegetable build(){
            return new Vegetable(type,weight,color);
        }
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }
}
