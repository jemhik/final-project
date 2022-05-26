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

public class StartCourseCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(StartCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("StartCourseCommand execute method start");

        String name = request.getParameter("name");
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        StudentDao studentDao = new StudentDao();
        studentDao.setCourseProgress(name, id, "In progress", "В прогресі");
        Course course = studentDao.getCourseMaterials(name);
        request.setAttribute("course", course);
        request.setAttribute("name", name);
        return Path.PAGE_STUDY_COURSE;
    }
}
