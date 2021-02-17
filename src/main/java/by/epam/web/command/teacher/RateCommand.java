package by.epam.web.command.teacher;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RateCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RateCommand.class);
    private static final String GRADE = "grade";
    private static final String SUBSCRIPTION_ID = "subscription_id";

    private final SubscriptionService service;

    public RateCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String gradeParam = request.getParameter(GRADE);
        String subscriptionIdParam = request.getParameter(SUBSCRIPTION_ID);
        int grade = Integer.parseInt(gradeParam);
        long subscriptionId = Long.parseLong(subscriptionIdParam);
        try {
            service.rate(subscriptionId, grade);
            return CommandResult.redirect(STUDENTS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
    }
}
