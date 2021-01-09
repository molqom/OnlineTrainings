package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.command.teacher.FeedbackCommand;
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

    private final SubscriptionService service;

    public SubscribeCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("id")==null){
            return CommandResult.forward(Url.LOGIN_PAGE);
        }
        String courseIdParam = request.getParameter("course_id");
        long courseId = Long.parseLong(courseIdParam);
        long userId = (long)session.getAttribute("id");
        try {
            if (!service.subscribe(courseId, userId)){
                request.setAttribute("errorMessage", "You already subscribe on this course!");
                return CommandResult.redirect(Url.TRAININGS_CMD);
            }
            return CommandResult.redirect(Url.SUBSCRIPTIONS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
