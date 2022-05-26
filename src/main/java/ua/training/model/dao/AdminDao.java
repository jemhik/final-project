package ua.training.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);

    final static String QUERY_GET_COURSES = "SELECT * FROM courses";
    final static String QUERY_GET_COURSES_ORDER = "SELECT * FROM courses ORDER BY ";


    final static String QUERY_GET_COURSES_FILTER = "SELECT * FROM courses WHERE teacher_id = ? or name = ? ORDER BY ";


    public List<Teacher> getTeachers(){
        logger.debug("AdminDao getTeachers method");
        List<Teacher> teachers = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = "SELECT id,first_name,last_name,email FROM users WHERE role = 'teacher'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                teachers.add(new TeacherBuilder()
                        .setId(id)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setCourses(getTeachersCourses(id))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return teachers;
    }

    public List<Course> getCourses(String method, String locale){
        logger.debug("AdminDao getCourses method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = QUERY_GET_COURSES_ORDER + method;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("teacher_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String duration;
                if (locale.equals("en")) {
                    duration = rs.getString("duration_en");
                } else {
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
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return courses;
    }

    public List<Course> getTeachersCourses(int id){
        logger.debug("AdminDao getTeachersCourses method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = "SELECT * FROM courses WHERE teacher_id = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String description = rs.getString("description");
                int numberOfStudents = rs.getInt("number_of_students");
                courses.add(new CourseBuilder()
                        .setName(name)
                        .setDescription(description)
                        .setTeacher(id)
                        .setNumberOfStudents(numberOfStudents)

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

    public boolean updateTeacher(User user){
        logger.debug("AdminDao updateTeacher method");
        boolean result = false;
        String query = "UPDATE users SET role = ? WHERE email = ?";

        try(Connection connection = DBManager.getConnection();PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "Teacher");
            ps.setString(2, user.getEmail());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public void insertCourse(Course course){
        logger.debug("AdminDao insertCourse method");
        Connection connection = DBManager.getConnection();
        String query = "INSERT INTO courses(name, description, teacher_id, materials, task, duration_en, duration_ua, number_of_students) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getTeacher());
            ps.setString(4, course.getMaterials());
            ps.setString(5, course.getTask());
            ps.setString(6, course.getDuration() + " hrs");
            ps.setString(7, course.getDuration() + " год");
            ps.setInt(8,0);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
    }

    public void deleteCourse(Course course){
        logger.debug("AdminDao deleteCourse method");
        Connection connection = DBManager.getConnection();
        String query = "DELETE FROM courses WHERE teacher_id = ? AND name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, course.getTeacher());
            ps.setString(2, course.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
    }

    public boolean updateCourse(Course course){
        logger.debug("AdminDao updateCourse method");
        boolean result = false;
        String query = "UPDATE courses SET name = ?,description = ? WHERE teacher_id = ?";

        try(Connection connection = DBManager.getConnection();PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,course.getName());
            ps.setString(2,course.getDescription());
            ps.setInt(3, course.getTeacher());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public List<Student> getStudents(String locale){
        logger.debug("AdminDao getStudents method");
        List<Student> students = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = "SELECT id,first_name,last_name,email,isBlocked FROM users WHERE role = 'student'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String status;
                if(rs.getBoolean("isBlocked")){
                    if(locale.equals("en")) {
                        status = "Blocked";
                    }else{
                        status = "Заблокований";
                    }
                }else{
                    if(locale.equals("en")) {
                        status = "Active";
                    }else {
                        status = "Активний";
                    }
                }
                students.add(new StudentBuilder()
                        .setId(id)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setStatus(status)
                        .setCourses(getStudentCourses(id))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return students;
    }

    public List<Course> getStudentCourses(int id){
        logger.debug("AdminDao getStudentCourses method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = "SELECT course_name,progress_en,teacher_id FROM student_courses WHERE student_id = " + id;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("course_name");
                String progress = rs.getString("progress_en");
                String teacher_id = rs.getString("teacher_id");
                courses.add(new CourseBuilder()
                        .setName(name)
                        .setProgress(progress)
                        .setTeacher(id)
                        .setStudent_id(id)
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

    public boolean blockStudent(int id){
        logger.debug("AdminDao blockStudent method");
        boolean result = false;
        String query = "UPDATE users SET isBlocked = ? WHERE id = ?";

        try(Connection connection = DBManager.getConnection();PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public boolean unBlockStudent(int id){
        logger.debug("AdminDao unBlockStudent method");
        boolean result = false;
        String query = "UPDATE users SET isBlocked = ? WHERE id = ?";

        try(Connection connection = DBManager.getConnection();PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public List<Course> getCoursesFilteredBy(String filter, String locale, String order){
        logger.debug("AdminDao getCoursesFilteredBy method");
        List<Course> courses = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String query = QUERY_GET_COURSES_FILTER + order;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, filter);
            ps.setString(2, filter);
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
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(connection);
        }
        return courses;
    }

}
