package ua.training.controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;
import ua.training.controller.command.Command;
import ua.training.model.dao.AdminDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.UserBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTeacherCommand extends Command {
    private static final Logger logger = LoggerFactory.getLogger(AddTeacherCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("AddTeacherCommand execute method start");

        String forward = "";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new UserBuilder()
                .setUserEmail(email)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole("Teacher")
                .build();
        UserDao userDao = new UserDao();
        if (!userDao.isUnique(user)) {
            userDao.insert(user);
            forward = "redirect:controller?action=showTeachers";
        }else {
            User user1 = new UserBuilder()
                    .setUserEmail(email)
                    .setPassword(password)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .build();
            AdminDao adminDao = new AdminDao();
            if (adminDao.updateTeacher(user1)) {
                forward = "redirect:controller?action=showTeachers";
            }else {
                forward = Path.PAGE_LOGIN;
            }
        }
        return forward;
    }
}
