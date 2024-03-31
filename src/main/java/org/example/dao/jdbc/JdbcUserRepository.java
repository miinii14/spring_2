package org.example.dao.jdbc;

import org.example.dao.IUserRepository;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcUserRepository implements IUserRepository {
    private static JdbcUserRepository instance;
    private final DatabaseManager databaseManager;

    private static String GET_ALL_USERS_SQL = "SELECT login, password, role, rentedPlate FROM tuser";
    private static String GET_USER_SQL = "SELECT * FROM tuser WHERE login LIKE ?";
    private static String ADD_USER = "INSERT INTO tuser (login, password, role, rentedPlate) VALUES (?,?,?,?)";
    private static String DELETE_USER = "DELETE FROM tuser WHERE login LIKE ?";

    private JdbcUserRepository() {
        this.databaseManager = DatabaseManager.getInstance();
    }


    @Override
    public User getUser(String login) {
        User user = null;
        Connection connection =null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = databaseManager.getConnection();
            connection.createStatement();
            preparedStatement = connection.prepareStatement(GET_USER_SQL);
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            if(rs.next()) {
                user = new User(
                    rs.getString("login"),
                    rs.getString("password"),
                    User.Role.valueOf(rs.getString("role")),
                    rs.getString("rentedPlate")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = databaseManager.getConnection();
            stmt = connection.prepareStatement(ADD_USER);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().toString());
            stmt.setString(4, user.getRentedPlate());

            int changed = stmt.executeUpdate();

            if(changed > 0){
                System.out.println("Success");
            }else{
                System.out.println("Failure");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removeUser(String login) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = databaseManager.getConnection();
            User user = getUser(login);
            if(user.getRentedPlate() != null){
                System.out.println("ma wypozyczony samochod");
                return;
            }
            stmt = connection.prepareStatement(DELETE_USER);
            stmt.setString(1, login);
            int changed = stmt.executeUpdate();
            if (changed > 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public Collection<User> getUsers() {
        Collection<User> users = new ArrayList<>();
        try(Connection connection = databaseManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL)) {
            while(resultSet.next()){
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                String plate = resultSet.getString("rentedPlate");

                User user = new User(login, password, role,plate);
                users.add(user);
            }
        }catch (SQLException e){
                e.printStackTrace();
        }
        return users;
    }


    public static JdbcUserRepository getInstance(){
        if (JdbcUserRepository.instance == null){
            JdbcUserRepository.instance = new JdbcUserRepository();
        }
        return instance;
    }

}
