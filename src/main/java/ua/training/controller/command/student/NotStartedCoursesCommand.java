package ua.training.controller.command.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.StudentDao;
import ua.training.model.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NotStartedCoursesCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(NotStartedCoursesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("NotStartedCoursesCommand execute method start");

        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute("lang");
        int id = (int) session.getAttribute("id");
        StudentDao studentDao = new StudentDao();
        List<Course> courses = studentDao.getStudentCourses(id, "Not Started", locale);
        request.setAttribute("courses", courses);
        return Path.PAGE_SHOW_STUDENT_COURSES;
    }
}
