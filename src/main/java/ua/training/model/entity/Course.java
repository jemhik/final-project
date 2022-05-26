package ua.training.model.entity;

public class Course {
    String name, description, progress, materials, task, studentSolution, duration;
    int teacher_id, student_id, numberOfStudents, assessment;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProgress() {
        return progress;
    }

    public int getTeacher() {
        return teacher_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getDuration() {
        return duration;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getMaterials() {
        return materials;
    }

    public String getTask() {
        return task;
    }

    public String getStudentSolution() {
        return studentSolution;
    }

    public int getAssessment() {
        return assessment;
    }

}
