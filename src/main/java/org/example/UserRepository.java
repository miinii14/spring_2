package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository implements IUserRepository{
    private List<User> users = new ArrayList<>();

    @Override
    public User getUser(String login, String password) {
        for(User user: users){
            if(user.getLogin().equals(login) && BCrypt.checkpw(password, user.getPassword())){
                user.setPassword(password);
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void save(String path) {
        try {
            StringBuffer data = new StringBuffer("");
            PrintWriter writer = new PrintWriter(new File(path+"\\users.csv"));
            for(User user: users){
                data.append(user.toCSV()).append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String path){
        try {
            Scanner scanner = new Scanner(new File(path+"\\users.csv"));
            while(scanner.hasNext()){
                String[] line = scanner.nextLine().split(";");
                users.add(new User(line[0], line[1], line[2], line[3]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
