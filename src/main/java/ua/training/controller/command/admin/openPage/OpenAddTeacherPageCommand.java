package ua.training.controller.command.admin.openPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenAddTeacherPageCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(OpenAddTeacherPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("OpenAddTeacherPageCommand execute method start");
        return Path.PAGE_ADD_TEACHER;
    }
}
