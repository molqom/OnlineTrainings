package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.command.teacher.FeedbackCommand;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.Subscription;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SubscriptionsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SubscriptionsCommand.class);

    private final SubscriptionService service;

    public SubscriptionsCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("id")==null){
            return CommandResult.forward(Url.LOGIN_PAGE);
        }
        long userId = (long)session.getAttribute("id");
        try {
            List<Subscription> subscriptions = service.findSubscriptions(userId);
            request.setAttribute("subscriptions", subscriptions);
            return CommandResult.forward(Url.SUBSCRIPTIONS_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
