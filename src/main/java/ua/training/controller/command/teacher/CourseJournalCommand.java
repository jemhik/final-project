package ua.training.controller.command.teacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.TeacherDao;
import ua.training.model.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CourseJournalCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(CourseJournalCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("CourseJournalCommand execute method start");
        String name = request.getParameter("name");
        TeacherDao teacherDao = new TeacherDao();
        List<Course> courses = teacherDao.getStudentsAssessments(name);
        request.setAttribute("courses", courses);
        return Path.PAGE_SHOW_COURSE_JOURNAL;
    }
}
