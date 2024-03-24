package org.example;

import java.util.List;

public interface IUserRepository {
    public User getUser(String login, String password);
    public List<User> getUsers();
    public void save(String path);
}
