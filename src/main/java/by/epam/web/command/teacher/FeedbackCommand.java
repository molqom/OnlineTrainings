package by.epam.web.command.teacher;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FeedbackCommand.class);
    private static final String FEEDBACK = "feedback";
    private static final String SUBSCRIPTION_ID = "subscription_id";
    private final SubscriptionService service;

    public FeedbackCommand(SubscriptionService service) {
        this.service = service;
    }
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String feedback = request.getParameter(FEEDBACK);
        String subscriptionIdParam = request.getParameter(SUBSCRIPTION_ID);
        long subscriptionId = Long.parseLong(subscriptionIdParam);
        try {
            service.addFeedback(subscriptionId, feedback);
            return CommandResult.redirect(STUDENTS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
    }
}
