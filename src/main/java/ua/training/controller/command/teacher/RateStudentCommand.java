package ua.training.controller.command.teacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.command.Command;
import ua.training.model.dao.TeacherDao;
import ua.training.model.entity.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RateStudentCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(RateStudentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("RateStudentCommand execute method start");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        String name = request.getParameter("name");
        System.out.println(name);
        int assessment = Integer.parseInt(request.getParameter("assessment"));
        System.out.println(assessment);
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.rateStudent(id, name, assessment);
        List<Course> courses = teacherDao.getStudentsAssessments(name);
        request.setAttribute("courses", courses);
        return "redirect:controller?action=courseJournal";
    }
}
