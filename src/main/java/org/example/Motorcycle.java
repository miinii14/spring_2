package org.example;

public class Motorcycle extends Vehicle{
    private String category;
    public Motorcycle(String registry, String brand, String model, int year, int price, boolean rented, String category) {
        super(registry, brand, model, year, price, rented);
        this.category = category;
    }

    @Override
    public String toCSV() {
        return "Motorcycle;"+super.toCSV()+";"+category;
    }
}
