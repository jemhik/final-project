package ua.training.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.model.entity.Course;
import ua.training.model.entity.CourseBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    final static String QUERY_GET_COURSES = "SELECT SQL_CALC_FOUND_ROWS * FROM courses WHERE courses.name NOT IN" +
            "(SELECT student_courses.course_name FROM student_courses WHERE student_courses.student_id = ?)" +
            " ORDER BY ";

    final static String QUERY_GET_FILTERED_COURSES = "SELECT SQL_CALC_FOUND_ROWS * FROM courses WHERE (teacher_id = ? OR name = ?) AND courses.name NOT IN" +
            "(SELECT student_courses.course_name FROM student_courses WHERE student_courses.student_id = ?)" +
            " ORDER BY ";

    final static String QUERY_GET_STUDENT_COURSES = "SELECT * FROM student_courses " +
            "WHERE student_id = ? AND progress_en = ?";
    final static String QUERY_UPDATE_COURSE_PROGRESS = "UPDATE student_courses SET progress_en = ?, progress_ua = ? " +
            "WHERE course_name = ? AND student_id = ?";
    final static String QUERY_GET_COURSE_MATERIALS = "SELECT materials,task FROM courses WHERE name = ?";

    final static String QUERY_FINISH_COURSE = "UPDATE student_courses SET progress_en = ?, student_answer = ? " +
            "WHERE course_name = ? AND student_id = ?";

    final static String QUERY_APPLY_FOR_COURSE = "INSERT INTO student_courses VALUES (?,?,?,'Not started','Не розпочато','', 0)";

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<Course> getAvailableCourses(String method, int studentId, String locale, int offset, int noOfRecords) {
        logger.debug("StudentDao getAvailableCourses method");
        String query = QUERY_GET_COURSES + method + " limit " + offset + ", " + noOfRecords;
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("teacher_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String duration;
                if (locale.equals("en")){
                    duration = rs.getString("duration_en");
                }else{
                    duration = rs.getString("duration_ua");
                }
                int numberOfStudents = rs.getInt("number_of_students");
                courses.add(new CourseBuilder()
                        .setName(name)
                        .setDescription(description)
                        .setTeacher(id)
                        .setDuration(duration)
                        .setNumberOfStudents(numberOfStudents)
                        .build());
            }
            rs.close();
            rs = ps.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return courses;
    }

    public List<Course> getAvailableFilteredCourses(int studentId, String filter, String locale, String order,
                                                    int offset, int noOfRecords){
        logger.debug("StudentDao getAvailableFilteredCourses method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = QUERY_GET_FILTERED_COURSES + order + " limit " + offset + ", " + noOfRecords;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, filter);
            ps.setString(2, filter);
            ps.setInt(3, studentId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("teacher_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int numberOfStudents = rs.getInt("number_of_students");
                String duration;
                if (locale.equals("en")){
                    duration = rs.getString("duration_en");
                }else{
                    duration = rs.getString("duration_ua");
                }
                courses.add(new CourseBuilder()
                        .setTeacher(id)
                        .setName(name)
                        .setDescription(description)
                        .setTeacher(id)
                        .setNumberOfStudents(numberOfStudents)
                        .setDuration(duration)
                        .build());
            }
            rs.close();
            rs = ps.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return courses;
    }


    public List<Course> getStudentCourses(int studentId, String progress, String locale) {
        logger.debug("StudentDao getStudentCourses method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_STUDENT_COURSES);
            ps.setInt(1, studentId);
            ps.setString(2, progress);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("course_name");
                String student_progress;
                if(locale.equals("en")) {
                    student_progress = rs.getString("progress_en");
                }else {
                    student_progress = rs.getString("progress_ua");
                }
                int assessment = rs.getInt("assessment");
                courses.add(new CourseBuilder()
                        .setName(name)
                        .setProgress(student_progress)
                        .setAssessment(assessment)
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return courses;
    }

    public void setCourseProgress(String name, int id, String progress_en, String progress_ua) {
        logger.debug("StudentDao setCourseProgress method");
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE_COURSE_PROGRESS);
            ps.setString(1, progress_en);
            ps.setString(2, progress_ua);
            ps.setString(3, name);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public Course getCourseMaterials(String name) {
        logger.debug("StudentDao getCourseMaterials method");
        Connection connection = DBManager.getConnection();
        Course course = null;
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_COURSE_MATERIALS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String materials = rs.getString("materials");
                String task = rs.getString("task");
                course = new CourseBuilder()
                        .setMaterials(materials)
                        .setTask(task)
                        .build();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return course;
    }

    public void finishCourse(String studentSolution, String course_name, int studentId) {
        logger.debug("StudentDao finishCourse method");
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_FINISH_COURSE);
            ps.setString(1, "Answer submitted");
            ps.setString(2, studentSolution);
            ps.setString(3, course_name);
            ps.setInt(4, studentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void applyForCourse(int teacherId, int studentId, String name) {
        logger.debug("StudentDao applyForCourse method");
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_APPLY_FOR_COURSE);
            ps.setInt(1, studentId);
            ps.setInt(2, teacherId);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
