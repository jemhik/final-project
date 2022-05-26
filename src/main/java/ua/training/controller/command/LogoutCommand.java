package ua.training.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends Command{
    private static final Logger logger = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("LogoutCommand execute method start");
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return Path.PAGE_LOGIN;
    }
}
