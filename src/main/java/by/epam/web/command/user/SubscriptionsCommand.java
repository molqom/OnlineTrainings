package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
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
    private static final int SUBSCRIPTION_QUANTITY_ON_PAGE = 4;
    private static final int DEFAULT_NUM_OF_PAGE = 1;
    private static final Logger LOGGER = Logger.getLogger(SubscriptionsCommand.class);

    private final SubscriptionService service;

    public SubscriptionsCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(Parameter.ID);
        String numOfPageParam = request.getParameter(Parameter.NUM_OF_PAGE);
        int numOfPage = DEFAULT_NUM_OF_PAGE;
        if (numOfPageParam != null) {
            numOfPage = Integer.parseInt(numOfPageParam);
        }
        try {
            int pagesQuantity = service.calculatePagesQuantity(SUBSCRIPTION_QUANTITY_ON_PAGE, userId);
            request.setAttribute(Parameter.PAGES_QUANTITY, pagesQuantity);
            List<Subscription> subscriptions = service.findSubscriptions(
                    userId,
                    numOfPage,
                    SUBSCRIPTION_QUANTITY_ON_PAGE);
            request.setAttribute(Parameter.SUBSCRIPTIONS, subscriptions);
            request.setAttribute(Parameter.NUM_OF_PAGE, numOfPage);
            return CommandResult.forward(Url.SUBSCRIPTIONS_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
