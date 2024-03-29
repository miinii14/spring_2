package org.example;

import java.util.List;

public interface IVehicleRepository {
    public Vehicle findVehicle(String registry);
    public void addVehicle(Vehicle vehicle);
    public void removeVehicle(String registry);
    public void rentVehicle(Vehicle vehicle, User user);
    public void returnVehicle(Vehicle vehicle, User user);
    public List<Vehicle> getVehicles();
    public void load(String path);
    public void save(String path);
}
