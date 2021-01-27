package by.epam.web.command.teacher;

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

public class StudentsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(StudentsCommand.class);

    private final SubscriptionService service;

    public StudentsCommand(SubscriptionService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        try {
            long teacherId = (long) session.getAttribute(Parameter.ID);
            List<Subscription> subscriptions = service.findStudents(teacherId);
            request.setAttribute(Parameter.SUBSCRIPTIONS, subscriptions);
            return CommandResult.forward(Url.STUDENTS_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
