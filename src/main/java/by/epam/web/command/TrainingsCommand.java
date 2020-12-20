package by.epam.web.command;

import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.data.entity.Course;
import by.epam.web.exception.DaoException;
import by.epam.web.service.TrainingsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class TrainingsCommand implements Command {
    private static final int COURSE_QUANTITY_ON_PAGE = 4;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("login") == null) {
            return "/WEB-INF/view/login.jsp";
        }
        String numOfPageParameter = request.getParameter("numOfPage");

        DaoHelperFactory factory = new DaoHelperFactory();
        TrainingsService service = new TrainingsService(factory);
        int pagesQuantity = 0;
        try {
            pagesQuantity = service.calculatePagesQuantity(COURSE_QUANTITY_ON_PAGE);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int numOfPage;
        if (numOfPageParameter==null){
            numOfPage = 1;
        } else {
            numOfPage = Integer.parseInt(numOfPageParameter);
        }

        List<Course> courses = null;
        try {
            courses = service.createListOfCourses(numOfPage, COURSE_QUANTITY_ON_PAGE);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("pagesQuantity",pagesQuantity);
        request.setAttribute("courses", courses);
        return "/WEB-INF/view/trainings.jsp";

    }
}
