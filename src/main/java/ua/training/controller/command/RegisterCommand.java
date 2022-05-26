package ua.training.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand extends Command{
    private static final Logger logger = LoggerFactory.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("RegisterCommand execute method start");
        String forward = Path.PAGE_REGISTER;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new UserBuilder()
                .setUserEmail(email)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole("Student")
                .build();
        UserDao userDao = new UserDao();
        if (!userDao.isUnique(user)) {
            userDao.insert(user);
            forward = Path.PAGE_LOGIN;
        }else {
            request.setAttribute("errMessage", "User with this email is already registered");
            forward = Path.PAGE_REGISTER;
        }
        return forward;
    }
}
