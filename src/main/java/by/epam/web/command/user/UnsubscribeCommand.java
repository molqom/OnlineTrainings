package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UnsubscribeCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UnsubscribeCommand.class);
    private final SubscriptionService service;

    public UnsubscribeCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            return CommandResult.forward(Url.LOGIN_PAGE);
        }
        String subscriptionIdParam = request.getParameter("subscription_id");
        long subscriptionId = Long.parseLong(subscriptionIdParam);
        try {
            service.unsubscribe(subscriptionId);
            return CommandResult.redirect(Url.SUBSCRIPTIONS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
