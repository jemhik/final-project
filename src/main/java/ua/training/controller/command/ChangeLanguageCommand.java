package ua.training.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ChangeLanguageCommand extends Command{
    private static final Logger logger = LoggerFactory.getLogger(ChangeLanguageCommand.class);


    private static final String LOCALE = "lang";
    private static final String SESSION_LOCALE = "lang";

    private ArrayList<String> supportedLanguages = new ArrayList<>();
    private static final String ENGLISH = "en";
    private static final String UKRAINE = "ua";

    public ChangeLanguageCommand() {
        logger.debug("ChangeLanguageCommand constructor");
        supportedLanguages.add(ENGLISH);
        supportedLanguages.add(UKRAINE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("ChangeLanguageCommand execute method start");
        String locale =  request.getParameter(LOCALE);
        HttpSession session = request.getSession(false);
        if(locale != null){
            if(!supportedLanguages.contains(locale)){
                locale = ENGLISH;

            }
            session.setAttribute(SESSION_LOCALE,locale);
        }

        String url = request.getHeader("Referer");
        return "redirect:" + url.replace("http://localhost:8080/training-center/","");
    }
}

