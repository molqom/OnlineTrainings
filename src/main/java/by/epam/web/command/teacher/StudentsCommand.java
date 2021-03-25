package by.epam.web.command.teacher;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StudentsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(StudentsCommand.class);
    private static final String ID = "id";
    private static final String SUBSCRIPTIONS = "subscriptions";
    private static final String NUM_OF_PAGE = "numOfPage";
    private static final String PAGES_QUANTITY = "pagesQuantity";
    private static final int STUDENTS_QUANTITY_ON_PAGE = 2;
    private static final int DEFAULT_NUM_OF_PAGE = 1;

    private final SubscriptionService service;

    public StudentsCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String numOfPageParam = request.getParameter(NUM_OF_PAGE);
        int numOfPage = DEFAULT_NUM_OF_PAGE;
        if (numOfPageParam != null) {
            numOfPage = Integer.parseInt(numOfPageParam);
        }
        request.setAttribute(NUM_OF_PAGE, numOfPage);
        try {
            long teacherId = (long) session.getAttribute(ID);
            int pagesQuantity = service.calculatePagesOfStudentQuantity(teacherId, STUDENTS_QUANTITY_ON_PAGE);
            request.setAttribute(PAGES_QUANTITY, pagesQuantity);
            List<Subscription> subscriptions = service.findStudents(teacherId, numOfPage, STUDENTS_QUANTITY_ON_PAGE);
            request.setAttribute(SUBSCRIPTIONS, subscriptions);
            return CommandResult.forward(STUDENTS_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
    }
}
