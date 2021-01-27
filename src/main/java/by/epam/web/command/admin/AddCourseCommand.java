package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCourseCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddCourseCommand.class);
    private final TrainingsService service;

    public AddCourseCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(Parameter.NAME);
        String teacherIdParam = request.getParameter(Parameter.TEACHER_ID);
        //todo need catch a runtime exception (if teacherId not String) ??
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
