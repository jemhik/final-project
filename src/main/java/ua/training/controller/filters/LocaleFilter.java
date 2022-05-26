package ua.training.controller.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", value = "/*")
public class LocaleFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LocaleFilter.class);


    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        //logger.debug("LocaleFilter doFilter method");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale = request.getParameter("lang");
        String defaultLocale = "en";

        if(locale != null){
            session.setAttribute("lang", locale);
        }
        else if (session.getAttribute("lang")==null){
            session.setAttribute("lang",defaultLocale);
        }
        chain.doFilter(servletRequest,servletResponse);
    }
}
