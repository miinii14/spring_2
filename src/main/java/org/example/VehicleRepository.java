package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleRepository implements IVehicleRepository{
    private List<Vehicle> vehicles = new ArrayList<>();
    private int lastId = 0;

    @Override
    public Vehicle findVehicle(String registry) {
        for(Vehicle vehicle: vehicles){
            if(vehicle.getRegistry().equals(registry)){
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(String registry) {
        for(Vehicle vehicle: vehicles){
            if(vehicle.getRegistry().equals(registry)){
                vehicles.remove(vehicle);
            }
        }
    }

    @Override
    public void rentVehicle(Vehicle vehicle, User user){
        if(!vehicle.isRented()){
            vehicle.setRented(true);
            user.rentedVehicleRegistry = vehicle.getRegistry();
        }else{
            System.out.println("The vehicle is already rented");
        }
    }

    @Override
    public void returnVehicle(Vehicle vehicle, User user) {
        if(vehicle.isRented()){
            vehicle.setRented(false);
            user.rentedVehicleRegistry = null;
        }else{
            System.out.println("The vehicle isn't rented yet");
        }
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void load(String path) {
        try {
            Scanner scanner = new Scanner(new File(path+"\\data.csv"));
            scanner.useDelimiter(";");
            while(scanner.hasNext()){
                String[] line = scanner.nextLine().split(";");
                if(line[0].equals("Car")){
                    vehicles.add(new Car(line[1], line[2], line[3], Integer.parseInt(line[4]), Integer.parseInt(line[5]), Boolean.parseBoolean(line[6])));
                }else{
                    vehicles.add(new Motorcycle(line[1], line[2], line[3], Integer.parseInt(line[4]), Integer.parseInt(line[5]), Boolean.parseBoolean(line[6]), line[7]));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(String path) {
        try {
            StringBuffer data = new StringBuffer("");
            PrintWriter writer = new PrintWriter(new File(path+"\\data.csv"));
            for(Vehicle vehicle: vehicles){
                data.append(vehicle.toCSV()).append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getLastId() {
        return lastId;
    }
}
