package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.Course;
import by.epam.web.enums.Url;
import by.epam.web.exception.DaoException;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TrainingsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(TrainingsCommand.class);

    private static final int COURSE_QUANTITY_ON_PAGE = 4;
    private final TrainingsService service;

    public TrainingsCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String numOfPageParameter = request.getParameter(Parameter.NUM_OF_PAGE);

        int pagesQuantity = 0;
        try {
            pagesQuantity = service.calculatePagesQuantity(COURSE_QUANTITY_ON_PAGE);
        } catch (DaoException e) {
            LOGGER.info(e.getMessage(), e);
        }
        int numOfPage;
        if (numOfPageParameter == null) {
            numOfPage = 1;
        } else {
            numOfPage = Integer.parseInt(numOfPageParameter);
        }

        List<Course> courses;
        try {
            courses = service.createListOfCourses(numOfPage, COURSE_QUANTITY_ON_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }

        request.setAttribute(Parameter.PAGES_QUANTITY, pagesQuantity);
        request.setAttribute(Parameter.COURSES, courses);
        request.setAttribute(Parameter.NUM_OF_PAGE, numOfPage);
        return CommandResult.forward(Url.TRAININGS_PAGE);

    }
}
