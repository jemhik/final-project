package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.entity.Course;
import ua.training.model.entity.CourseBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCourseCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("UpdateCourseCommand execute method start");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = new CourseBuilder()
                .setName(name)
                .setDescription(description)
                .setTeacher(id)
                .build();
        AdminDao adminDao = new AdminDao();
        adminDao.updateCourse(course);
        return "redirect:controller?action=showCourses";
    }
}
