package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowTeachersCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(ShowTeachersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("ShowTeachersCommand execute method start");

        AdminDao adminDao = new AdminDao();
        List<Teacher> teachers = adminDao.getTeachers();
        request.setAttribute("teachers", teachers);
        return Path.PAGE_SHOW_TEACHERS;
    }
}
