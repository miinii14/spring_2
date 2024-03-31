package org.example.model;

public class Car extends Vehicle {
    public Car(String brand, String model, int year, double price, String plate) {
        super(brand, model, year, price, plate);
    }

    public Car(String brand, String model, int year, double price, String plate, boolean rented) {
        super(brand, model, year, price, plate);
        setRent(rented);
    }
}
