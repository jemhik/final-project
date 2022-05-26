package ua.training.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import java.sql.*;

public class UserDao {
//    private final String URL = "jdbc:mysql://localhost:3306/training_center";
//    private final String USER_NAME = "root";
//    private final String PASSWORD = "12345";
//    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);


    public void insert(User user){
        logger.debug("UserDao insert method");
        Connection connection = DBManager.getConnection();
       String query = "INSERT INTO users(first_name, last_name, email, password, role) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } finally {
            DBManager.close(connection);
        }
    }

    public String validate(User user){
        logger.debug("UserDao validate method");
        String DBEmail = "";
        String DBPassword = "";
        String DBRole = "";
        boolean isBlocked = false;
        Connection connection = DBManager.getConnection();
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DBEmail = rs.getString("email");
                DBPassword = rs.getString("password");
                DBRole = rs.getString("role");
                isBlocked = rs.getBoolean("isBlocked");
            }
            if (user.getEmail().equals(DBEmail) && user.getPassword().equals(DBPassword) && DBRole.equals("Admin")){
                return "Admin";
            }else if (user.getEmail().equals(DBEmail) && user.getPassword().equals(DBPassword) && DBRole.equals("Teacher")){
                return "Teacher";
            }else if (user.getEmail().equals(DBEmail) && user.getPassword().equals(DBPassword) && DBRole.equals("Student")){
                if(!isBlocked) {
                    return "Student";
                }else{
                    return "Blocked";
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return "User not registered in system";
    }

    public boolean isUnique(User user){
        logger.debug("UserDao isUnique method");
        Connection connection = DBManager.getConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        boolean status = false;
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
     return status;
    }

    public User findUser(User user){
        logger.debug("UserDao findUser method");
        Connection connection = DBManager.getConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        User user1 = null;
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user1 = new UserBuilder()
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setUserEmail(rs.getString("email"))
                        .setRole(rs.getString("role"))
                        .setId(rs.getInt("id"))
                        .build();
            }
        } catch (SQLException e) {
            //logger.error(String.valueOf(e));
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return user1;
    }

    public void deleteUser(User user){
        logger.debug("UserDao deleteUser method");
        Connection connection = DBManager.getConnection();
        String query = "DELETE FROM users WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
