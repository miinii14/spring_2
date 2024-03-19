package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class VehicleController {
    private final VehicleRepository vehicleRepository = new VehicleRepository();

    public void run(String path){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Vehicle vehicle;
        String command;

        vehicleRepository.load(path);
        while(true){
            try {
                command = reader.readLine();
                switch (command){
                    case "exit":
                        break;
                    case "rent":
                        command = reader.readLine();
                        vehicle = vehicleRepository.findVehicle(command);
                        if(vehicle == null){
                            System.out.println("vehicle not found");
                            break;
                        }
                        vehicleRepository.rentVehicle(vehicle);
                        vehicleRepository.save(path);
                        break;
                    case "return":
                        command = reader.readLine();
                        vehicle = vehicleRepository.findVehicle(command);
                        if(vehicle == null){
                            System.out.println("vehicle not found");
                            break;
                        }
                        vehicleRepository.returnVehicle(vehicle);
                        vehicleRepository.save(path);
                        break;
                    case "info":
                        List<Vehicle> vehicles = vehicleRepository.getVehicles();
                        for(Vehicle v: vehicles){
                            System.out.println(v);
                        }
                        break;
                    default:
                        System.out.println("Bad command");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
