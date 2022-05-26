package ua.training.controller.command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.controller.command.admin.*;
import ua.training.controller.command.admin.openPage.OpenAddCoursePageCommand;
import ua.training.controller.command.admin.openPage.OpenAddTeacherPageCommand;
import ua.training.controller.command.admin.openPage.OpenAdminPageCommand;
import ua.training.controller.command.admin.openPage.OpenUpdateCoursePageCommand;
import ua.training.controller.command.student.*;
import ua.training.controller.command.teacher.CourseJournalCommand;
import ua.training.controller.command.teacher.RateStudentCommand;
import ua.training.controller.command.teacher.ShowTeachersCoursesCommand;
import ua.training.controller.command.teacher.TeacherCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer{
    private static final Logger logger = LoggerFactory.getLogger(CommandContainer.class);

    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    static{
        logger.debug("Filling commands map");
        commands.put("noCommand", new ErrorCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("admin",new OpenAdminPageCommand());
        commands.put("teacher",new TeacherCommand());
        commands.put("student",new StudentCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("showTeachers", new ShowTeachersCommand());
        commands.put("showCourses", new ShowCoursesCommand());
        commands.put("showStudents", new ShowStudentsCommand());
        commands.put("openAddTeacher", new OpenAddTeacherPageCommand());
        commands.put("openAddCourse", new OpenAddCoursePageCommand());
        commands.put("openUpdateCourse", new OpenUpdateCoursePageCommand());
        commands.put("addTeacher", new AddTeacherCommand());
        commands.put("addCourse", new AddCourseCommand());
        commands.put("deleteCourse", new DeleteCourseCommand());
        commands.put("updateCourse", new UpdateCourseCommand());
        commands.put("blockStudent", new BlockStudentCommand());
        commands.put("unblockStudent", new UnBlockStudentCommand());
        commands.put("availableCourses", new AvailableCoursesCommand());
        commands.put("notStartedCourses", new NotStartedCoursesCommand());
        commands.put("coursesInProgress", new CoursesInProgressCommand());
        commands.put("endedCourses", new EndedCoursesCommand());
        commands.put("applyForCourse", new ApplyForCourseCommand());
        commands.put("startCourse", new StartCourseCommand());
        commands.put("finishCourse", new FinishCourseCommand());
        commands.put("showTeachersCourses", new ShowTeachersCoursesCommand());
        commands.put("courseJournal", new CourseJournalCommand());
        commands.put("rateStudent", new RateStudentCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());

    }

    public static Command get(String commandName) {
        logger.debug("Getting command method");
        if (commandName == null || !commands.containsKey(commandName)){
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
