package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnsubscribeCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UnsubscribeCommand.class);
    private static final String SUBSCRIPTION_ID = "subscription_id";
    private final SubscriptionService service;

    public UnsubscribeCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String subscriptionIdParam = request.getParameter(SUBSCRIPTION_ID);
        long subscriptionId = Long.parseLong(subscriptionIdParam);
        try {
            service.unsubscribe(subscriptionId);
            return CommandResult.redirect(SUBSCRIPTIONS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
    }
}
