package ua.training.model.entity;

import java.util.List;

public class TeacherBuilder {
    Teacher teacher = new Teacher();

    public TeacherBuilder setId(int id) {
        teacher.id = id;
        return this;
    }

    public TeacherBuilder setEmail(String email) {
        teacher.email = email;
        return this;
    }

    public TeacherBuilder setFirstName(String firstName) {
        teacher.firstName = firstName;
        return this;
    }

    public TeacherBuilder setLastName(String lastName) {
        teacher.lastName = lastName;
        return this;
    }

    public TeacherBuilder setCourses(List<Course> courses) {
        teacher.courses = courses;
        return this;
    }

    public Teacher build(){
        return teacher;
    }
}
