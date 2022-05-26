package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.command.Command;
import ua.training.controller.command.CommandContainer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Controller doGet method");
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Controller doPost method");
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Controller process method");
        String commandName = request.getParameter("action");
        Command command = CommandContainer.get(commandName);

        String page = command.execute(request, response);
        if (page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else{
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
