package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
   int id;
   String email, firstName, lastName, status;
   List<Course> courses;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCoursesNames() {
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getName());
        }
        return courseNames.toString();
    }

    public String getStatus() {
        return status;
    }

}
