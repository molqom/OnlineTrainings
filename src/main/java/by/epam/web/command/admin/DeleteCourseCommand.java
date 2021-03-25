package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteCourseCommand.class);
    private static final String DELETE = "delete";

    private final TrainingsService service;

    public DeleteCourseCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String id_param = request.getParameter(DELETE);
        long id = Long.parseLong(id_param);
        try {
            service.deleteCourse(id);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
        return CommandResult.redirect(TRAININGS_CMD);
    }
}
