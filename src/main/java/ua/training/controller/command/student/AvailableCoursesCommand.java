package ua.training.controller.command.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.dao.StudentDao;
import ua.training.model.entity.Course;
import ua.training.model.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AvailableCoursesCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(AvailableCoursesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("AvailableCoursesCommand execute method start");

        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        String locale = (String) session.getAttribute("lang");
        int page = 1;
        int rowsPerPage = 8;
        int noOfPages;
        if (request.getParameter("page")!= null)
            page = Integer.parseInt(request.getParameter("page"));


        StudentDao studentDao = new StudentDao();
        AdminDao adminDao = new AdminDao();
        List<Teacher> teachers = adminDao.getTeachers();
        request.setAttribute("teachers", teachers);
        List<Course> byCourses = adminDao.getCourses(request.getParameter("order"), locale);
        request.setAttribute("byCourses", byCourses);
        List<Course> courses;
        if(request.getParameter("filter") == null) {
            courses = studentDao.getAvailableCourses(request.getParameter("order"), id, locale, (page-1)*rowsPerPage,rowsPerPage);
            int noOfRecords = studentDao.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / rowsPerPage);


        }else {
            courses = studentDao.getAvailableFilteredCourses(id, request.getParameter("filter"), locale, request.getParameter("order"),(page-1)*rowsPerPage,rowsPerPage);
            int noOfRecords = studentDao.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / rowsPerPage);
        }
        request.setAttribute("courses", courses);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        return Path.PAGE_SHOW_AVAILABLE_COURSES;
    }

}
