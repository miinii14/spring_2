package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class VehicleController {
    private final VehicleRepository vehicleRepository = new VehicleRepository();
    private final UserRepository userRepository;

    VehicleController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void run(String path, User user){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Vehicle vehicle = null;
        String command;

        vehicleRepository.load(path);
        while(true){
            try {
                command = reader.readLine();
                switch (command){
                    case "exit":
                        return;
                    case "rent":
                        command = reader.readLine();
                        vehicle = vehicleRepository.findVehicle(command);
                        if(vehicle == null){
                            System.out.println("vehicle not found");
                            break;
                        }
                        vehicleRepository.rentVehicle(vehicle, user);
                        vehicleRepository.save(path);
                        userRepository.save(path);
                        break;
                    case "return":
                        command = reader.readLine();
                        vehicle = vehicleRepository.findVehicle(command);
                        if(vehicle == null){
                            System.out.println("vehicle not found");
                            break;
                        }
                        vehicleRepository.returnVehicle(vehicle, user);
                        vehicleRepository.save(path);
                        userRepository.save(path);
                        break;
                    case "vehicles":
                        List<Vehicle> vehicles = vehicleRepository.getVehicles();
                        for(Vehicle v: vehicles){
                            System.out.println(v);
                        }
                        break;
                    case "add":
                        if(user.getRole().equals("admin")){
                            String type = reader.readLine();
                            if(type.equals("car")){
                                vehicle = new Car(reader.readLine(), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), false);
                            }else if(type.equals("motorcycle")){
                                vehicle = new Motorcycle(reader.readLine(), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), false, reader.readLine());
                            }

                            vehicleRepository.addVehicle(vehicle);
                            vehicleRepository.save(path);
                        }else{
                            System.out.println("You don't have enough permissions");
                        }
                        break;
                    case "remove":
                        if(user.getRole().equals("admin")){
                            vehicleRepository.removeVehicle(reader.readLine());
                            vehicleRepository.save(path);
                        }else{
                            System.out.println("You don't have enough permissions");
                        }
                        break;
                    case "info":
                        if(user.getRole().equals("admin")){
                            for(User u: userRepository.getUsers()){
                                System.out.println(u);
                            }
                        }else{
                            System.out.println(user);
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
