package ua.training.controller.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.Path;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);


    List<String> commonPages;
    List<String> adminPermission;
    List<String> teacherPermission;
    List<String> studentPermission;
    String error = "You don't have permission for this page!";

    public void init(FilterConfig config) throws ServletException {
        logger.debug("SecurityFilter init");

        adminPermission = new ArrayList<>();
        teacherPermission = new ArrayList<>();
        studentPermission = new ArrayList<>();
        commonPages = new ArrayList<>();
        commonPages.add(Path.PAGE_LOGIN);
        commonPages.add(Path.PAGE_HOME);
        commonPages.add(Path.PAGE_REGISTER);
        commonPages.add("/training-center/");
        commonPages.add("/controller");

        //
        adminPermission.add("admin");
        adminPermission.add("showTeachers");
        adminPermission.add("showCourses");
        adminPermission.add("showStudents");
        adminPermission.add("openAddTeacher");
        adminPermission.add("openAddCourse");
        adminPermission.add("openUpdateCourse");
        adminPermission.add("addTeacher");
        adminPermission.add("addCourse");
        adminPermission.add("deleteCourse");
        adminPermission.add("updateCourse");
        adminPermission.add("blockStudent");
        adminPermission.add("unblockStudent");

        //
        teacherPermission.add("teacher");
        teacherPermission.add("showTeachersCourses");
        teacherPermission.add("courseJournal");
        teacherPermission.add("rateStudent");

        //
        studentPermission.add("student");
        studentPermission.add("availableCourses");
        studentPermission.add("notStartedCourses");
        studentPermission.add("coursesInProgress");
        studentPermission.add("endedCourses");
        studentPermission.add("applyForCourse");
        studentPermission.add("finishCourse");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //logger.debug("SecurityFilter doFilter method");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        String URI = httpServletRequest.getRequestURI();


        if (URI.contains("/controller") && !httpServletRequest.getParameter("action").equals("login") &&
                !httpServletRequest.getParameter("action").equals("changeLanguage") &&
                !httpServletRequest.getParameter("action").equals("register")){
                if (session == null || session.getAttribute("role") == null) {
                    httpServletRequest.getRequestDispatcher("controller?action=noCommand").forward(request, response);
                    return;
                }else if (adminPermission.contains(httpServletRequest.getParameter("action")) &&
                        !session.getAttribute("role").equals("Admin")){
                    httpServletRequest.getRequestDispatcher("controller?action=noCommand").forward(request, response);
                    return;
                }else if (teacherPermission.contains(httpServletRequest.getParameter("action")) &&
                        !session.getAttribute("role").equals("Teacher")){
                    httpServletRequest.getRequestDispatcher("controller?action=noCommand").forward(request, response);
                    return;
                }else if (studentPermission.contains(httpServletRequest.getParameter("action")) &&
                        !session.getAttribute("role").equals("Student")){
                    httpServletRequest.getRequestDispatcher("controller?action=noCommand").forward(request, response);
                    return;
                }
        }
        chain.doFilter(request, response);
    }


}


