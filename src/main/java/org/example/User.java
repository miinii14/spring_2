package org.example;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String login, hashedPassword, role, password;
    public String rentedVehicleRegistry;

    public User(String login, String password, String role, String registry){
        this.login = login;
        this.hashedPassword = password;
        this.role = role;
        this.rentedVehicleRegistry = registry;
    }

    public String getLogin() {
        return login;
    }

    public String toCSV(){
        return login+";"+hashedPassword+";"+role+";"+rentedVehicleRegistry;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "login: "+login+", password: "+password+", role: "+role+", registry: "+rentedVehicleRegistry;
    }
}
