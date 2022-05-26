package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowStudentsCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(ShowStudentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("ShowStudentsCommand execute method start");

        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute("lang");
        AdminDao adminDao = new AdminDao();
        List<Student> students = adminDao.getStudents(locale);
        request.setAttribute("students", students);
        return Path.PAGE_SHOW_STUDENTS;
    }
}
