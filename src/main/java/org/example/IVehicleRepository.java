package org.example;

import java.util.List;

public interface IVehicleRepository {
    public Vehicle findVehicle(String registry);
    public void rentVehicle(Vehicle vehicle);
    public void returnVehicle(Vehicle vehicle);
    public List<Vehicle> getVehicles();
    public void load(String path);
    public void save(String path);
}
