package ua.training.controller.command.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(StudentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("StudentCommand execute method start");

        HttpSession session = request.getSession(false);
        request.setAttribute("email",session.getAttribute("email"));
        request.setAttribute("firstName",session.getAttribute("firstName"));
        request.setAttribute("lastName",session.getAttribute("lastName"));
        request.setAttribute("role",session.getAttribute("role"));
        return Path.PAGE_STUDENT;
    }
}
