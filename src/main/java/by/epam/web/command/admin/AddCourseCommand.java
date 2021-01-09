package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Role;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddCourseCommand.class);
    private final TrainingsService service;

    public AddCourseCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.getAttribute("role").equals(Role.ADMIN.toString())) {
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
        String name = request.getParameter("name");
        String teacherIdParam = request.getParameter("teacher_id");
        long teacherId = Long.parseLong(teacherIdParam);
        try {
            service.addCourse(name, teacherId);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }

        return CommandResult.redirect(Url.TRAININGS_CMD);
    }
}
