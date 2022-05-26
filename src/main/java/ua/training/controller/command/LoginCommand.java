package ua.training.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Controller;
import ua.training.controller.Path;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command{

    private static final Logger logger = LoggerFactory.getLogger(LoginCommand.class);
//    private UserDao userDao;
//
//    public LoginCommand(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("LoginCommand execute method start");
        String forward = Path.PAGE_LOGIN;
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new UserBuilder()
                .setUserEmail(email)
                .setPassword(password)
                .build();
        UserDao userDao = new UserDao();
        String validate = userDao.validate(user);
        User user1 = userDao.findUser(user);
        if (validate.equals("Admin")) {
            session.setAttribute("role", user1.getRole());
            session.setAttribute("firstName", user1.getFirstName());
            session.setAttribute("lastName", user1.getLastName());
            session.setAttribute("email", user1.getEmail());
            return "redirect:controller?action=admin";
        } else if (validate.equals("Teacher")) {
            session.setAttribute("id", user1.getId());
            session.setAttribute("role", user1.getRole());
            session.setAttribute("firstName", user1.getFirstName());
            session.setAttribute("lastName", user1.getLastName());
            session.setAttribute("email", user1.getEmail());
            return "redirect:controller?action=teacher";
        } else if (validate.equals("Student")) {
            session.setAttribute("id", user1.getId());
            session.setAttribute("role", user1.getRole());
            session.setAttribute("firstName", user1.getFirstName());
            session.setAttribute("lastName", user1.getLastName());
            session.setAttribute("email", user1.getEmail());
            return "redirect:controller?action=student";
        } else if (validate.equals("Blocked")) {
            request.setAttribute("errMessage", validate);
            return forward;
        } else {
            request.setAttribute("errMessage", validate);
            return forward;
        }
    }

}
