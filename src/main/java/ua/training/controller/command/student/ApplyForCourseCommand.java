package ua.training.controller.command.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ApplyForCourseCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(ApplyForCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("ApplyForCourseCommand execute method start");
        int teacherId = Integer.parseInt(request.getParameter("teacher"));
        String name = request.getParameter("name");
        System.out.println(name);
        HttpSession session = request.getSession(false);
        int studentId = (int) session.getAttribute("id");
        StudentDao studentDao = new StudentDao();
        studentDao.applyForCourse(teacherId, studentId, name);
        return "controller?action=notStartedCourses";
    }
}
