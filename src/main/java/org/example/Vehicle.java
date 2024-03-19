package org.example;

public abstract class Vehicle {
    private String brand, model, registry;
    private int year, price;
    private boolean rented;

    public Vehicle(String registry, String brand, String model, int year, int price, boolean rented){
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = rented;
        this.registry = registry;
    }

    public String toCSV() {
        return registry+";"+brand+";"+model+";"+String.valueOf(year)+";"+String.valueOf(price)+";"+String.valueOf(rented);
    }

    @Override
    public String toString() {
        return "registry=" + registry + " brand=" + brand + " model=" + model + " year=" + String.valueOf(year) + " price=" + String.valueOf(price) + " rented=" + String.valueOf(rented);
    }

    public String getRegistry() {
        return registry;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
