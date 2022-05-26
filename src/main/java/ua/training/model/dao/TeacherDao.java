package ua.training.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.model.entity.Course;
import ua.training.model.entity.CourseBuilder;
import ua.training.model.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {

    private static final Logger logger = LoggerFactory.getLogger(TeacherDao.class);

    final static String QUERY_TEACHERS_COURSES = "SELECT name,number_of_students,task FROM courses WHERE teacher_id = ?";

    final static String QUERY_SHOW_STUDENTS_ASSESSMENTS = "SELECT student_id,student_answer,assessment " +
            "FROM student_courses WHERE course_name = ? AND progress_en = ?";

    final static String QUERY_SET_ASSESSMENT = "UPDATE student_courses SET assessment = ?, progress_en = ?, progress_ua = ? " +
            "WHERE course_name = ? AND student_id = ?";

    public List<Course> getTeachersCourses(int teacherId) {
        logger.debug("TeacherDao getTeachersCourses method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_TEACHERS_COURSES);
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String course_name = rs.getString("name");
                int numberOfStudents = rs.getInt("number_of_students");
                String task = rs.getString("task");
                courses.add(new CourseBuilder()
                        .setName(course_name)
                        .setNumberOfStudents(numberOfStudents)
                        .setTask(task)
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return courses;
    }

    public List<Course> getStudentsAssessments(String name) {
        logger.debug("TeacherDao getStudentsAssessments method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_SHOW_STUDENTS_ASSESSMENTS);
            ps.setString(1, name);
            ps.setString(2, "answer submitted");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int assessment = rs.getInt("assessment");
                String studentAnswer = rs.getString("student_answer");
                courses.add(new CourseBuilder()
                        .setName(name)
                        .setStudent_id(studentId)
                        .setAssessment(assessment)
                        .setStudentSolution(studentAnswer)
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return courses;
    }


    public void rateStudent(int id, String name, int assessment) {
        logger.debug("TeacherDao rateStudent method");
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_SET_ASSESSMENT);
            ps.setInt(1, assessment);
            ps.setString(2, "ended");
            ps.setString(3, "Закінчено");
            ps.setString(4, name);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
