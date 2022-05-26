package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockStudentCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(BlockStudentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("BlockStudentCommand execute method start");

        int id = Integer.parseInt(request.getParameter("id"));
        AdminDao adminDao = new AdminDao();
        adminDao.blockStudent(id);
        return "redirect:controller?action=showStudents";
    }
}
