package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.Course;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TrainingsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(TrainingsCommand.class);

    private static final int COURSE_QUANTITY_ON_PAGE = 4;
    private static final int DEFAULT_NUM_OF_PAGE = 1;
    private static final String COURSES = "courses";
    private static final String NUM_OF_PAGE = "numOfPage";
    private static final String PAGES_QUANTITY = "pagesQuantity";
    private final TrainingsService service;

    public TrainingsCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String numOfPageParam = request.getParameter(NUM_OF_PAGE);
        int numOfPage = DEFAULT_NUM_OF_PAGE;
        if (numOfPageParam != null) {
            numOfPage = Integer.parseInt(numOfPageParam);
        }
        try {
            int pagesQuantity = service.calculatePagesQuantity(COURSE_QUANTITY_ON_PAGE);
            List<Course> courses = service.createListOfCourses(numOfPage, COURSE_QUANTITY_ON_PAGE);
            request.setAttribute(PAGES_QUANTITY, pagesQuantity);
            request.setAttribute(COURSES, courses);
            request.setAttribute(NUM_OF_PAGE, numOfPage);
            return CommandResult.forward(TRAININGS_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
    }
}
