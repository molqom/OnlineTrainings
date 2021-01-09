package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Role;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import by.epam.web.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteCourseCommand.class);

    private final TrainingsService service;

    public DeleteCourseCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.getAttribute("role").equals(Role.ADMIN.toString())) {
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
        String id_param = request.getParameter("delete");
        long id = Long.parseLong(id_param);
        try {
            service.deleteCourse(id);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
        return CommandResult.redirect(Url.TRAININGS_CMD);
    }
}
