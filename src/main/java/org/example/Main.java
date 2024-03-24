package org.example;

import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("asd123", BCrypt.gensalt()));
        String path = "E:\\Programming\\java\\2 year\\zad2";
        Authentication authentication = new Authentication();
        VehicleController controller = new VehicleController(authentication.getUserRepository());

        User currentUser = authentication.login(path);
        controller.run(path, currentUser);
    }
}