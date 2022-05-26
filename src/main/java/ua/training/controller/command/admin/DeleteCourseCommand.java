package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.entity.CourseBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCourseCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(DeleteCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("DeleteCourseCommand execute method start");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        AdminDao adminDao = new AdminDao();
        adminDao.deleteCourse(new CourseBuilder()
                .setTeacher(id)
                .setName(name)
                .build());

        return "redirect:controller?action=showCourses";
    }
}
