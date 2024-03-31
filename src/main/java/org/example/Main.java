package org.example;

import org.example.authenticate.Authenticator;
import org.example.model.Motorcycle;
import org.example.model.Vehicle;

public class Main {

    // TODO !!! Integration this current project with previous one !!
    //  Redesign interfaces from previous project


    public static void main(String[] args) {
        System.out.println(Authenticator.hashPassword("123"));
        App app = new App();
        app.run();
    }
}
