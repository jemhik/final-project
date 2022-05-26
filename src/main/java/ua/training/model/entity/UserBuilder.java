package ua.training.model.entity;

public class UserBuilder {

    User user = new User();

    public UserBuilder setUserEmail(String email) {
        user.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.password = password;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        user.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        user.lastName = lastName;
        return this;
    }

    public UserBuilder setRole(String role) {
        user.role = role;
        return this;
    }

    public UserBuilder setId(int id) {
        user.id = id;
        return this;
    }

    public UserBuilder setBlocked(boolean blocked) {
        user.isBlocked = blocked;
        return this;
    }

    public User build(){
        return user;
    }
}
