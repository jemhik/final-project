package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.entity.Course;
import ua.training.model.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowCoursesCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(ShowCoursesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("ShowCoursesCommand execute method start");

        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute("lang");
        AdminDao adminDao = new AdminDao();
        List<Teacher> teachers = adminDao.getTeachers();
        request.setAttribute("teachers", teachers);
        List<Course> byCourses = adminDao.getCourses(request.getParameter("order"), locale);
        request.setAttribute("byCourses", byCourses);
        List<Course> courses;
        if(request.getParameter("filter") == null) {
            courses = adminDao.getCourses(request.getParameter("order"), locale);
        }else {
            courses = adminDao.getCoursesFilteredBy(request.getParameter("filter"), locale, request.getParameter("order"));
        }
        request.setAttribute("courses", courses);
        return Path.PAGE_SHOW_COURSES;
    }
}
