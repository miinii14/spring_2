package org.example;

public class Car extends Vehicle{
    public Car(String registry, String brand, String model, int year, int price, Boolean rented) {
        super(registry, brand, model, year, price, rented);
    }

    @Override
    public String toCSV() {
        return "Car;"+super.toCSV();
    }
}
