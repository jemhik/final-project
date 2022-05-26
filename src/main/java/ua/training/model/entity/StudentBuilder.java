package ua.training.model.entity;


import java.util.List;

public class StudentBuilder {

    Student student = new Student();

    public StudentBuilder setId(int id) {
        student.id = id;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        student.email = email;
        return this;
    }

    public StudentBuilder setFirstName(String firstName) {
        student.firstName = firstName;
        return this;
    }

    public StudentBuilder setLastName(String lastName) {
        student.lastName = lastName;
        return this;
    }

    public StudentBuilder setCourses(List<Course> courses) {
        student.courses = courses;
        return this;
    }

    public StudentBuilder setStatus(String status) {
        student.status = status;
        return this;
    }

    public Student build(){
        return student;
    }
}
