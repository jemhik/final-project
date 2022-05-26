package ua.training.model.entity;

public class User {
    String email, password, firstName, lastName, role;
    int id;
    boolean isBlocked;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

}
