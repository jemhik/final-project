package ua.training.model.entity;

public class CourseBuilder {
    Course course = new Course();

    public CourseBuilder setName(String name) {
        course.name = name;
        return this;
    }

    public CourseBuilder setDescription(String description) {
        course.description = description;
        return this;
    }

    public CourseBuilder setProgress(String progress) {
        course.progress = progress;
        return this;
    }

    public CourseBuilder setTeacher(int teacher_id) {
        course.teacher_id = teacher_id;
        return this;
    }

    public CourseBuilder setStudent_id(int student_id) {
        course.student_id = student_id;
        return this;
    }

    public CourseBuilder setDuration(String duration) {
        course.duration = duration;
        return this;
    }

    public CourseBuilder setNumberOfStudents(int numberOfStudents) {
        course.numberOfStudents = numberOfStudents;
        return this;
    }

    public CourseBuilder setMaterials(String materials) {
        course.materials = materials;
        return this;
    }

    public CourseBuilder setTask(String task) {
        course.task = task;
        return this;
    }

    public CourseBuilder setStudentSolution(String studentSolution) {
        course.studentSolution = studentSolution;
        return this;
    }

    public CourseBuilder setAssessment(int assessment) {
        course.assessment = assessment;
        return this;
    }

    public Course build(){
        return course;
    }
}
