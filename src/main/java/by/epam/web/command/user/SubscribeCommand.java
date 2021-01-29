package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubscribeCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SubscribeCommand.class);
    private static final String ERROR_MESSAGE = "You already subscribe on this course!";
    private static final String TRAININGS_PAGE_URL = "/controller?command=trainings";

    private final SubscriptionService service;

    public SubscribeCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String courseIdParam = request.getParameter(Parameter.COURSE_ID);
        long courseId = Long.parseLong(courseIdParam);
        long userId = (long)session.getAttribute(Parameter.ID);
        try {
            if (!service.subscribe(courseId, userId)){
                request.setAttribute(Parameter.ERROR_MESSAGE, ERROR_MESSAGE);
                return CommandResult.forward(TRAININGS_PAGE_URL);
            }
            return CommandResult.redirect(Url.SUBSCRIPTIONS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
