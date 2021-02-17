package by.epam.web.command;

import by.epam.web.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface using for executing some operation when we have request to servlet
 */
public interface Command {
    String ERROR_400 = "/WEB-INF/view/error/400.jsp";
    String ERROR_500 = "/WEB-INF/view/error/500.jsp";
    String USERS_MANAGE_PAGE = "/WEB-INF/view/usersManage.jsp";
    String USERS_MANAGE_CMD = "/WebApp/controller?command=usersManage";
    String LOGIN_CMD = "/WebApp/controller?command=login";
    String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    String LOGOUT_CMD = "/WebApp/controller?command=logout";
    String CONTACTS_PAGE = "/WEB-INF/view/contacts.jsp";
    String INFO_PAGE = "/WEB-INF/view/info.jsp";
    String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    String MAIN_CMD = "/WebApp/controller?command=main";
    String BAN_PAGE = "/WEB-INF/view/ban.jsp";
    String BAN_CMD = "/WebApp/controller?command=ban";
    String NEWS_PAGE = "/WEB-INF/view/news.jsp";
    String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";
    String TEACHERS_PAGE = "/WEB-INF/view/teachers.jsp";
    String TEACHERS_CMD = "/WebApp/controller?command=teachers";
    String TRAININGS_PAGE = "/WEB-INF/view/trainings.jsp";
    String TRAININGS_CMD = "/WebApp/controller?command=trainings";
    String ADD_COURSE_CMD = "/WebApp/controller?command=addCourse";
    String DELETE_COURSE_CMD = "/WebApp/controller?command=deleteCourse";
    String STUDENTS_CMD = "/WebApp/controller?command=students";
    String STUDENTS_PAGE = "/WEB-INF/view/students.jsp";
    String SUBSCRIBE_CMD = "/WebApp/controller?command=subscribe";
    String SUBSCRIPTIONS_PAGE = "/WEB-INF/view/subscriptions.jsp";
    String SUBSCRIPTIONS_CMD = "/WebApp/controller?command=subscriptions";

    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
