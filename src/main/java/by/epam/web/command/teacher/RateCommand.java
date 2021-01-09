package by.epam.web.command.teacher;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Role;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RateCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RateCommand.class);

    private final SubscriptionService service;

    public RateCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.getAttribute("role").equals(Role.TEACHER.toString())) {
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
        String gradeParam = request.getParameter("grade");
        String subscriptionIdParam = request.getParameter("subscription_id");
        int grade = Integer.parseInt(gradeParam);
        long subscriptionId = Long.parseLong(subscriptionIdParam);
        try {
            service.rate(subscriptionId, grade);
            return CommandResult.redirect(Url.STUDENTS_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
