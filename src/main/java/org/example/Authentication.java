package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authentication {
    private final UserRepository userRepository = new UserRepository();

    public User login(String path){
        String login, password;
        User user;
        userRepository.load(path);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while(true){
                login = reader.readLine();
                password = reader.readLine();
                user = userRepository.getUser(login, password);
                if(user != null){
                    return user;
                }else{
                    System.out.println("Incorrect login or password, try again");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
